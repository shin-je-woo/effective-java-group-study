# 💡 아이템 55 옵셔널 반환은 null을 반환하는 것보다 낫다

## 개요
* 메소드가 특정 조건에서 값을 반환할 수 없을때 취할 수 있는 선택지가 두 가지가 있었습니다. 예외를 던지거나, null 자체를 반환하는 것입니다.

### 하지만 이 두가지 방법 모두 허점이 있습니다.

* 예외는 진짜 예외적인 상황에서만 사용해야 하며, 예외를 생성할 때 스택 추적 전체를 잡아먹기에 비용이 만만치 않습니다.
* null을 반환하면 이런 문제가 생기지는 않지만 별도의 null 값을 처리해주는 처리 코드를 추가해야합니다. null 처리를 무시하고, null값을 저장해둔다면 언젠간 NullPointerException이 발생할 수 있습니다.

##   
### ✅ 해결방법
* 자바 버전이 8로 올라오면서 하나의 선택지가 생겼습니다. 이번 아이템에서 배우는 Optional<T> 입니다.
* 해당 API는 값의 존재 여부를 명시적으로 나타내는데 사용됩니다. 위에서 말한 null에 관한 허점의 문제와 예외 처리의 어려움을 해결하기 위해 Optional<T>이 도입되었습니다.
* 옵셔널을 사용함으로써 얻을 수 있는 이점은   **NullPointerException 방지 , 코드의 가독성 향상, 예외 처리 용이성** 정도가 있습니다.
  
##   
### 예시코드
* 아래 두 가지 예시 코드를 통해서 Optional 의 기능에 대해서 알아봅시다.

```java
public static <E extends Comparable<E>> E max (Collection<E> c) {
	if(c.isEmpty())
    	throw new IllegalArgumentException("빈 컬렉션");
    
    E result = null;
    for (E e : c )
    	if (result==null || e.compareTo(result) > 0)
        	result = Objects.requireNonNull(e);
            
    return result;
} 
```
* 이 메소드는 주어진 컬렉션에서 가장 큰값을 찾아서 반환하는 코드입니다.
* 다만 해당 컬렉션이 비어있는 경우 즉 전달되는 요소가 없는 경우는 IllegalArgumentException 의 예외를 생성합니다. 

### 보완 코드

```java
public static <E extends Comparable<E>> Optional<E> max(Collection<E> c) {
   if (c.isEmpty())
   		return Optional.empty();
        
   E result = null;
   for (E e : c)
   		if(result==null||e.compareTo(result) > 0)
        	result = Objects.requireNonNull(e);
            
   return Optional.of(result);
}
```
* 옵셔널을 반환하도록 구현하는건 위와 같이 어렵지 않습니다. 값이 전달되지 않을 경우에는 Optional.empty()를 이용해 빈 옵셔널을 만들고, 값이 든 옵셔널은 Optional.of()을 이용해 값을 전달했다. 다만 이런 경우 Optional.of()에 null이 들어갈 경우 NullPointerException을 던지니, Optional.ofNullable()을 사용하면 된다. 
* ★옵셔널을 반환하는 메소드에서는 절대 null을 반환하지 말자. 

  
##   
### ✅ 옵셔널 사용법
* 아래 예시들은 옵셔널을 활용하여 값의 존재 여부를 체크하고, 값을 다루는 다양한 상황에서 유용하게 사용할 수 있음을 보여줍니다. 
* 이러한 활용법을 통해 코드의 가독성과 안정성을 높일 수 있습니다. 물론, 실제 사용 시에는 각 상황에 맞게 적절한 옵셔널 메서드를 선택하여 사용해야 합니다.

```java
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
```
### 기본적으로 쓰는 방식
```java
public class Example {
    public static void main(String[] args) {
        String result = getValueFromDatabase("key")
                .orElse("Default Value");
        System.out.println(result);
    }

    public static Optional<String> getValueFromDatabase(String key) {
        // 데이터베이스에서 값을 조회하는 로직
        // 값이 존재하는 경우
        String value = "Value from database";
        return Optional.ofNullable(value);

        // 값이 존재하지 않는 경우
        // return Optional.empty();
    }
}
```
* 'getValueFromDatabase' 메소드는 데이터베이스에서 값을 조회하는 로직으로 가정해봅시다. 
* 메소드를 호출 한 후에 orElse 메소드를 사용하여 값의 존재 여부를 확인하고, 값이 없는 경우 대체값을 이용해 값을 제공하는 방식입니다.
  
  
##
### 정리
* 값을 변환하지 못할 가능성이 있고, 호출 때 마다 반환값이 없을 가능성을 염두에 둬야 하는 메소드라면 옵셔널이 효율적일 수 있다. 
* 하지만 옵셔널 반환에는 성능 저하가 뒤따르니, 성능에 민감한 메소드라면 null을 반환하거나 예외를 던지는편이 나을 수 있다. 


