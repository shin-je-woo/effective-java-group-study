package chapter6.item39;

import java.lang.annotation.*;

/**
 * 반복 가능한 애너테이션
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(ExceptionTest2Container.class)
public @interface ExceptionTest2 {
    Class<? extends Throwable> value();
}

