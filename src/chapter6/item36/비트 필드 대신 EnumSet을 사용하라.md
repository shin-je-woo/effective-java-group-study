# 💡 비트 필드 대신 EnumSet을 사용하라

## ✔ 비트 필드의 문제점
비트 필드: 비트별 OR를 사용해 여러 상수를 하나의 집합으로 모은 것
- 비트 필드 값이 그대로 출력되면 해석하기 어렵다.
- 모든 원소를 순회하기 까다롭다.
- 최대 몇 비트가 필요한지 API 작성 시 예측하여 적절한 타입을 선택해야 한다.

### 비트 필드 예시코드
```java
public class Licenses {
    public static final int PC          = 1 << 0; // 1
    public static final int MOBILE      = 1 << 1; // 2
    public static final int MSG         = 1 << 2; // 4
    public static final int MOBILE_MSG  = 1 << 3; // 8
    public static final int MAIL        = 1 << 4; // 16
    
    int flags = 0;
    public void applyLicense(int licenses){
        this.flags = licenses;
        if((flags & PC) != 0) System.out.println("PC");
        if((flags & MSG) != 0) System.out.println("MSG");
    }

    public static void main(String[] args) {
        Licenses license = new Licenses();
        license.applyLicense(PC | MSG);
    }
```

## ✔ 비트 필드의 대안인 EnumSet
열거 타입 상수의 값으로 구성된 집합
- Set 인터페이스를 구현하여 타입 안전하고 다른 Set 구현체와 사용할 수 있다.
- 내부는 비트 벡터로 구현되어 원소가 총 64개 이하라면, EnumSet 전체를 long 변수 하나로 표현하여 비트 필드와 성능이 비슷하다.
- 산술 연산 등 구현되어 있어 비트를 직접 다룰 때 겪는 오류를 줄여준다.

### EnumSet 예시코드
```java
public class License {
    public enum Type {PC, MOBILE, MSG, MOBILE_MSG, MAIL}

    public void applyLicense(Set<Type> licenses){
        System.out.println(licenses);
    }

    public static void main(String[] args) {
        License license = new License();
        license.applyLicense(EnumSet.of(Type.MOBILE, Type.MOBILE_MSG));
    }
```
> 열거 타입의 타입 안전성과 메서드, 생성자, 필드 등에서 사용될 수 있는 편의성 등등의 장점까지 가지게 된다.
> EnumSet의 유일한 담점은 불변 EnumSet을 만들 수 없다는 것이다.
> Collections.unmodifiableSet으로 감싸는 대안이 있다.


### 💡마무리
> 비트 필드 대신 Enum 열거 타입의 장점과 Set 인터페이스의 장점을 가진 EnumSet을 사용하자.