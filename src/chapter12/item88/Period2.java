package chapter12.item88;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Date;

public final class Period2 implements Serializable {
    private Date start;  // 가변 객체(불변 X)
    private Date end;

    /**
     * @param  start 시작 시각
     * @param  end 종료 시각. 시작 시각보다 뒤여야 한다.
     * @throws IllegalArgumentException 시작 시각이 종료 시각보다 늦을 때 발생한다.
     * @throws NullPointerException start나 end가 null이면 발생한다.
     */
    public Period2(Date start, Date end) {
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());

        if (this.start.compareTo(this.end) > 0)
            throw new IllegalArgumentException(
                    this.start + "가 " + this.end + "보다 늦다.");
    }

    public Date start() {
        return start;
    }
    public Date end() {
        return end;
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {

        s.defaultReadObject();

        // 가변 요소들을 방어적으로 복사한다.
        start = new Date(start.getTime());
        end   = new Date(end.getTime());

        // 불변식을 만족하는지 검사한다.
        if (start.compareTo(end) > 0)
            throw new InvalidObjectException(start +" after " + end);
    }

    @Override
    public String toString() {
        return "Period2{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}