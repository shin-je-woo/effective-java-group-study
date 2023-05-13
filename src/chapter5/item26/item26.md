# 💡 아이템26 로(raw) 타입은 사용하지 말라

## 개요
* 이번 아이템은 자바5부터 사용이 가능한 제네릭에 대해서 배우게 됩니다. 제네릭의 장점과 단점을 최소화하는 방법을 천천히 알아봅시다.
* 간단한 용어 정리부터 하자면 클래스와 인터페이스 선언에 타입 매개변수가 쓰이면 그것을 바로 제네릭 클래스 혹은 제네릭 인터페이스라 한다.
* List로 예를 들면, List 인터페이스는 원소의 타입을 나타내는 타입 매개변수 <E>를 받는다 하자. 그래서 이 인터페이스 List<E>이지만 우리는 List라고 자연스럽게 쓴다. 이것을 제네릭 클래스와, 인터페이스를 제네릭 타입 이라고 부른다.

	##
### ✅ 제네릭 타입
* 제네릭 타입은 매개변수 타입을 정의한다.  List와 같이 인터페이스 혹은 클래스가 나오고, 꺽쇠 <>를 이용해 실제 타입매개 변수를 나열한다.
ex ) List<String> -> 원소타입이 String인 리스트를 뜻하는 매개변수화 타입.  
  
##
### ✅ 로 타입의 예시
- 타이틀에서 제시하는 로 타입이라는 것은 제네릭 타입에서 타입 매개변수를 전혀 사용하지 않는 것을 칭한다. List와 같이 말이다.   
- 로 타입은 제네릭을 사용하되 매개변수를 사용하지 않기때문에 안전성을 보장 받지 못한다.  
- 사실 로 타입을 사용하게되면 제네릭의 사용의미가 없어진다. 이러한 단점에도 사용했던 이유는 제네릭이 도래하기 전 코드와 호환이 하기 위한 궁여지책이라고 책에 쓰여있다.  
    
