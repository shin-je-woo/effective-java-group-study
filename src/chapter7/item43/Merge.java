package chapter7.item43;

    import java.util.HashMap;
import java.util.Map;

public class Merge {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();

        // "key1"이 이미 존재하는 경우 1을 더하고,
        // "key2"가 존재하지 않는 경우 1을 추가합니다.
        map.merge("key1", 1, (count, incr) -> count + incr);
        map.merge("key2", 1, (count, incr) -> count + incr);
        map.merge("key1", 1, Integer::sum);

        System.out.println(map);
    }
}
