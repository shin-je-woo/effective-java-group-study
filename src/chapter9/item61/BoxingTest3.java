package chapter9.item61;

import java.util.Comparator;

public class BoxingTest3 {

    public static void main(String[] args) {
        Comparator<Integer> naturalOrder = (i, j) -> (i < j) ? -1 : (i == j ? 0 : 1);
        int result = naturalOrder.compare(new Integer(200), new Integer(200));
        System.out.println("result = " + result); // 1

        Comparator<Integer> naturalOrder2 = (iBoxed, jBoxed) -> {
            int i = iBoxed, j = jBoxed;
            return i < j ? -1 : (i == j ? 0 : 1);
        };
        int result2 = naturalOrder2.compare(new Integer(200), new Integer(200));
        System.out.println("result = " + result2); // 0
    }
}
