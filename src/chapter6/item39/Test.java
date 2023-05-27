package chapter6.item39;

import java.lang.annotation.*;

/**
 * 매개변수 없는 정적 메서드 전용
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
    // 유지되는 범위 : RUNTIME
    // 적용 가능한 대상 : METHOD
}
