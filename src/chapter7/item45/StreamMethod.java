package chapter7.item45;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamMethod {
	public static void main(String [] args) {
		
		//원소들의 시퀀스를 일관되게 변환한다.
		List<String> name = Arrays.asList("Lee", "Kwang", "ho");
		List<String> uppercaseNames = name.stream()
		                                   .map(String::toUpperCase)
		                                   .collect(Collectors.toList());
		
		//원소들의 시퀀스를 필터링한다.
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		List<Integer> evenNumbers = numbers.stream()
		                                   .filter(num -> num % 2 == 0)
		                                   .collect(Collectors.toList());
		
		//원소들의 시퀀스를 하나의 연산을 사용해 결합한다.
		List<String> names = Arrays.asList("Lee", "Kwang", "ho");
		String concatenatedNames = names.stream()
		                                .reduce("", (str1, str2) -> str1 + str2);

		//원소들의 시퀀스를 컬렉션에 모은다.
		List<String> nameGroup = Arrays.asList("Lee", "Kwang", "ho");
		Set<String> nameSet = nameGroup.stream()
		                           .collect(Collectors.toSet());
		
		//원소들의 시퀀스에서 특정 조건을 만족하는 원소를 찾는다.
		List<Integer> num = Arrays.asList(1, 2 ,3, 4, 5, 6);
		//List<Integer> num = Arrays.asList(1,3,5);
		Optional<Integer> firstEvenNumber = num.stream()
		                                           .filter(number -> number % 2 == 0)
		                                           .findFirst();
		
		if(firstEvenNumber.isPresent()) {
			System.out.println(firstEvenNumber.get());
		}else {
			System.out.println("원하는 객체가 없네요");
		}
		
	}
}
