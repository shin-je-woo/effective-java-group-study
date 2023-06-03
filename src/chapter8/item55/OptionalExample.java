package chapter8.item55;

import java.util.NoSuchElementException;
import java.util.Optional;

public class OptionalExample {
	public static void main(String[] args) {

		String name ;
		
		//1. 값이 없을 경우 기본값을 제공하기
		Optional<String> optionalDefault = Optional.ofNullable(null);
		name = optionalDefault.orElse("Unknown");

		//2. 값이 없는 경우 예외 발생시키기
		Optional<String> optionalException = Optional.ofNullable("");
		name = optionalException.orElseThrow(() -> new NoSuchElementException("Name not found"));
		
		//3. 값이 있는 경우에만 처리하기
		Optional<String> optionalIfName = Optional.ofNullable("이광호");
		optionalIfName.ifPresent(data -> System.out.println("Name: " + data));
		
		//4. 값이 있는 경우 변환 또는 가공하기
		Optional<String> optionalName = Optional.ofNullable("이광호");
		Optional<Integer> optionalNameLength = optionalName.map(String::length);
		
		//5. 다른 옵셔널과 조합하기
		Optional<String> optionalMixName = Optional.ofNullable("LeeKwangho");
		Optional<String> optionalUpperCaseName = optionalMixName.map(String::toUpperCase);
		Optional<String> optionalGreeting = optionalUpperCaseName.flatMap(data -> Optional.of("Hello, " + data));
		
		
	}
}
