package chapter7.item47;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class IterableToStream {

    public static <T> Stream<T> streamOf(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    public static void main(String[] args) {
        List<String> locationList = Arrays.asList("서울", "부산", "대구");

        Stream<String> locationStream = streamOf(locationList);

        locationStream.forEach(System.out::println);
    }
}
