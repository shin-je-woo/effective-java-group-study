# 💡 명명 패턴보다 애너테이션을 사용하라

## ✔ 명명 패턴이란?
도구나 프레임워크에서 특별히 다뤄야할 프로그램 요소에 딱 구분되는 명명 패턴을 적용해왔다.
예시로 JUnit은 버전 3까지 테스트 메서드 이름을 test로 시작하게 했다.

- 명명 패턴의 단점
1) 오타가 나면 안 된다. - 실패없이 메서드를 무시하고 지나간다.
2) 올바른 요소에서만 사용한다고 보증이 안된다. - 경고 메시지없이 의도한대로 동작되지 않는다.
3) 요소를 매개변수로 전달할 적절한 방법이 없다. - 메서드 이름에 문자열로 추가하는 방법이 있지만 가독성과 유연성이 낮고 [아이템62]  
컴파일러가 문자열이 의미하는 바를 알 수 없다.

## ✔ 애너테이션 (Annontation) 이란?
소스 코드에 메타데이터를 추가하기 위한 요소로 특정 정보를 제공하거나 컴파일러에게 특정 작업을 수행하게 한다.   
런타임에 특정 작업을 수행해야 하기 때문에 컴파일러 수준에서 해석이 되거나 완전히 정적이어야 한다.
동적으로 런타임 중에 바뀌어야하는 것들은 애너테이션에 사용할 수 없다.

### 메타 애너테이션 (기타 애너테이션에 적용되는 애너테이션)
|    애너테이션    |                  설명                  |
|:-----------:|:------------------------------------:|
| @Retention  |     애너테이션이 유지되는 범위를 지정하는데 사용한다.      |
|   @Target   |    애너테이션이 적용 가능한 대상을 지정하는데 사용한다.     |
| @Documented | 애너테이션 정보가 javadoc으로 작성된 문서에 포함되게 한다. |
|  @Inheritd  |       애너테이션 자손 클래스에 상속되도록 한다.        |
| @Repeatable |       애너테이션을 반복해서 적용할 수 있게 한다.       |

### 마커 애너테이션 예시
```java
/**
 * 애너테이션 타입 선언
 * 매개변수 없는 정적 메서드 전용
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
    // 유지되는 범위 : RUNTIME
    // 적용 가능한 대상 : METHOD
}
```
> 이 애너테이션을 사용하면 Test 이름에 오타를 내거나 메서드 선언 외의 요소에 달면 컴파일 오류를 내준다.
```java
/**
 * 마커 애너테이션 처리하는 코드
 */
Class<?> testClass = Class.forName("chapter6.item39.Sample");
        
        for (Method m : testClass.getDeclaredMethods()){
            if (m.isAnnotationPresent(Test.class)){
                tests++;
                try {
                    // Method 동적 실행
                    // 정적 메서드 호출
                    m.invoke(null);
                    passed++;
                } catch (InvocationTargetException wrappedExc){
                    // 리플렉션 API로 호출된 메서드가 예외를 던지면 InvocationTargetException으로 감싼다.
                    Throwable exc = wrappedExc.getCause();
                    System.out.println(m + " 실패 - " + exc);
                } catch (Exception exc){
                    System.out.println("잘못 사용한 @Test: " + m);
                }
            }
        }
```
> 매개변수 없는 정적 메서드 전용이지만 이 제약을 컴파일러가 강제하지 않는 상태이다. 애너테이션 처리기를 직접 구현해야 한다면 javax.annotation.processing API 문서를 참고하면 된다.   
> 제약을 지키지 않은 경우 테스트 도구를 실행할 때 문제가 발생한다.

### 하나의 매개변수를 받는 애너테이션
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionTest {
    // Throwable을 확장한 클래스 : 모든 예외와 오류(Exception extends Throwable)를 수용한다.
    Class<? extends Throwable> value();
}
```
```java
public class Sample2 {
    @ExceptionTest(IndexOutOfBoundsException.class) 
    public static void doublyBad() {
        List<String> list = new ArrayList<>();
        list.addAll(5, null);
    }
```

### 여러 매개변수를 받는 애너테이션
1) 배열을 사용하는 경우
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionTest {
    Class<? extends Throwable>[] value();
}
```
```java
public class Sample2 {
    @ExceptionTest({ IndexOutOfBoundsException.class,
                    NullPointerException.class })
    public static void doublyBad() {
        List<String> list = new ArrayList<>();
        list.addAll(5, null);
    }
```
> 원소를 배열로 지정할 때는 원소들을 {} 중괄호로 감싸고 쉼표로 구분해준다.

2) @Repeatable 애너테이션을 사용하는 경우
```java
/**
 * 반복 가능한 애너테이션
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(ExceptionTest2Container.class)
public @interface ExceptionTest2 {
    Class<? extends Throwable> value();
}

// 컨테이너 애너테이션
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionTest2Container {
    // @Repeatable을 단 애너테이션을 반환하는 역할
    ExceptionTest2[] value();
}
```
```java
@ExceptionTest2(IndexOutOfBoundsException.class)
@ExceptionTest2(NullPointerException.class)
    public static void doublyBad() {
        List<String> list = new ArrayList<>();
        list.addAll(5, null);
    }
```
> 컨테이너 애너테이션은 반복 가능한 애너테이션을 지원하기 위한 기능으로 Java 8 이상에서 도입되었다.   
> 이를 통해 동일한 애너테이션을 반복해서 사용할 수 있으며, 코드의 가독성과 유지 보수성을 향상시킬 수 있다.   
> 하지만 애너테이션을 선언하고 이를 처리하는 부분에서 코드의 양이 늘어나며, 처리 코드가 복잡해져 오류가 날 가능성이 커질 수 있다.

### 💡마무리
>  애너테이션으로 할 수 있는 일을 명명 패턴으로 처리할 이유가 없다.