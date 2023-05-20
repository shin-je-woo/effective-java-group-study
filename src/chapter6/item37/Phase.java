package chapter6.item37;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

public enum Phase {
    SOLID, LIQUID, GAS;
    // ,PLASMA

    public enum Transition {
        MELT(SOLID, LIQUID), FREEZE(LIQUID, SOLID),
        BOIL(LIQUID, GAS), CONDENSE(GAS, LIQUID);
        // IONIZE(GAS, PLASMA), DEIONIZE(PLASMA, GAS),

        private final Phase from;
        private final Phase to;

        Transition(Phase from, Phase to){
            this.from = from;
            this.to = to;
        }

        private static final Map<Phase, Map<Phase, Transition>> transitionMap;

        static {
            transitionMap = Stream.of(Transition.values())
                    .collect(groupingBy(
                            t -> t.from,
                            () -> new EnumMap<>(Phase.class),
                            Collectors.toMap(
                                    t -> t.to,
                                    t -> t,
                                    (x, y) -> y,
                                    () -> new EnumMap<>(Phase.class)
                            )));
        }
        /*
        * toMap 3번째 매개변수
        * BinaryOperator<U> mergeFunction,
        * 중복된 키에 대한 충돌 해결 전략을 정의
        * */

        public static Transition from(Phase from, Phase to){
            return transitionMap.get(from).get(to);
        }
    }
}
