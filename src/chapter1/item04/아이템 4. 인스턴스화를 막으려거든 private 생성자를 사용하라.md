# 💡 private 생성자로 인스턴스화 막기
### private 생성자를 사용하는 경우
* 정적 유틸리티(단순히 정적 메서드와 정적 필드만 담은 클래스)를 만들고 싶을 때   
java.lang.Math, java.util.Arrays, java.util.Collections 와 같은 것들이 있다.
* 상속을 불가하게 하고 싶을 때   
모든 생성자는 명시적이든 묵시적이든 상위 클래스의 생성자를 호출하게 되는데,   
이를 private으로 선언했으니 하위 클래스가 상위 클래스의 생성자에 접근할 수 없다.

### 인스턴스화를 막으려면 private 생성자를 사용해야 하는 이유
* 생성자를 아예 명시하지 않으면 컴파일러가 기본 public 생성자를 만들어준다.
즉, 매개변수를 받지 않는 public 생성자가 만들어지며, 사용자는 코드만 보고 이 생성자가 자동 생성된 것인지 구분할 수 없다.
* 추상 클래스로 만든다고해도 하위 클래스로 인스턴스화를 할 수 있기에 막을 수 없다. 

### 인스턴스화 막는 방법
1. private 생성자를 추가한다.
2. 클래스 내부에서 실수로 생성자를 호출하는 것을 방지하기 위해 AssertionError를 throw 코드를 추가한다.
3. private 생성자가 존재하는데 호출할 수가 없는 형태이므로, 다른 개발자들이 코드를 볼 때
의문을 가질 수 있다. 그러므로 주석도 같이 달아놓는다.

```java
public class DateUtil {
    // 인스턴스화 방지
    private DateUtil() {
        throw new AssertionError();
    }

    private static Date now = new Date();
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd 'at' HH:mm:ss z");

    public static String today(){
        return formatter.format(now);
    }
}