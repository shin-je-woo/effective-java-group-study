package chapter6.item39;

import java.util.ArrayList;
import java.util.List;

public class Sample2 {
    @ExceptionTest(IndexOutOfBoundsException.class) public static void doublyBad() {
        List<String> list = new ArrayList<>();
        list.addAll(5, null);
    }

    @ExceptionTest(ArithmeticException.class) public static void m2() { // 다른 예외 발생
        int[] a = new int[0];
        int i = a[1];
    }

    @ExceptionTest(ArithmeticException.class) public static void m3() {} // 예외 발생하지 않음
}
