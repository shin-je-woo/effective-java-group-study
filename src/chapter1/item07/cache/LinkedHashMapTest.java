package chapter1.item07.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapTest {
    public static void main(String[] args) {
        final int MAX_ENTRIES = 5;
        Map<Integer, String> map = new LinkedHashMap<>(MAX_ENTRIES){

            // LRU 페이지 알고리즘 API 제공
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
                return size() > MAX_ENTRIES;
            }
        };

        map.put(0, "H");
        map.put(1, "E");
        map.put(2, "L");
        map.put(3, "L");
        map.put(4, "O");
        map.put(5, "!");

        System.out.println(map);
    }
}
