# 💡 불필요한 객체 생성을 피하라
## 같은 기능의 객체를 재생성 하기 보다는 재사용하는 것이 좋다.
### 1. 안좋은 예시 및 개선안
```
String str1 = new String("Test");
```
* 해당 방식은 실행될 때마다 String 인스턴스를 새로 만든다. 
* 생성자에 넘겨진 "Test"자체가 생성자로 만들어내는 String과 기능적으로 완전히 똑같다.
```
String str1 = "Test";  // 새로운 String 객체 생성
String str2 = "Test";  // 이미 생성된 String 객체를 참조
```
* 개선안으로, 하나의 String 인스턴스를 사용한다. 
* 같은 JVM 안에서 같은 문자열 리터럴을 사용하는 모든 코드가 같은 객체를 재사용함이 보장된다.

### 2. 정적 팩터리 매서드 사용
* 생성자 대신 정적 팩터리 메서드을 제공하는 불변 클래스에서는 불필요한 객체 생성을 피할 수 있다.
* 불변이 아닌 가변객체임에도 데이터가 사용중에 변경되지 않다면 재사용 가능하다.
* Boolean(String) 생성자 대신 Boolean.valueOf(String) 팩터리 메서더를 사용하는 것이 좋다.


### 3. 비싼 객체의 경우 성능 개선
#### 비싼 객체
  * 생성 및 초기화하는 비용이 많거나, 메모리를 많이 차지하는 객체를 의미한다.
  * 이러한 객체들은 반복적으로 생성하는 것이 비효율적으로 필요 시 재사용 하는것이 성능에 도움된다.

#### * 예제 및 개선안
```java
public class RomanNumerals {
      
  static boolean isRomanNumeral(String s) {
          return s.matches("^(?=[MDCLXVI])M*(C[MD]|D?C{0,3})" 
          + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
          }
}
```
* 해당 방식의 문제는 String.matches 메서드를 사용한다.
* 문자열 검사에 가장 쉬운 방법이지만 내부의 Pattern 인스턴스는 일회용으로 사용되어 가비지 컬렉션 대상이 된다.
  * Pattern은 입력받은 정규표현식에 해당하는 유한 상태 머신을 만들기 때문에 인스턴스 생성 비용이 높다.

* 성능 개선을 위해서는 Pattern 인스터르를 직접 생성하고 isRomanNumeral 메서드가 동일한 인스턴스를 사용케 한다.
* 성능 개선 뿐만 아니라 Pattern 인스턴스를 static final 필드로 끄집어내고 이름도 생성하여 코드더 더욱 명확해진다.
```java
 public class RomanNumerals2 {

        private static final Pattern ROMAN = Pattern.compile(
                "^(?=[MDCLXVI])M*(C[MD]|D?C{0,3})"
                + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

        static boolean isRomanNumeral(String s) {
            return ROMAN.matcher(s).matches();
        }
}
```
* 간단한 문자열이지만 5 ms -> 1 ms 단축
```java
public class RomanNumeralsTest {

    public static void main(String[] args) {
        String romanNumeral = "TEST";
        long start1 = System.currentTimeMillis();
        boolean result1 = RomanNumerals.isRomanNumeral(romanNumeral);
        long end1 = System.currentTimeMillis();
        System.out.printf("Result 1: %s, Time elapsed: %d ms\n", result1, end1 - start1);
        // Result 1: false, Time elapsed: 5 ms
      
        long start2 = System.currentTimeMillis();
        boolean result2 = RomanNumerals2.isRomanNumeral(romanNumeral);
        long end2 = System.currentTimeMillis();
        System.out.printf("Result 2: %s, Time elapsed: %d ms\n", result2, end2 - start2);
        // Result 2: false, Time elapsed: 1 ms
    }
}

```
#### * 유한 상태 머신
* 입력값을 받아서 입력값이 원하는 패턴과 일치하는지 판단할 때 사용
* 다음과 같이 아래 정규 표현식을 사용하는 경우 Pattern 클래스는 다음과 같은 유한 상태 머신을 생성.
```
String pattern = "a*b";
```

* "a"를 0번 이상 반복하고, "b"로 끝나는 문자열을 찾기 위한 것
* 패턴이 복잡할 때 객체가 반복 생성될 경우 머신의 생성과 검사 과정이 '비싸다' 라는것을 의미한다.
```
          ┌───── a ─────> [State 1]
          │      │
[Start]  ─┤      ├─ b ──> [Final State]
          │      │
          └───── ε ─────> [State 2]
```

