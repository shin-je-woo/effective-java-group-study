# 💡 equals 메서드를 재정의할 때 반드시 따라야 할 일반 규약 5가지
**null이 아닌 모든 참조 값 x, y, z에 대해**
### 반사성(reflexivity)
* x.equals(x) == true
### 대칭성(symmetry)
* x.equals(y) == true
* y.equals(x) == true
### 추이성(transitivity)
* x.equals(y) == true
* y.equals(z) == true
* x.equals(z) == true
### 일관성(consistency)
* x.equals(y)를 반복호출하면 항상 true거나 항상 false이다.
### null-아님(non-null)
* x.equals(null) == false

## 어렵다고 규약을 어기면 안된다!
*컬렉션을 포함해 수많은 클래스는 전달받은 객체가 equals 규약을 지킨다고 가정하고 동작한다.*  
이제부터 위 5가지 규약을 하나씩 살펴보자.

# 💡 1. 반사성(reflexivity)
* 객체는 자기자신과 같아야 한다.
* 이 요건을 일부러 어기는 경우가 아니라면 만족하지 못하기가 어렵다.

# 💡 2. 대칭성(symmetry)
* 서로에 대한 동치 여부가 동일해야 한다는 뜻
```java
public class CaseString {
    private final String str;

    public CaseString(String str) {
        this.str = Objects.requireNonNull(str);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof CaseString) {
            return str.equalsIgnoreCase(((CaseString) o).str);
        }
        if (o instanceof String) {
            return str.equalsIgnoreCase((String) o);
        }
        return false;
    }
}
```
```java
public static void main(String[] args) {
    CaseString caseString = new CaseString("Jewoo");
    String string = "jewoo";

    System.out.println(caseString.equals(string)); // true
    System.out.println(string.equals(caseString)); // false
    // CaseString은 대소문자를 무시하지만, String은 대소문자까지 비교한다.
    // 따라서, 대칭성 위반이다.
}
```
* 예제로 작성한 CasaeString클래스는 equals()를 String필드의 대소문자를 무시하여 비교합니다.
* String클래스의 equals()는 값 그자체(대소문자 구분)를 비교하기 때문에 x -> y 면 y -> x가 성립하지 않습니다.
* 대칭성을 확보하려면 CaseString이 String과 equals 비교한다는 허황된 꿈(필자의 표현)을 버려야 합니다.
```java
@Override public boolean equals(Object o) {
    return o instanceof CaseString && ((CaseString) o).str.equalsIgnoreCase(str);
}
```
