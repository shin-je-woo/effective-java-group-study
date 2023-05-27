package chapter6.item39;

import java.util.ArrayList;
import java.util.List;

public class Sample3 {
    @ExceptionTest2(IndexOutOfBoundsException.class)
    @ExceptionTest2(NullPointerException.class)
    public static void doublyBad() {
        List<String> list = new ArrayList<>();
        list.addAll(5, null);
    }

    @ExceptionTest2(ArithmeticException.class) public static void m2() {
        int[] a = new int[0];
        int i = a[1];
    }
}
