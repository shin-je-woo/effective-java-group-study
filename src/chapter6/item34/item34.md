# 💡 아이템 34 int 상수 대신 열거 타입을 사용해라

## 개요
* 이번 아이템에서는 열거타입에 대해서 배우게 됩니다.
* 열거 타입에 대해서 자세히 배우기전 간단히 상수,  열거 패턴, 열거 타입에 대해서 하나씩 이해해봅시다.


	##
### ✅ 상수
* 상수(常數, constant)란 수식에서 변하지 않는 값을 뜻한다. 이것은 변하는 값 변수와 반대이다.
  
  ##
### ✅ 열거 패턴 (int enum pattern)
- 자바에서 열거 타입이라고 하면 일정 개수의 상수값을 아래와 같이 정의한 다음 그 외의 값은 허용하지 않는 타입입니다.
- 열거 패턴을 사용할 때는 상수를 public static final로 선언하고, 해당 상수에 대한 의미를 주석으로 나타내는 것이 일반적입니다. 이렇게 정의된 정수 상수들은 서로 다른 값을 가지며, 각각의 값은 특정한 의미를 나타냅니다.    
```java
public static final int APPLE_FUJI = 0;
public static final int APPLE_PIPPIN = 1;

public static final int ORANGE_NAVEL = 0;
public static final int ORANGE_TEMPLE = 1;
```
열거 패턴 기법에는 단점이 많습니다.
- 타입 안전성을 보장 받지 못한다.
- 이름 충돌 방지를 위해 접두어를 이용한다.
- 가독성 및 유지보수가 어렵다.

책에서는 상수의 의미를 출력할 수 있어서 좋을 수 있지만 반대로 출력되는 상수의 의미때문에 문제가 생길 수 있다고 합니다.  

예를들어 **APPLE_FUJI 과 ORANGE_NAVEL 가 의미하는 상수값은 둘다 0** 이기때문에,   
오렌지가 들어가야하는 문제에서, 사과가 들어가게되는 문제와 같이 코드의 의미를 모르는 사람이 잘못 활용할 수 있습니다.

    
##
  ### ✅ 열거 타입 (enum type)
* 자바에서 열거 타입(Enumeration Type)은 서로 연관된 상수들의 집합을 정의하는 특별한 데이터 타입입니다. **열거 타입은 관련된 상수들을 그룹화하고, 해당 상수들에 대한 타입 안전성(type safety)을 제공**합니다.
* 열거 타입은 enum 키워드를 사용하여 정의하며, 열거 상수들은 타입의 인스턴스로서 존재합니다. 
* 열거 상수들은 대문자로 작성되며, 필요에 따라 소문자로 구분되는 이름을 가질 수 있습니다.


```java
enum Day {
    MONDAY, TUESDAY, WEDNESDAY...}
```
* enum 으로 만들어지는 열거 타입 자체는 클래스이며, 상수 하나당 인스턴스를 만들어 public static final 필드로 공개 됩니다.


##
  ### ✅ 열거 타입 (enum type)

```java
public enum Planet {
    MERCURY(3.302e+23, 2.439e6),
    VENUS(4.869e+24, 6.052e6),
    EARTH(5.975e+24, 6.378e6);

    private final double mass;          // 질량
    private final double raduis;        // 반지름
    private final double surfaceGravity; // 표면중력

    private static final double G = 6.67300E-11;


    Planet(double mass, double raduis) {
        this.mass = mass;
        this.raduis = raduis;
        this.surfaceGravity = G * mass / (raduis * raduis);
    }


    public double surfaceGravity() {
        return surfaceGravity;
    }

    public double surfaceWeight(double mass) {
        return mass * surfaceGravity; // F = ma
    }
}
```  
**열거 타입을 이용한 메소드, 필드 추가 예제입니다**

