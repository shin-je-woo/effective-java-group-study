package chapter4.item18;

import java.time.Instant;
import java.util.*;

public class SetTest {

    public static <E> void main(String[] args) {
        InstrumnetdHashSet<String> s = new InstrumnetdHashSet<>();
        s.addAll(List.of("아", "야야", "거"));
        System.out.println(s.getAddCount());


        // 어떠한 Set 구현체라도 계측할 수 있음 -> 래퍼 클래스
        Set<Instant> times = new InstrumnetdSet<>(new TreeSet<>());
        Set<E> set = new InstrumnetdSet<>(new HashSet<>(5));


        InstrumnetdSet<String> instrumnetdSet = new InstrumnetdSet<>(new HashSet<>());
        instrumnetdSet.addAll(Arrays.asList(new String[]{"아", "야야", "거"}));
        System.out.println(instrumnetdSet.getAddCount());




    }
}
