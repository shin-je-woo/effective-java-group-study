# 💡 finalizer와 cleaner 사용을 피하라

### 개요
* 자바에서는 두 가지 객체 소멸자를 제공한다.
* finalizer , cleaner 오늘은 두 가지 객체 소멸자에 대해서 알아보도록 하자.

## ✅ finalizer 와 cleaner   
finalizer, cleaner 객체 소멸자라고 하지만 본 교재에서는 불 필요하고 사용하지 말라고한다.
그이유는 다음과 같다.


* finalizer는 예측할 수 없고, 상황에 따라 위험할 수 있어 불필요하다.
* finalizer, cleaner는 제때 실행되어야하는 작업에서 절대 할 수 없다.
* 자바9에서는 사용 자제(deprecated)해야되는 api로 지정되었으며, 그 대안으로 cleaner가 대안이다.
* finalizer, cleaner 는 심각한 성능 문제도 동반한다.
* finalizer는 심각한 보안문제를 일으킬 수 있다.

자바에서 finalizer, cleaner 의 수행 여부는 가비지 컬렉터 구현마다 천차만별이다.
테스트했던 JVM에서는 정상적으로 동작했던 프로그램이, 실제 현업에서 자원 회수가 제멋대로 지연되어 큰 문제를 야기했다는 글도 해당 책에 명시되어있다.

* 상태를 영구적으로 수정하는 작업에서는 절대 finalizer, cleaner 를 의존하면 안된다.
* 사실 이를 대비해서 System.gc , System.runFinalization를 통해서 finalizer, cleaner 가 실행될 가능성을 높혀주지만 보장되지 않는다고 한다.
* 또 보장되지 않는 두 메소드를 대비해서 System.runFinalizerOnExit, Runtime.runFinalizersOnExit가 있지만 심각한 결함으로 인해 수십년간 지탄 받아 왔다고 한다.


## 코드 메인 Class
```java
public class Coffee {
public class Room implements AutoCloseable {
	private static final Cleaner cleaner = Cleaner.create();
    
    //청소가 필요한 자원, 절대 Room을 참조해서는 안 된다!
    private static class State implements Runnable {
    	int numJunkPiles; //방 안의 쓰레기 수
        
        State(int numJunkPiles) {
        	this.numJunkPiles = numJunkPiles;
        }
        
        //close 메서드나 cleaner가 호출된다.
        @Override public void run() {
        	System.out.println("방 청소");
            numJunkPiles = 0;
        }
        
    }
    
    //방의 상태, cleanable과 공유한다.
 	private final State state;
    
    //cleanable 객체. 수거 대상이 되면 방을 청소한다.
    private final Cleaner.Cleanable cleanable;
    
    public Room(int numJunkPiles) {
    	state = new State(numJunkPiles);
        cleanable = cleaner.register(this, state);
    }
    
    @Override public void close() {
    	cleanable.clean();
    }       
}
```

## 코드 서브 클래스1

```java
public class Adult {
	public static void main(String[] args) {
		try (Room room = new Room(7)){
			System.out.println("안녕");
		}
	}
}
```


## 코드 서브 클래스2

```java
public class Adult {
	public static void main(String[] args) {
		try (Room room = new Room(7)){
			System.out.println("안녕");
		}
	}
}
```

메인클래스를 만들고
서브클래스 1,2 에서 메인클래스를 이용해 새로운 객체를 만들 때 출력결과는 이론상 동일해야된다.

하지만 실행결과는 아래와 같다.
### 서브클래스 1 = 안녕 방청소
### 서브클래스 2 = 안녕

## 문제점
* finalizer와 cleaner 사용을 피하라 의 주제에 맞게 '예측할 수 없는 상황'이 해당 케이스의 올바른 예시이다.
* 또 서브클래스2에서 사용하는 System.gc()를 사용하게되면 동일한 출력결과를 얻을 수 있다고 책에는 아래와 같이 나와있다.

![image](https://user-images.githubusercontent.com/91134556/233818873-20236466-f89a-49e1-bb14-6ff2760718ad.png)

하지만 마지막 뒷문장에서와 같이 그러리라는 보장이 없다고한다.
결국 개발이라는 것은 특정 기능을 설계하여 원하는 동작이 이루어지도록 만들어야하는데, 상황이 다른케이스가 있다면 안된다. 
그렇기 때문에 finalizer와 cleaner 사용을 피하라!


## 정리

* cleaner(자바 8까지는 finalizer)는 안전망 역할이나 중요하지 않는 자원회수 용으로만 사용하자.
* 물론 이 경우라도 불확실성과 성능 저하에 주의해야한다.
