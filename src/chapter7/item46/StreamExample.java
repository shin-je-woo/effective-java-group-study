package chapter7.item46;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

@SuppressWarnings("resource")
public class StreamExample {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

        Map<String, Long> freq = new HashMap<>();
        try (Stream<String> words = new Scanner("file").tokens()) {
            words.forEach(word -> freq.merge(word.toLowerCase(), 1L, Long::sum));
        }
        
        Map<String, Long> sampleStream = new HashMap<>();
        try (Stream<String> words = new Scanner("file").tokens()) {
        	sampleStream=words.collect(groupingBy(String::toLowerCase, counting()));
        }
        
        //counting()은 스트림의 요소 개수를 집계하기 위해 사용되는 편리한 컬렉터로, 그룹화된 요소의 개수를 세는 등 다양한 상황에서 활용될 수 있습니다.
        
	}
}
