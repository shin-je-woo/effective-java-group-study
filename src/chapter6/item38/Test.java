package chapter6.item38;

import java.util.Arrays;
import java.util.Collection;

public class Test {
    public static void main(String[] args) {
        double x = 2.3;
        double y = 1.2;

        // class 리터널을 넘겨 확장된 연산들을 알려준다.
        test(ExtendedOperation.class, x, y);

        // 한정적 와일드카드 타입
        test(Arrays.asList(ExtendedOperation.values()), x, y);
    }

    private static <T extends Enum<T> & Operation> void test(
            Class<T> opEnumType, double x, double y) {
       for(Operation op : opEnumType.getEnumConstants()) // 모든 상수를 배열로 return
           System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
    }

    private static void test(
            Collection<? extends Operation> operations, double x, double y) {
        for(Operation op : operations)
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
    }
}
