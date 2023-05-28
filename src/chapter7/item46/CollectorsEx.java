package chapter7.item46;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import java.util.Arrays;

public class CollectorsEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//toList() 수집기 예시
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> collectedList = 
				numbers.stream().
				collect(Collectors.toList());
		System.out.println(collectedList); 
		
		//toSet() 수집기 예시
		List<String> words = Arrays.asList("apple", "banana", "cherry", "apple");
		Set<String> uniqueWords = 
				words.stream().
				collect(Collectors.toSet());
		System.out.println(uniqueWords); 
		
		//toMap(keyMapper, valueMapper) 수집기 
		List<String> countries = Arrays.asList("Korea", "Japan", "China", "France");
		Map<String, Integer> countryLengths = 
				countries.stream()
		        .collect(Collectors.toMap(country -> country, String::length));
		System.out.println(countryLengths); 
		
		//중복된 키값을 병합하는 방법은? toMap-> mergeFunction 활용
		
	}

}
