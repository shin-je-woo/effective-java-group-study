package chapter11.item79;

@FunctionalInterface
public interface SetObserver<E> {
    // ObservableSet에 원소가 더해지면 호출된다.
    void added(ObservableSet<E> eObservableSet, E element);

    // BiConsumer : 인수를 하나 받고 반환값은 없는(특히 인수를 소비하는) 함수
    // 커스텀 항수형 인터페이스 정의함으로 다중 콜백을 지원하도록 확장할 수 있다.
}
