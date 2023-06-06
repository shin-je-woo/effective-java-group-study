# 💡 ordinal 인덱싱 대신 EnumMap을 사용하라

## ✔ ordinal 인덱싱의 문제점
ordinal 메서드로 열거 타입 상수의 인덱스를 얻어 배열이나 리스트 원소를 꺼낼 수 있다.
- 그러나 배열은 제너릭과 호환되지 않으니 비검사 형변환을 수행해야 하고,
- 각 인덱스의 의미를 모르니 출력 결과에 직접 레이블을 달아야 한다.
- 또한 정확한 정숫값을 사용한다는 것을 직접 보증해야한다.

### 배열을 사용한 예시코드
```java
Plant[] garden = {new Plant("매화", Plant.LifeCycle.PERNNIAL), new Plant("매화2", Plant.LifeCycle.PERNNIAL), new Plant("매화3", Plant.LifeCycle.ANNUAL)};

// 비검사 형변환을 수행해야 한다.
@SuppressWarnings("unchecked")
Set<Plant>[] plantByLifeCycle = (Set<Plant>[]) new Set[Plant.LifeCycle.values().length];

for(int i=0; i < plantByLifeCycle.length; i++)
    plantByLifeCycle[i] = new HashSet<>();


// 인덱스의 의미를 알 수 없다.
for (Plant p : garden)
    plantByLifeCycle[p.lifeCycle.ordinal()].add(p);


// 정확한 정숫값을 사용한다는 것을 직접 보증해야한다.
for(int i=0; i < plantByLifeCycle.length; i++){
    System.out.printf("%s: %s%n", Plant.LifeCycle.values()[i], plantByLifeCycle[i]);
}
```

## ✔ EnumMap의 완벽한 해결책
- 배열 대신 Map 타입을 사용하여 안전하지 않은 형변환을 쓰지 않는다.
- 맵의 키인 열거 타입이 그 자체로 출력용 문자열을 제공하니 직접 레이블을 달지 않아도 된다.
- 배열 인덱스를 계산하는 과정에서 오류가 날 가능성이 아예 없다.

### EnumMap을 사용한 예시코드
```java
// 배열을 사용하지 않기 때문에 안전하지 않은 형변환을 쓰지 않는다.
Map<LifeCycle, Set<Plant>> plantByLifeCycle = new EnumMap<>(LifeCycle.class);

for (LifeCycle lc : LifeCycle.values())
    plantByLifeCycle.put(lc, new HashSet<>());

for (Plant p : garden)
    plantByLifeCycle.get(p.lifeCycle).add(p);

System.out.println(plantByLifeCycle);
// Output: {ANNUAL=[매화3], PERNNIAL=[매화, 매화2], BIENNIAL=[]}
```
> EnumMap의 내부에서 배열을 사용하며 구현 방식을 안으로 숨겨서 Map 타입 안전성과 배열의 성능을 모두 언어낸 것이다.

### stream을 이용한 예시코드
```java
System.out.println(Arrays.stream(garden)
        .collect(Collectors.groupingBy(
                        p -> p.lifeCycle,
                        () -> new EnumMap<>(LifeCycle.class),
                        toSet()
                )
        ));
// Output: {ANNUAL=[매화3], PERNNIAL=[매화, 매화2]}
```
> 스트림 버전에서는 Plant 기준으로 생성을 하기 때문에 해당 생애주기에 속하는 식물이 있을 때만 만든다.

### 중첩 EnumMap을 사용한 예시코드
```java
public enum Phase {
    SOLID, LIQUID, GAS, PLASMA;
    
    public enum Transition {
        IONIZE(GAS, PLASMA), DEIONIZE(PLASMA, GAS),
        // ...중략
        
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
```
> 데이터와 열거 타입 쌍을 연결한 경우로, Map 타입을 이용했기 때문에 상태 값이 추가되더라고
> 기존 로직에서 수정할 부분이 없어서 유지보수성이 좋다.


### 💡마무리
> 배열의 인덱스를 얻기 위해 ordinal을 쓰는 것은 좋지 않으니, EnumMap을 사용하고
> 다차원 관계도 EnumMap<..., EnumMap<...>>으로 표현 가능하다.