각 **행성에는 질량과 반지름**이 있고, 이 **두 속성과 메소드를 활용해 표면중력**을 계산할 수 있습니다.  
그렇기 때문에 **열거타입으로 각 객체(행성)의 질량을 정해주면 계산이 가능**하게 됩니다.  
열거 타입 상수 각각을 특정 데이터와 연결 지으려면 **생성자를 활용해 매개변수로 각 객체의 질량과 반지름을 넘기는 방식**이다.  
열거타입은 근본 적으로 불변이기 때문에 모든 필드를 final로 지정하되, 접근자 메소드를 활용해 처리 가능하도록 합니다.  

### 계산 방식
* 생성자에서는 전달받은 질량과 반지름을 이용해 private static final 열거 패턴 (int enum pattern) 을 활용해 지정하고, 표면중력을 구하는 것입니다.


##
### ✅ 값에 따라 분기처리하는 열거 타입
```java
public enum Operation {
    PLUS, MINUS, TIMES, DIVIDE;

    public double apply(double x, double y) {
        switch (this) {
            case PLUS:
                return x + y;
            case MINUS:
                return x - y;
            case TIMES:
                return x * y;
            case DIVIDE:
                return x / y;
        }
        throw new AssertionError("알 수 없는 연산: " + this);
    }
}
```

##
### ✅ 상수별 메소드 구현을 활용한 열거 타입
```java
public enum OperationUpgrade {
    PLUS    {	public double apply(double x, double y){
	    		return x + y;
	    		}
    		},
    MINUS   {public double apply(double x, double y){return x - y;}},
    TIMES   {public double apply(double x, double y){return x * y;}},
    DIVIDE  {public double apply(double x, double y){return x / y;}};
    
    public abstract double apply(double x, double y);
}
```

* "Operation"은 switch 문을 사용하여 상수별 동작을 구현하고, 

* "OperationUpgrade"는 각 상수에 대한 메소드를 개별적으로 선언하여 동작을 구현합니다. 
*  **가독성과 확장성 면에서 좀 더 유리하며, 각 연산에 대한 독립성과 유연성을 제공**합니다.


##
### ✅ enum 에서 제공하는 메소드 활용 법
```java
enum Color {
    RED,
    GREEN,
    BLUE
}

public class EnumClass {
	public static void main (String [] args) {
    	/*
    	 * values(): 이 메서드는 열거 타입의 모든 열거 상수를 배열로 반환합니다. 
    	 * 열거 상수들은 열거 타입에 선언된 순서대로 배열에 저장됩니다.
    	 */
		Color [] color = Color.values();
    	System.out.println(Arrays.toString(color));
    	
    	/*
    	 * valueOf(String name): 이 메서드는 주어진 이름에 해당하는 열거 상수를 반환합니다.
    	 * 주어진 이름과 일치하는 상수가 없을 경우 IllegalArgumentException이 발생합니다.
    	 */
    	Color valOf = Color.valueOf("RED");
    	System.out.println(valOf);
    	
    	/*
    	 * name(): 이 메서드는 열거 상수의 이름을 문자열로 반환합니다.
    	 */
    	Color name = Color.BLUE;
    	System.out.println(name.name());
    	
    	/*
    	 * ordinal(): 이 메서드는 열거 상수의 순서(0부터 시작하는 인덱스)를 반환합니다.
    	 */
    	Color ordinal = Color.GREEN;
    	System.out.println(ordinal.ordinal());
    	
    }
}
```
* 이 외에도 열거 타입은 여러 가지 유용한 메서드와 기능을 제공합니다. 열거 타입은 자바에서 강력하고 유연한 도구로 사용될 수 있으며, 이를 통해 코드의 가독성과 안정성을 향상시킬 수 있습니다.


## 정리
- 열거 타입은 필요한 원소를 컴파일타입에 다 알 수 있는 상수 집합이라면 사용하자.
- 열거 타입에 정의된 상수 개수가 영원히 고정불변일 필요는 없다.
- 열거 타입은 확실히 정수 상수보다 뛰어나며, 더 읽기 안전하고 강력하다.
