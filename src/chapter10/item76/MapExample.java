package chapter10.item76;

import java.util.Map;
import java.util.TreeMap;

public class MapExample {
    static Map<Object, String> map = new TreeMap<>();

    public static void main(String[] args) {
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put("4", "D");
    }
}