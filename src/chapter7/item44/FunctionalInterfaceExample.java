package chapter7.item44;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FunctionalInterfaceExample {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();

        for (int i = 1; i <= 50000; i++) {
            numbers.add(i);
        }

        // Consumer 인터페이스를 사용하여 정수를 출력하는 메서드를 구현
        Consumer<Integer> integerConsumer = (Integer number) -> System.out.println(number);

        IntConsumer intConsumer = (int number) -> System.out.println(number);


        long startTime = System.nanoTime();

        // Consumer<Integer>를 사용하여 리스트의 각 요소 출력
        numbers.forEach(integerConsumer);

        long endTime = System.nanoTime();
        long integerConsumerTime = endTime - startTime;





        startTime = System.nanoTime();

        // IntConsumer를 사용하여 리스트의 각 요소 출력
        numbers.forEach(intConsumer::accept);

        endTime = System.nanoTime();
        long intConsumerTime = endTime - startTime;




        System.out.println("Consumer<Integer> 실행 시간: " + integerConsumerTime + " ns");
        System.out.println("IntConsumer 실행 시간: " + intConsumerTime + " ns");
    }
}
