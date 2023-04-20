# 💡 정적 팩터리 메서드 장점
### 장점1. 이름을 가질 수 있다.  
* 생성자 자체는 생성되는 객체의 특성을 직관적으로 설명할 수 없다.  
* 메서드의 시그니처가 같다면(= name, parameter types가 같다.) 제약이 있다.

### 장점2. 호출될 때마다 인스턴스를 새로 생성하지는 않아도 된다.   
* `불변 클래스`는 인스턴스를 미리 만들어 두고 캐싱하여 재활용할 수 있다.  
* 같은 객체가 자주 생성되는 상황이라면 성능향상을 기대할 수 있다. (ex. 플라이웨이트 패턴)

✅ Flyweight 패턴이란?
* 공통으로 사용하는 클래스(Flyweight)를 생성하는 팩토리 클래스(FlyweightFactory)를 만들어, 인스턴스를 최초 1개만 생성하고 공유하여 재사용할 수 있도록 하는 구조 패턴이다.
* 자주 변하는 속성(= 외적인 속성, extrinsit)과 변하지 않는 속성(= 내적인 속성, intrinsit)을 분리하고 재사용하여 메모리를 효율적으로 사용한다.

![image](https://user-images.githubusercontent.com/39439576/233398283-6d91eb83-763b-4d52-b74d-d62c245f8e5d.png)

```java
/**
 * Flyweight 클래스
 * 불변클래스로 구현
 */
public final class Font {
    // Flyweight 클래스의 필드는 공유가 필요한 값들(공통 관심사)
    private final String fontFamily;
    private final int fontSize;

    public Font(String fontFamily, int fontSize) {
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
    }
}

```

```java
public class FontFactory {

    private final static Map<String, Font> fontStore = new HashMap<>();

    public static Font of(String font) {
        if (fontStore.containsKey(font)) {
            return fontStore.get(font);
        } else {
            String[] split = font.split(":");
            Font newFont = new Font(split[0], Integer.parseInt(split[1]));
            fontStore.put(font, newFont);
            System.out.println("새로운 Font가 생성되었습니다.");
            return newFont;
        }
    }
}
```
```java
public class Character {

    private char value;
    private String color;
    private Font font;

    public Character(char value, String color, Font font) {
        this.value = value;
        this.color = color;
        this.font = font;
    }
}
```
```java
public class Client {

    public static void main(String[] args) {
        Character c1 = new Character('h', "red", FontFactory.of("Nanum:12"));
        Character c2 = new Character('e', "blue", FontFactory.of("Nanum:12"));
        Character c3 = new Character('l', "yellow", FontFactory.of("Nanum:12"));
        Character c4 = new Character('l', "green", FontFactory.of("Nanum:12"));
        Character c5 = new Character('o', "white", FontFactory.of("Nanum:12"));
    }
}

```
* 위 예제에서는 value, color를 자주 변하는 값으로, fontFamily와 fontSize를 자주 변하지 않는 값으로 간주하였다.
* Nanum체, 12사이즈는 딱 한번 생성하고 모든 Character에서 공유하고 있다.
* FontFactory에서 정적팩토리 메서드 of()를 통해 불변한 Flyweight객체를 재사용할 수 있다.

### 장점3. 반환 타입의 하위 타입 객체 반환 가능
```java
public interface Payment {

    void pay();

    static Payment accessPayment(String phone) {
        if ("galaxy".equals(phone)) {
            return new SamsungPay();
        } else if ("iphone".equals(phone)) {
            return new ApplePay();
        } else {
            throw new IllegalStateException("올바르지 않은 기종입니다.");
        }
    }
}
```
```java
public class SamsungPay implements Payment {

    @Override
    public void pay() {
        System.out.println("삼성페이로 결제합니다.");
    }
}
```
```java
public class ApplePay implements Payment {

    @Override
    public void pay() {
        System.out.println("애플페이로 결제합니다.");
    }
}
```
```java
public static void main(String[] args) {

    // 추상화에 의존할 수 있는 장점을 가진다.
    Payment galaxy = Payment.accessPayment("galaxy");
    galaxy.pay();

    Payment iphone = Payment.accessPayment("iphone");
    iphone.pay();

    // 개발자는 구체화에 의존하지 않고 추상화된 개념에 의존할 수 있다. SOLID - DIP
}
```
* Payment인터페이스의 정적팩토리 메서드 accessPayment(String)을 통해 결제수단(하위타입 객체)을 반환한다.
* 이를 통해 클라이언트 입장에서는 휴대폰 기종 구분 없이 Payment(추상화)만 가지고 결제를 완료할 수 있다.
* 이는 객체지향 SOLID의 DIP를 잘 지키는 구조이다.

### 장점4. 입력 매개변수에 따라 다른 클래스의 객체를 반환할 수 있다.
* 장점3과 동일한 내용이다. 정적 팩토리 메서드를 통한 코드유연성을 강조한다.
* `Enumset` 인터페이스가 정적 팩토리 메서드를 통해 하위타입을 반환하는 예제를 보자.
```java
public static <E extends Enum<E>> EnumSet<E> noneOf(Class<E> elementType) {
    Enum<?>[] universe = getUniverse(elementType);
    if (universe == null)
        throw new ClassCastException(elementType + " not an enum");

    if (universe.length <= 64)
        return new RegularEnumSet<>(elementType, universe);
    else
        return new JumboEnumSet<>(elementType, universe);
}
```

### 장점 5. 정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다.
