package chapter5.item31;

import java.util.Arrays;
import java.util.List;

public class Swap {

    public static <E> void swap(List<E> list, int i, int j) {
        list.set(i, list.set(j, list.get(i)));
    }

    public static void swap2(List<?> list, int i, int j) {
        swapHelper(list, i, j);
    }

    private static <E> void swapHelper(List<E> list, int i, int j) {
        list.set(i, list.set(j, list.get(i)));
    }

    public static void main(String[] args) {
        String[] strings = {"하나", "둘", "셋"};
        List<String> list = Arrays.asList(strings);

        swap(list, 0, 1);
        for (String s : list) {
            System.out.println(s);
        }

        swap2(list, 1, 2);
        for (String s : list) {
            System.out.println(s);
        }
    }
}
