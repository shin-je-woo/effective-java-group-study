package chapter3.item10.transitivity;

public class TransitivityTest {

    public static void main(String[] args) {
        ColorPoint redPoint = new ColorPoint(5, 5, Color.RED);
        Point normalPoint = new Point(5, 5);
        ColorPoint greenPoint = new ColorPoint(5, 5, Color.GREEN);

        System.out.println(redPoint.equals(normalPoint)); // true
        System.out.println(normalPoint.equals(greenPoint)); // true
        System.out.println(redPoint.equals(greenPoint)); // false
    }
}
