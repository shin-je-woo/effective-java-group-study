package chapter5.item27;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SupWarnings {

    public static void main(String[] args) {
        Set<String> set = new HashSet();

    }

    private <T> List<T> toList(Class<T> clazz, T... elements) {
        return Arrays.asList(elements);
    }
}
