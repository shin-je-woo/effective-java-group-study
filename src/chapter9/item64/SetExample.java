package chapter9.item64;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class SetExample {
	public static void main(String[] args) {
        // LinkedHashSet 사용
        Set<String> linkedSet = new LinkedHashSet<>();
        linkedSet.add("Apple");
        linkedSet.add("Banana");
        linkedSet.add("Orange");

        System.out.println("LinkedHashSet:");
        for (String item : linkedSet) {
            System.out.println(item);
        }

        // HashSet으로 변경
        Set<String> hashSet = new HashSet<>(linkedSet);

        System.out.println("\nHashSet:");
        for (String item : hashSet) {
            System.out.println(item);
        }
	}
}
