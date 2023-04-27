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

# 💡 3. 추이성(transitivity)
* 좌표(x, y)를 나타내는 클래스를 예로 들어보자.
```java
public class Point {

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point)) return false;
        Point p = (Point) o;
        return p.x == x && p.y == y;
    }
}
```
* 이제 이 클래스를 확장해서 색상을 더해보자.
```java
public class ColorPoint extends Point {

    private final Color color;

    public ColorPoint(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        // 관련 없는 클래스와 비교하면 false
        if (!(o instanceof Point)) return false;

        // o가 일반 Point이면 Point의 equals로 비교한다.(색상을 무시한다.)
        if (!(o instanceof ColorPoint)) {
            return o.equals(this);
        }
        
        return super.equals(o) && ((ColorPoint) o).color == color;
    }
}
```
* 아래 코드는 위 2개의 클래스를 equals 비교했을 때 나타나는 추이성 위반 문제이다.
```java
public static void main(String[] args) {
    ColorPoint redPoint = new ColorPoint(5, 5, Color.RED);
    Point normalPoint = new Point(5, 5);
    ColorPoint greenPoint = new ColorPoint(5, 5, Color.GREEN);

    System.out.println(redPoint.equals(normalPoint)); // true
    System.out.println(normalPoint.equals(greenPoint)); // true
    System.out.println(redPoint.equals(greenPoint)); // false
}
```

## 추이성 - 리스코프 치환 원칙
* Point클래스의 equals를 아래와 같이 재정의 해보자.
```java
@Override
public boolean equals(Object o) {
    if (o == null | o.getClass() != this.getClass()) return false;

    Point p = (Point) o;
    return p.x == x && p.y == y;
}
```
* instancof검사를 getClass검사로 바꾸게 되면 코드는 모두 동작하겠지만, ColorPoint와 Point는 사실상 비교할 수 없게 된다.
* Point의 하위타입인 ColorPoint는 Point의 equals기능을 상실했다고 보아야 한다.
* *구체클래스를 확장해 새로운 값을 추가하면서 equals 규약을 만족시킬 방법은 존재하지 않는다.*

✅ 리스코프 치환 원칙(LSP)  
* 어떤 타입에 있어 중요한 속성이라면 그 하위 타입에서도 마찬가지로 중요하다.    
* 따라서, 그 타입의 모든 메서드가 하위 타입에서도 똑같이 잘 작동해야 한다.
* Point의 하위 클래스는 정의상 여전히 Point이므로 어디서든 Point로써 활용될 수 있어야 한다.

# 💡 4. 일관성(consistency)
* 두 객체가 같다면 앞으로도 영원히 같아야 한다.
* 클래스가 불변이든 가변이든 equals의 판단에 신뢰할 수 없는 자원이 끼어들게 해서는 안된다.
* 아래 URL의 equals 예제를 보자.
```java
public final class URL implements java.io.Serializable {
    ...
    public boolean equals(Object obj) {
        if (!(obj instanceof URL))
            return false;
        URL u2 = (URL)obj;

        return handler.equals(this, u2);
    }
    ...
```
```java
public abstract class URLStreamHandler {
    ...
    protected boolean equals(URL u1, URL u2) {
        String ref1 = u1.getRef();
        String ref2 = u2.getRef();
        return (ref1 == ref2 || (ref1 != null && ref1.equals(ref2))) &&
               sameFile(u1, u2);
    }
    ...
    protected boolean sameFile(URL u1, URL u2) {
        // Compare the protocols.
        if (!((u1.getProtocol() == u2.getProtocol()) ||
              (u1.getProtocol() != null &&
               u1.getProtocol().equalsIgnoreCase(u2.getProtocol()))))
            return false;

        // Compare the files.
        if (!(u1.getFile() == u2.getFile() ||
              (u1.getFile() != null && u1.getFile().equals(u2.getFile()))))
            return false;

        // Compare the ports.
        int port1, port2;
        port1 = (u1.getPort() != -1) ? u1.getPort() : u1.handler.getDefaultPort();
        port2 = (u2.getPort() != -1) ? u2.getPort() : u2.handler.getDefaultPort();
        if (port1 != port2)
            return false;

        // Compare the hosts.
        if (!hostsEqual(u1, u2))
            return false;

        return true;
    }
    ...
    protected boolean hostsEqual(URL u1, URL u2) {
        InetAddress a1 = getHostAddress(u1);
        InetAddress a2 = getHostAddress(u2);
        // if we have internet address for both, compare them
        if (a1 != null && a2 != null) {
            return a1.equals(a2);
        // else, if both have host names, compare them
        } else if (u1.getHost() != null && u2.getHost() != null)
            return u1.getHost().equalsIgnoreCase(u2.getHost());
         else
            return u1.getHost() == null && u2.getHost() == null;
    }
}
```
* java.net.URL 클래스는 URL과 매핑된 host의 IP주소를 이용해 비교한다.
* 이 때 호스트이름을 IP주소로 바꾸기 위해 네트워크를 통하게 되는데, 그 결과를 항상 보장할 수 없게 된다.
* 이러한 문제를 피하기 위해 equals는 항시 메모리에 존재하는 객체만을 사용한 결정적 계산만을 수행해야 한다는 것을 알 수 있다.

# 💡 null-아님(non-null)
* 이 부분은 쉽게 지킬수 있는데, `NullPointerException`이 발생하는 상황만 조심하자.
* instanceof 연산자로 입력 매개변수가 올바른 타입인지 검사하면 이 경우를 피할 수 있는데, 이를 `묵시적 null검사`라고 한다.
```java
@Override
public boolean equals(Object o) {
    // 인텔리제이가 생성하는 방식
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Point point = (Point) o;
    return x == point.x && y == point.y;

    // 이펙티브 자바의 추천 방식 - null검사할 필요 없이 instanceof연산자로 묵시적 null검사를 할 것
    if (!(o instanceof Point)) return false;
    Point p = (Point) o;
    return p.x == x && p.y == y;
}
```
* instanceof 연산자는 첫 번째 피연산자가 null이면 false를 반환하기 때문에 null검사를 명시적으로 하지 않아도 된다.
