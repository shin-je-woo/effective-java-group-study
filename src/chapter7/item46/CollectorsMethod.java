package chapter7.item46;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

import java.util.Arrays;

public class CollectorsMethod {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<String> fruits = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");
		long count = fruits.stream()
		                   .collect(Collectors.counting());

		System.out.println(count);
		
		// 구분자를 기준으로 문자열을 합침
		String joinedString = fruits.stream()
		        .collect(Collectors.joining(","));

		System.out.println(joinedString);
		
	}

}
