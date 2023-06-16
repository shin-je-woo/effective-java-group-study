package chapter9.item61;

public class BoxingTest {

    public static void main(String[] args) {
        Integer integer127_1 = Integer.valueOf(127);
        Integer integer127_2 = Integer.valueOf(127);
        System.out.println("integer127_1 == integer127_2 ? " + (integer127_1 == integer127_2)); // true

        Integer integer200_1 = Integer.valueOf(200);
        Integer integer200_2 = Integer.valueOf(200);
        System.out.println("integer200_1 == integer200_2 ? " + (integer200_1 == integer200_2)); // false

        Integer newInteger127_1 = new Integer(127);
        Integer newInteger127_2 = new Integer(127);
        System.out.println("newInteger127_1 == newInteger127_2 ? " + (newInteger127_1 == newInteger127_2)); // false
    }
}
