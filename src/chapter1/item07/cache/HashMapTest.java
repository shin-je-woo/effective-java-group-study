package chapter1.item07.cache;

import chapter1.item02.User;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
    public static void main(String[] args) {
        Map<User, String> map = new HashMap();

        User key1 = new User();
        User key2 = new User();

        map.put(key1, "key1");
        map.put(key2, "key2");

        key1 = null;

        System.gc(); // 강제 Garbage Collection

        System.out.println("gc after --------------------------");
        map.entrySet().stream().forEach(System.out::println);
    }
}
