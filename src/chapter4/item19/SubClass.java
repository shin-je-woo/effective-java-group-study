package chapter4.item19;

import java.time.Instant;

public final class SubClass extends Super{
    // 초기화되지 않은 final 필드. 생성자에서 초기화한다.
    private final Instant instant;

    public SubClass() {
        this.instant = Instant.now();
    }

    // 상위 클래스의 생성자가 호출된다.
    @Override
    public void overrideMe() {
        System.out.println(instant);
    }
}
