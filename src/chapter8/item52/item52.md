# 💡 다중정의는 신중히 사용하라

## 1. 다중정의(overloading)는 컴파일타임에 정해진다
```java
public static String classify(Set<?> s){ return "집합"; }
public static String classify(List<?> lst){ return "리스트"; }
public static String classify(Collection<?> c){ return "그 외"; }
        
public static void main(String[] args) {
        Collection<?>[] collections = {
                new HashSet<String>(),
                new ArrayList<Integer>(),
                new HashMap<String, String>().values()
        };

        for (Collection<?> c : collections)
            System.out.println(classify(c));
    }
    
    // output: 그 외 그 외 그 외
```
> 다중정의는 어느 메서드를 호출할지 컴파일타임에 정해지기 때문에 c의 매개변수 Collection&#60;&#63;&#62; 타입 기준으로 classify(Collection&#60;&#63;&#62;)만 호출한다.   
> 다중정의한 메서드는 정적으로 선택되는 것이다.

## 2. 매개변수 수가 같은 다중정의는 만들지 말자
- 가변인수를 사용하는 메서드라면 아예 사용하지 말아야 한다.
### 대안 1) 메서드 이름을 다르게 지어주자
- ObjectOutputStream 클래스에서 write 메서드는 타입 별로 다른 메서드 이름을 가지고 있다.   
이 방식은 짝을 이루는 메서드를 호출할 때도 한결 편하다.   
writeBoolean(boolean) : readBoolean(), wirteInt(int) : readInt(), wirteLong(long) : readLong()
### 대안 2) 형변환할 수 없는 매개변수를 사용하자
- 근본적으로 타입이 다르면 어느 다중정의 메서드를 호출할지가 매개변수들의 런타임 타입만으로 결정된다.
```java
// ArrayList 다중정의된 생성자
public ArrayList(int initialCapacity) {}
public ArrayList(Collection<? extends E> c) {}
```

## 3. 불가피한 경우의 대안
- 헷갈릴 만한 매개변수는 형변환하여 정확한 다중정의 메서드가 선택되도록 한다.
```java
for (int i=0; i<3; i++){
    list.remove((Integer) i); // remove(Object)를 명시해줌
}
```

- 같은 객체를 입력받는 다중정의 메서드들이 모두 동일하게 동작하도록 만든다.
기존 클래스를 수정해서 새로 구현한 경우 사용되며 상대적으로 더 특수한 다중정의 메서드에서 더 일반적인 다중정의 메서드로 일을 넘기는(forward) 방법이다.
```java
public boolean contentEquals(StringBuffer sb){
    return contentEquals((CharSequence) sb);
}
```

## 4. 람다와 메서드 참조에서의 다중정의 혼란
```java
// ExecutorService의 submit 메서드 호출
ExecutorService exec = Executors.newCachedThreadPool();
exec.submit(System.out::println); // 컴파일 오류
```
> submit과 println 모두 다중정의되어 있어서 다중정의 해소 알고리즘이 동작하지 않는 상황이다.
> 부정확한 메서드 참조나 암시적 타입 람다식 같은 인수 표현식은 목표 타입이 선택되기 전에는 의미가 정해지지 않기 때문이다.

### 서로 다른 함수형 인터페이스이라도 같은 위치의 인수로 받으면 안된다.
다른 함수형 인터페이스라도 서로 근본적으로 다르지 않다는 의미이다.   
컴파일할 때 명령줄 스위치로 -Xlint:overloads를 지정하면 다중정의를 경고해준다.

### 💡마무리
>다중정의를 허용한다고 해서 다중정의를 꼭 활용하라는 의미는 아니다.   
>이번 아이템의 정신을 실패한 클래스도 있다. String 클래스의 valueOf(char[])과 valueOf(Object)는 같은 객체를 넘기더라도 전혀 다른 일을 수행한다.

