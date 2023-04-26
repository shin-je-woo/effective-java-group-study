package chapter3.item10.transitivity;

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
