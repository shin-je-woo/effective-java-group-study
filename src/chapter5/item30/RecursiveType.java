package chapter5.item30;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class RecursiveType {

    public static <E extends Comparable<E>> E max(Collection<E> c) {
        if (c.isEmpty())
            throw new IllegalArgumentException("컬렉션이 비어 있습니다.");

        E result = null;
        for (E e : c)
            if (result == null || e.compareTo(result) > 0)
                result = Objects.requireNonNull(e);

        return result;
    }

    public static void main(String[] args) {
        String[] strings = {"하나", "둘", "셋"};
        List<String> stringList = Arrays.asList(strings);
        System.out.println(max(stringList));

        Integer[] integers = {1, 2, 3};
        List<Integer> integerList = Arrays.asList(integers);
        System.out.println(max(integerList));
    }

}
