package chapter5.item30;

import java.util.HashSet;
import java.util.Set;

public class Union {

    public static <E> Set<E> union(Set<E> set1, Set<E> set2) {
        Set<E> result = new HashSet<>(set1);
        result.addAll(set2);
        return result;
    }

    public static void main(String[] args) {
        Set<String> stringSet = Set.of("하나", "둘", "셋");
        Set<Integer> integerSet = Set.of(1, 2, 3);
        //Union.union(stringSet, integerSet);
    }
}
