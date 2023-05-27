package chapter6.item39;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 매개변수 하나 받는 애너테이션
 * 명시한 예외를 던져야 성공
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionTest {
    // Throwable을 확장한 클래스 : 모든 예외와 오류(Exception extends Throwable)를 수용한다.
    Class<? extends Throwable> value();

}
