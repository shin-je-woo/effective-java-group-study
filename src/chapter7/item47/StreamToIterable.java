package chapter7.item47;

import java.util.stream.Stream;

public class StreamToIterable {

    public static <T> Iterable<T> iterableOf(Stream<T> stream) {
        return stream::iterator;
    }

    public static void main(String[] args) {
        Stream<String> locationStream = Stream.of("서울", "부산", "대구");

        // 컴파일 오류
/*
        for (String location : locationStream) {
            System.out.println(location);
        }
*/
        // 형변환 적용
        for (String location : (Iterable<String>) locationStream::iterator) {
            System.out.println(location);
        }

        // 어댑터 적용
        for (String location : iterableOf(locationStream)) {
            System.out.println(location);
        }

    }

}
