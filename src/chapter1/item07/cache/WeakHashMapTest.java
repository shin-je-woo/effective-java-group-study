package chapter1.item07.cache;

import chapter1.item02.User;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapTest {
    public static void main(String[] args) {
        // WeakReference를 이용하는 HashMap
        Map<User, String> map = new WeakHashMap<>();

        User key1 = new User();
        User key2 = new User();

        map.put(key1, "key1");
        map.put(key2, "key2");

        key1 = null;

        System.out.println("gc before -------------------------");
        map.entrySet().stream().forEach(System.out::println);

        System.gc(); // 강제 Garbage Collection

        System.out.println();
        System.out.println("gc after --------------------------");
        // 결과로 key2만 찍힌다.
        map.entrySet().stream().forEach(System.out::println);
    }
}
