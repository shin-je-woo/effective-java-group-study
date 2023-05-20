package chapter6.item37;

import java.util.*;
import java.util.stream.Collectors;

import static chapter6.item37.Plant.LifeCycle;
import static java.util.stream.Collectors.toSet;

public class ExampleEnumMap {
    public static void main(String[] args) {

        Plant[] garden = {new Plant("매화", LifeCycle.PERNNIAL), new Plant("매화2", LifeCycle.PERNNIAL), new Plant("매화3", LifeCycle.ANNUAL)};

        // 배열을 사용하지 않기 때문에 안전하지 않은 형변환을 쓰지 않는다.
        Map<LifeCycle, Set<Plant>> plantByLifeCycle = new EnumMap<>(LifeCycle.class);

        for (LifeCycle lc : LifeCycle.values())
            plantByLifeCycle.put(lc, new HashSet<>());

        for (Plant p : garden)
            plantByLifeCycle.get(p.lifeCycle).add(p);

        System.out.println("EnumMap만 사용한 경우");
        System.out.println(plantByLifeCycle);


        /* 스트림 사용한 경우 */
        System.out.println("stream과 사용한 경우");
        System.out.println(Arrays.stream(garden)
                .collect(Collectors.groupingBy(
                                p -> p.lifeCycle,
                                () -> new EnumMap<>(LifeCycle.class),
                                toSet()
                        )
                ));
                            // 1) Function<? super T, ? extends K> classifier
                            // 2) Supplier<M> mapFactory
                            // 3) Collector<? super T, A, D> downstream
                            // 그룹화 결과는 Map<LifeCycle, Set<Plant>>

        }
    }