```java
public class Box {
    private Object contents;

    public void setContents(Object contents) {
        this.contents = contents;
    }

    public Object getContents() {
        return contents;
    }
    
    public static void main(String [] args) {
    
    	Box box = new Box();  // 로 타입 사용
    	box.setContents("Hello");  // String 인자로 전달
    	Integer i = (Integer) box.getContents();  // 런타임 에러 발생
    	
    }
}
```  
* 간단하게 Box 클래스에 로타입을 이용한 제네릭을 만들었다.
* Box 클래스는 타입 매개변수를 사용하지 않고, Object 타입을 사용하여 필드와 메소드의 매개변수 및 반환값을 처리하고 있으므로 로 타입의 예시이다.
* 자바빈즈 패턴을 활용하여 자유롭게 클래스의 객체를 컨트롤 할 수 있다고 생각해보자.

 ![image](https://github.com/shin-je-woo/effective-java-group-study/assets/91134556/99b0c3c5-77c5-47f3-985f-24158ab62a36)  
실제로 위 이미지에서 제시하고 있는 것 처럼 로타입으로 선언했기 때문에, setContents()에는 어떠한 Object가 들어가도 상관이 없다.
  
코드가 억지스럽지만 Contents를 String의 인자를 전달하고 객체를 getter 할 때 Integer를 이용해 형변환한다고 생각해보자. 
  ![image](https://github.com/shin-je-woo/effective-java-group-study/assets/91134556/2ea73671-b792-439e-934c-e8f61cc1af0e)  

  * 코드는 신기하게도 컴파일에도 이상이없다. 다만 이러한 경우는 당연하지만 String을 Integer로 강제 형변환하지 못한다. 이는 런타임 시에 ClassCastException을 발생시키게 된다.
  * 오류는 발생한 즉시 이상적으로는 컴파일 할 때 발견하는 것이 좋다. 위 예시에서는 오류가 발생하고 런타임 시에만 알아챌 수 있기 때문에, 좋지 않은 코드이다.
  
  ```java
  public class Box {
    private String contents;

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getContents() {
        return contents;
    }
}
  ```
**그렇기 때문에 이렇게, 제네릭의 매개변수 타입을 확실하게 지정해줘야 아! 해당 제네릭은 String만 넣어야되는구나, 컴파일러가 인지할 수 있다.**
  
  
##
  ### ✅ List 로타입 vs List<Object> 
  ### 
```java
public class RawType {
	public static void main(String[] args) {
	    List<String> str = new ArrayList<>();
	    
	    unsafeAdd(str, 42);
	    
	    // 컴파일은 정상작동하지만, ClassCastException이 발생
        // List<String>에 추가된  Integer 객체를 String으로 형변환하려고 시도할 때 발생
	    String s = str.get(0);	// 컴파일러가 자동으로 형변환 코드를 넣어줌
	}

	// raw type인 List 이용
	private static void unsafeAdd(List list, Object o) {
	    list.add(o);
	}
}
```
  
* 책에서 제시한 로 타입인 List를 사용하여 컴파일 시점에 타입 안정성을 보장하지 않는 예제이다.
* unsafeAdd 메소드는 매개변수로 List list와 Object o를 받는데, 이때 List는 로 타입으로 선언되어 있으므로, 저장된 객체의 타입이 무엇인지 컴파일러가 알 수 없습니다. 이로 인해 모든 타입의 객체를 List에 추가할 수 있습니다.

* 코드에서는 unsafeAdd 메서드를 사용하여 List<String>에 Integer 타입의 객체를 추가하였습니다. 
* 컴파일 시점에서는 오류가 발생하지 않습니다. 그러나 런타임 시점에서 ClassCastException이 발생합니다.

##
### ✅ 로타입 대신 비한정적 와일드 카드 타입을 사용하자.
  - 제네릭 타입을 쓰고 싶지만 실제 타입 매개변수가 무엇인지 신경쓰고 싶지 않다면 물음표를 사용하자.
```java
public class UnboundedWildcardExample {
    public static void main(String[] args) {
        List<?> list = new ArrayList<>();
        list = new ArrayList<String>(); // OK
        list = new ArrayList<Integer>(); // OK
        list = new ArrayList<Object>(); // OK

        //list.add("String"); 
        //list.add(1);  
        //list.add(new Object()); 
        //list.add(null);

        printList(list);
    }

    private static void printList(List<?> list) {
        for (Object o : list) {
            System.out.println(o);
        }
    }
}
```

* List<?>는 모든 타입을 허용하는 것이 맞지만, 컴파일러는 해당 리스트에 어떤 타입의 객체가 저장되어 있는지 알 수 없습니다. 따라서 add() 메소드로 원소를 추가하는 것은 불가능합니다.
* 실제로 위 코드에서는 null을 제외하고 모든 타입을 넣을 수 없습니다. 컴파일러는 제 역할을 한 것이고, 컬렉션의 타입 불변식을 훼손하지 못하게 막는 것이다.

##
### ✅ 로 타입을 써야 하는 경우
### 💡 1.class 리터럴을 사용할 때.
- 자바 명세는 class 리터럴에 제네릭 별로 변수화 하는 것을 허용하지 않았습니다. (배열과 기본 타입은 허용)

```java
List<String> stringList = new ArrayList<>();
Class<? extends List> clazz = stringList.getClass();
```

* stringList.getClass()를 호출하면 List<String>의 Class 객체를 반환합니다. 
* 하지만 이 Class 객체는 List<String>에 대한 것이며, List<Integer>나 List<Object>와 같은 다른 매개변수화 타입의 Class 객체를 나타낼 수 없습니다. 이 경우에는 로 타입인 List.class를 사용해야 합니다.

![image](https://github.com/shin-je-woo/effective-java-group-study/assets/91134556/abc6716e-a4a0-4424-8ace-7a48e90a3d7f)



### 💡 2.instanceof 연산자를 사용할 때.
* 런타임에는 제네릭 타입 정보가 지워지므로, 비한정적 와일드 카드(?)를 제외하고는 instanceof 연산자를 사용할 수 없다.  
* 또한 instanceof의 맥락에선 로 타입과 비 한정적 와일드 카드가 동일하게 동작하므로, 코드가 깔끔해진다는 측면에서 차라리 안쓰는 게 낫다.
```java
public static boolean isList(Object obj) {
    return obj instanceof List;
}
```

## 정리
* 로 타입을 사용하면 런타임 예외가 날 수 있으니 사용을 자제하자.
* 로 타입은 제네릭이 도입되기 전 코드와의 호환성을 위해 제공된 것이다.
* 그럼에도 사용하고 싶다면 비한정 와일드 카드 타입인 <?>를 사용하자. 다만 위 예시 처럼 <?>를 사용한다면 한정적인 것들이 많으니,  매개변수를 확실히 정하여 용도에 맞게 활용하자.
