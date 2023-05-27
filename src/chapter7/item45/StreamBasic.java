package chapter7.item45;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamBasic {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		//컬렉션
		List<String> names = Arrays.asList("Lee", "Kwang", "ho");
		Stream<String> stream = names.stream();

		//배열
		int[] numbers = {1, 2, 3, 4, 5};
		IntStream stream1 = Arrays.stream(numbers);

		//파일
		Path filePath = Paths.get("path/to/file.txt");
		Stream<String> lines = Files.lines(filePath);

		//정규표현식
		String text = "This is a sample text.";
		Pattern pattern = Pattern.compile("\\b\\w+\\b");
		Stream<String> words = pattern.matcher(text).results()
		                              .map(MatchResult::group);
//		
//		List<String> wordList = words.collect(Collectors.toList());
//		System.out.println(wordList);
		

		//난수생성기
		Random random = new Random();
		DoubleStream randomNumbers = random.doubles(5);

		//객체참조
		Person[] people = {new Person("Lee"), new Person("Kwang"), new Person("ho")};
		Stream<Person> stream2 = Arrays.stream(people);


		//기본타입값
		IntStream intStream = IntStream.of(1, 2, 3, 4, 5);
		LongStream longStream = LongStream.range(1, 10);
		DoubleStream doubleStream = DoubleStream.generate(Math::random);
	}
	
	static class Person {

		public Person(String string) {
		}
		
	}

}
