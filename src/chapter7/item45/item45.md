# 💡 아이템 45 스트림은 주의해서 사용하라

## 개요
* 스트림은 다량의 데이터 처리 작업을 돕고자 자바 8에서 추가된 API입니다. 이 API가 제공하는 **추상 개념 핵심 두 가지**가 있습니다.
- 첫 번째로는 스트림은 **데이터 원소의 유한 혹은 무한 시퀀스**를 뜻합니다.
- 두 번째는 **파이프라인을 통해 원소들로 수행하는 연산단계를 표현하는 개념**입니다.
* 그럼 아래 예시들을 통해서 추상 개념에 대해서 자세히 알아봅시다.

##   
### ✅ 스트림의 원소 예시
* 책에서 제시한 스트림의 원소들은 컬렉션,배열,파일...등등이 있고, 스트림 안의 데이터 원소들은 객체 참조나 기본 타입값입니다. 기본 타입값으로는 int,long,double 이렇게 세가지를 지원합니다.

```java
//컬렉션
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
Stream<String> stream = names.stream();

//배열
int[] numbers = {1, 2, 3, 4, 5};
IntStream stream = Arrays.stream(numbers);

//파일
Path filePath = Paths.get("path/to/file.txt");
Stream<String> lines = Files.lines(filePath);

//정규표현식
String text = "This is a sample text.";
Pattern pattern = Pattern.compile("\\b\\w+\\b");
Stream<String> words = pattern.matcher(text).results()
                              .map(MatchResult::group);

//난수생성기
Random random = new Random();
DoubleStream randomNumbers = random.doubles(5);

//객체참조
Person[] people = {new Person("Alice"), new Person("Bob"), new Person("Charlie")};
Stream<Person> stream = Arrays.stream(people);


//기본타입값
IntStream intStream = IntStream.of(1, 2, 3, 4, 5);
LongStream longStream = LongStream.range(1, 10);
DoubleStream doubleStream = DoubleStream.generate(Math::random);
```

##   
### 스트림 파이프 라인
* 소스 스트림에서 시작해 종단연산으로 끝나며, 그 사이에 하나 이상의 중간연산이 있을 수 있습니다.
* 각 중간 연산은 어떠한 방식으로 변환할 수 있고, 특정 조건을 만족 못하는 원소들을 걸러낼 수 있습니다.

```java
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

		// 중간 연산과 종단 연산을 활용한 스트림 파이프라인
		IntStream sum = numbers.stream()              // 스트림 생성
		                 .filter(n -> n % 2 == 0) // 중간 연산: 짝수만 필터링
		                 .mapToInt(n -> n * 2);    // 중간 연산: 원소를 2배로 변환
		                                  

		// 종단 연산: 합계 계산
		int sumNumber = sum.sum();
		System.out.println("Sum: " + sumNumber); // 출력: Sum: 12
```

* 자바 스트림은 이처럼 연속된 스트림 연산들의 연결을 통해 데이터를 처리하는 메커니즘입니다.
* **스트림생성 -> 중간연산 -> 종단연산의 흐름으로 됩니다.**
* 중간연산은 필요에 따라 여러번 사용할 수 있고, 종단 연산이 호출될 때 까지 중간연산은 **★지연평가** 됩니다.

### ✅ 지연평가
* 스트림에서 지연평가란 중간연산이 실제로 수행되기 전까지 연산이 지연되는 특징입니다.
* 중간연산은 호출되는 시기에 바로 실행되지 않고, 종단연산이 호출될 때 까지 기다립니다.

### ✔ 효율적인연산
* 중간 연산이 필요한 만큼만 수행하기 때문에 불필요한 연산 발생하지 않음으로, 대량의 데이터 처리시 성능 향상
### ✔ 단축연산
* 필요에 따라 중간연산을 추가하여 원하는 결과를 도출 할 수 있도록 결과를 빠르게 얻을 수 있습니다.
### ✔ 무한 스트림 처리
* 무한한 개수의 스트림또한 지연평가를 통해 처리 가능하고, 필요한 만큼만 연산을 수행하므로, 무한한 요소를 다룰 때에도 제한된 자원으로 작업이 가능합니다.

## 정리
* 스트림을 사용할 때 스트림의 명확성, 연산의 방식이 정확하게 이뤄지도록 해야한다.
* 스트림을 사용하는 이유는 결국 간결함, 가독성, 병렬처리, 성능향상등이지만 목적이 불분명하게 쓰인다면 좋은 **장점을 단점으로 만들어버릴 수 있습니다.**

### 스트림에 적합한 예시
- 원소들의 시퀀스를 일관되게 변환한다.
- 원소들의 시퀀스를 필터링한다.
- 원소들의 시퀀스를 하나의 연산을 사용해 결합한다. (더하기, 연결하기, 최소값 등..)
- 원소들의 시퀀스를 컬렉션에 모은다
- 원소들의 시퀀스에서 특정 조건을 만족하는 원소를 찾는다.

```java
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
```
