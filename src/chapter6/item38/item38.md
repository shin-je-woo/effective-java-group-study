# 💡 확장할 수 있는 열거 타입이 필요하면 인터페이스를 사용하라

## ✔ 열거 타입의 확장성
열거 타입을 확장시킨다면 확정한 타입 원소 -> 기반 타입을 알 수 있지만 그 반대의 경우는 알 수 없고,    
두 타입의 모든 원소를 순회할 명확한 방법이 없어서 확장할 수 없게 설계된 것이다.   
_확장할 수 있는 열거 타입이 필요한 경우가 있는데 이때는 열거 타입이 인터페이스를 구현할 수 있는 사실을 이용하면 된다._

### 인터페이스를 구현한 열거 타입 예시코드
```java
public enum BasicOperation implements Operation{
    PLUS("+") {
        @Override public double apply(double x, double y) { return x+y; }
    },
    MINUS("-") {
        @Override public double apply(double x, double y) { return x-y; }
    },
    TIMES("*") {
        @Override public double apply(double x, double y) { return x*y; }
    },
    DIVIDE("/") {
        @Override public double apply(double x, double y) { return x/y; }
    };

    private final String symbol;
    BasicOperation(String symbol){
        this.symbol = symbol;
    }
```
> 열거 타입인 BasicOperation은 확장할 수 없지만 인터페이스 Operation를 구현하며
> 또 다른 열거 타입을 정의해야 한다면 인터페이스 Operation를 구현하는 열거 타입을 만들면 된다.

## ✔ 확장된 열거 타입 응용
- 확장된 열거 타입의 원소 모두를 사용할 수 있다.

### 확장된 열거 타입 예시코드 1 - Class 객체
```java
public static void main(String[] args) {
        double x = 2.3;
        double y = 1.2;
        
        // class 리터널을 넘겨 확장된 연산들을 알려준다.
        test(ExtendedOperation.class, x, y);
        }

private static <T extends Enum<T> & Operation> void test(
        Class<T> opEnumType, double x, double y) {
        for(Operation op : opEnumType.getEnumConstants()) // 모든 상수를 배열로 return
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }
```
> 제네릭 타입은 열거 타입이면서 'Operation' 인터페이스를 구현한 타입이어야 함을 의미한다.
> 인터페이스 제약을 추하여서 T는 Operation 인터페이스의 기능을 사용할 수 있다.


### 확장된 열거 타입 예시코드 2 - 한정적 와일드카드 타입
```java
public static void main(String[] args) {
        test(Arrays.asList(ExtendedOperation.values()), x, y);

private static void test(
        Collection<? extends Operation> operations, double x, double y) {
        for(Operation op : operations)
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }
```
> Operation 인터페이스를 구현한 어떤 컬렉션이든 전달 받을 수 있어 유연성이 좋아진다.

## ✔ 확장된 열거 타입의 한계
- 열거 타입끼리 구현을 상속할 수 없다. 상태값에 의존하지 않는 경우라면 인터페이스에 디폴트 메서드를 선언하는 방법이 있다.
- 공유하는 기능이 많아 코드 중복이 많으면 도우미 클래스나 정적 도우미 메서드로 분리하는 방식을 쓰자.

### 💡마무리
> 인터페이스를 구현하는 기본 열거 타입을 함께 사용해 확장의 효과를 낼 수 있다.   
> API가 인터페이스 기반으로 작성되었다면 새롭게 확장한 열거 타입은 기존의 기본 열거 타입과 같은 인터페이스를 구현하므로,   
> 기존의 코드를 수정하지 않고도 사용할 수 있으며 유지 보수성과 확장성을 높여준다.   
> - 자바 라이브러리에서 해당 패턴을 사용한 코드 : public enum LinkOption implements OpenOption, CopyOption