#### * 지연 초기화
* isRomanNumeral 메서드가 한번도 호출되지 않는다면 ROMAN 필드는 쓸데없이 초기화 된 꼴이다.
* 그래서 isRomanNumeral가 처음 호출될 때 필드를 초기화 할 수 있으나 복잡도에 비해 성능개선이 크지 않아 권하지 않는다.
  * 다중 스레드 체크와 같은 예시가 있음. 아직 초기화 되지 않은 변수를 동시에 참조할 때 메모리 낭비등의 이슈 방지

```java
public class RomanNumerals2 {
  private static Pattern ROMAN;

  static boolean isRomanNumeral(String s) {
    if (ROMAN == null) {
      ROMAN = Pattern.compile("^(?=[MDCLXVI])M*(C[MD]|D?C{0,3})"
              + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    }
    return ROMAN.matcher(s).matches();
  }
}
```

### 4. 불필요한 객체를 만들어내는 예시
#### *  오토박싱
   * 기본 자료형을 해당 자료형의 래퍼 클래스로 자동으로 변환하는 것 (섞어 쓸 때 자동으로 상호변환)
   * 예시에서 Long 타입의 경우 래퍼 클래스이기 때문에 객체 생성/파괴를 Integer.MAX_VALUE 만큼 반복하니 불필요하다.
   * int -> Integer, long -> Long
```java
public class AutoBoxing {
  public static void main(String[] args) {
    long startTime1 = System.currentTimeMillis();
    long result1 = sumUsingLong();
    long endTime1 = System.currentTimeMillis();

    long startTime2 = System.currentTimeMillis();
    long result2 = sumUsinglong();
    long endTime2 = System.currentTimeMillis();

    //Using Long: 2305843008139952128 in 5524ms
    System.out.println("Using Long: " + result1 + " in " + (endTime1 - startTime1) + "ms");

    //Using long: 2305843008139952128 in 725ms
    System.out.println("Using long: " + result2 + " in " + (endTime2 - startTime2) + "ms");
  }

  //불필요한 객체를 만들어내는 예시
  private static Long sumUsingLong() {
    Long sum = 0L; //Integer.MAX_VALUE 만큼 생성됨
    for (long i = 0; i <= Integer.MAX_VALUE; i++) {
      sum += i;
    }
    return sum;
  }

  //기본 자료형으로 변경
  private static long sumUsinglong() {
    long sum = 0L;
    for (long i = 0; i <= Integer.MAX_VALUE; i++) {
      sum += i;
    }
    return sum;
  }
}
```
#### *  어댑터 방식
* 실제 작업은 뒷단 객체에 위임하고, 자신은 제2의 인터페이스 역할을 해주는 객체다.
* 인터페이스를 구현하거나 추상 클래스를 상속받아 새로운 인터페이스를 만들 때 사용되는 디자인 패턴 중 하나
```java
public class AdaptorTest {
    public static void main(String[] args) {
        //Map 인터페이스를 구현한 HashMap 클래스
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "first");
        map.put(2, "second");
        map.put(3, "third");

        //Set이 Map 객체의 어댑터
        Set<Integer> keySet = map.keySet();
        System.out.println(keySet); // [1, 2, 3]

        //HashMap 객체에서 remove() 메서드를 사용하여 key값이 3인 쌍을 제거
        map.remove(3);
        //keySet()을 호출하여 해당 key 값이 제거된 것을 확인
        System.out.println(keySet); // [1, 2]
    }
}
```
* 예를들어, Map 인터페이스의 keySet 메서드는 Map 객체 안의 키 전부를 담은 Set 뷰를 반환한다.
  Map 객체와 그것의 뷰인 Set이 같은 Map 인스턴스를 공유하고 있음을 알 수 있습니다.
* 따라서 keySet이 뷰 객체를 여러 개 만들어도 상관없지만, 굳이 이득은 없다.
 
## 5. '객체 생성은 비싸니 피해야 한다'로 오해하지 말자.
* 요즘 JVM는 잘 최적화 되어 보통 객체 생성/회수는 부담되지 않는 테스크이며 더욱 빠르기도 하다.
* DB커넥션 객체와 같은 경우에는 재사용이 낫지만 일반적으로 자체 객체 풀은 코드를 헷갈리게 하며 성능을 저하시킨다.
* 단순한 코드 형태와 성능에 영향있는 불필요한 객체 생성과 달리 무리한 객체 재사용이 각종 버그 및 보안 구멍에 취약하다.