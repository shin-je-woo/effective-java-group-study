package chapter8.item50;

import java.util.Date;

public final class SafePeriod {
    private final Date start;  // 가변 객체(불변 X)
    private final Date end;

    /**
     * @param  start 시작 시각
     * @param  end 종료 시각. 시작 시각보다 뒤여야 한다.
     * @throws IllegalArgumentException 시작 시각이 종료 시각보다 늦을 때 발생한다.
     * @throws NullPointerException start나 end가 null이면 발생한다.
     */

    public SafePeriod(Date start, Date end) {
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());

        if (this.start.compareTo(this.end) > 0)
            throw new IllegalArgumentException(
                    this.start + "가 " + this.end + "보다 늦다.");
    }

    public Date start() {
        return new Date(start.getTime());
    }

    public Date end() {
        return new Date(end.getTime());
    }
}