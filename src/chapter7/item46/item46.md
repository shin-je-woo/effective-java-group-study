# 💡 아이템 46스트림에서는 부작용 없는 함수를 사용하라

## 개요

* 스트림은 처음 봐서는 이해하기 어려울 수 있습니다. 원하는 작업을 스트림 파이프라인으로 표현하는 것 조차 어려울지 모릅니다. 성공하여 프로그램이 동작하더라도 장점이 무엇인지 쉽게 와 닿지 않을 수 있습니다.

* 스트림의 핵심은 계산을 **일련의 변환으로 재구성하는 부분**입니다. 이때 각 변환 단계는 이전 단계의 결과를 받아 처리하는 *순수 함수여야 합니다.
* 다른 가변 상태를 참조하지 않고, 함수 스스로도 다른 상태를 변경하지 않는다. 그렇기에 이번 아이템에서는 스트림에서 사용되는 함수(람다 표현식 또는 메서드 참조)가 **부작용(side effects)을 포함하지 않아야 한다는 원칙을 강조**합니다.

###### 순수함수 = 오직 입력만이 결과에 영향을 주는 함수


##   
### ✅ 스트림 API를 잘못 활용한 예시
```java
        Map<String, Long> freq = new HashMap<>();
        try (Stream<String> words = new Scanner("file").tokens()) {
            words.forEach(word -> freq.merge(word.toLowerCase(), 1L, Long::sum));
        }
```
* 해당 코드는 스트림, 람다를 사용해서 오류없이 출력할 수 있는 예시입니다.
* 다만 책에서 제시하는 이 코드는 절대 스트림 코드라할 수 없고, 스트림 API의 이점을 잘 못 살린 잘못된 코드라고 합니다. 스트림 API를 잘못 사용했을 때 오히려 길고, 읽기 어렵고, 유지보수에도 좋지 않습니다.


### ✅ 스트림 API를 올바르게 활용한 예시
```java
        Map<String, Long> sampleStream = new HashMap<>();
        try (Stream<String> words = new Scanner("file").tokens()) {
        	sampleStream=words.collect(groupingBy(String::toLowerCase, counting()));
        }
```
* 이 코드는 스트림의 요소를 수집하여 **결과를 반환하는 종단연산자인 collect를 사용**하고있다.
* 스트림을 사용하려면 꼭 알아야되는 개념인데, java.util.stream.Collectors 에는 굉장히 많은 메소드와 타입 매개변수를 포함하고 있다고 한다. 이 수집기가 사용하는 객체는 일반적으로 컬렉션이기에 'collector' 라는 이름을 사용하게 된다.


##   
### 수집기 활용법

```java
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
```
### ✅ Collectors 메소드 
### ✔ counting()
```java
List<String> fruits = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");
long count = fruits.stream()
                   .collect(Collectors.counting());

System.out.println(count);
```
* Collectors.counting() -> 스트림의 요소 개수를 계산하는 컬렉터. 주로 그룹화된 요소의 개수를 세는용도로 사용합니다. 현재 fruits에 선언된 객체는 5개임으로, counting()를 활용해 결과값은 5가 출력될 것입니다.

```java
List<String> words = 
Arrays.asList("apple", "banana", "cherry", "date", "elderberry","apple");
Map<String, Long> countByString = words.stream()
		        .collect(Collectors.groupingBy(String::toString, Collectors.counting()));
```
* {date=1, banana=1, cherry=1, apple=2, elderberry=1}

### ✔ joining()
```java
List<String> fruits = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");

// 구분자를 기준으로 문자열을 합침
String joinedString = fruits.stream()
        .collect(Collectors.joining(","));

System.out.println(joinedString);
```
* joining() 컬렉터를 사용하여 구분자 ,로 문자열을 합칩니다.  구분자를 인자로 전달하여 원하는 구분자로 요소들을 연결할 수 있습니다.
* apple,banana,cherry,date,elderberry


## 정리
* 스트림 파이프라인 프로그래밍의 핵심은 부작용 없는 함수 객체에 있습니다.
* 종단연산자 중 forEach는 수행한 계산 결과를 보고할 때만 이용을 하는게 좋습니다.
* 또 스트림을 올바르게 사용하려면 위에서 제시한 toList, toSet, toMap...등을 올바르게 알고 상황에 맞게 활용하는 것이 이번 아이템에서 제시한 올바른 API 사용법 입니다.
