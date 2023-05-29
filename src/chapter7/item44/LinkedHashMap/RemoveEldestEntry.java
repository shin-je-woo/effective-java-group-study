package chapter7.item44.LinkedHashMap;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiPredicate;

public class RemoveEldestEntry {
    public static void main(String[] args) {

        // 재정의
        LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
                return size() > 100;
            }
        };

        // LinkedHashMap을 오늘날 다시 구현한다면, 함수 객체를 받는 정적 팩터리나 생성자를 제공했을 것이다.
        EldestEntryRemovalFunction<String, Integer> removalFunction = (map2, eldest) -> map2.size() > 100;

        LinkedHashMap<String, Integer> map2 = new LinkedHashMap<String, Integer>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
                return removalFunction.remove(this, eldest);
            }
        };

        //표준 인터페이스 사용
        BiPredicate<Map<String, Integer>, Map.Entry<String, Integer>> removalPredicate =
                (map3, eldest) -> map3.size() > 100;

        LinkedHashMap<String, Integer> map3 = new LinkedHashMap<String, Integer>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
                return removalPredicate.test(this, eldest);
            }
        };

        // 맵에 요소 추가
        for (int i = 0; i < 200; i++) {
            map.put("Key" + i, i);
            map2.put("Key" + i, i);
            map3.put("Key" + i, i);
        }

        // 맵 출력
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}