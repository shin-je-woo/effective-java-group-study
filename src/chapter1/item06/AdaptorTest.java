package chapter1.item06;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AdaptorTest {
    public static void main(String[] args) {
        //Map 인터페이스를 구현한 HashMap 클래스
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "first");
        map.put(2, "second");
        map.put(3, "third");

        //Set이 Map 객체의 어댑터
        Set<Integer> keySet = map.keySet();
        System.out.println(keySet); // [1, 2, 3]

        //HashMap 객체에서 remove() 메서드를 사용하여 key값이 3인 쌍을 제거
        map.remove(3);
        //keySet()을 호출하여 해당 key 값이 제거된 것을 확인
        System.out.println(keySet); // [1, 2]
    }
}