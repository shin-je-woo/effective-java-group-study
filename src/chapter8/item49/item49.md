# 💡 아이템 49 매개변수가 유효한지 검사해라

## 개요
* 이번 8장은 메서드를 설계할 때 주의할 점과 구체적으로 매개변수와 반환값을 처리하는 방법, 문서화 방법...등에 대해서 배우게 됩니다.

##   
### ✅ 메소드를 만들 때 주의할 점
* 매서드와 생성자가 특정 제약이 있다면 문서화 하는 것이 좋다. 
* 메서드 몸체가 시작되기전에 검사 해야한다.
* 오류는 가능한 빨리 (발생한 곳에서) 잡아야 한다. 그렇지 않다면 오류를 감지하기 어렵고, 발생한 지점또한 찾기 어려울 것이다.

### ✅ 매개변수가 잘못 전달 됐을 때 문제
* 메소드가 수행되는 중간에 애매모호한 예외를 던지며 실패할 수 있다 (매개변수의 문제라고 판단하기 어려울 수 있음)
* 메소드가 잘 수행되었지만 설계자의 의도와는 잘못된 결과를 반환할 때
* 똑같이 잘 수행되었지만 어떤 객체를 이상한 상태로 만들어놓아서 미래의 시점에 이 메소드와는 관련없는 오류를 낼 때

##   
### 예시코드

```java
public class ExampleClass {
	public static void main(String[] args) {

		
		BigInteger number = new BigInteger("5");
		System.out.println(mod(number));
		
	}
	/**
	 * (현재 값 mod m) 값을 반환한다. 이 메서드는
	 * 항상 음이 아닌 BigInteger를 반환한다는 점에서 remainder 메서드와 다르다.
	 *
	 * @param m 계수(양수여야 한다.)
	 * @return 현재 값 mod m
	 * @throws ArithmeticException m이 0보다 작거나 같으면 발생한다.
	 */
	public static BigInteger mod(BigInteger m) {
		if (m.signum() <= 0) {
	    	throw new ArithmeticException("계수(m)는 양수여야합니다. " + m);
	    }
	    return m;
	}
}
```
* 이 메소드는 m 이 null 이면 signum()을 호출 할 때 NullpointerException오류가 나게됩니다.
* 다만 해당 오류는 어디에도 설명이 없는데요. 그 이유는 클래스 수준 주석에 설명에만 되어있기 때문입니다.

![image](https://github.com/shin-je-woo/effective-java-group-study/assets/91134556/2e0c8d85-999b-47ce-8a2e-6779055fdbc0)


### 보완 코드

```java
	public static BigInteger mod(BigInteger m) {
		if (m==null) {
			throw new IllegalArgumentException("계수(m)는 null일 수 없습니다.");
		}
		if (m.signum() <= 0) {
	    	throw new ArithmeticException("계수(m)는 양수여야합니다. " + m);
	    }
	    return m;
	}
```

##
### requireNonNull 
* 자바 7에서 추가된 requireNonNull 메소드는 유연하고 사용하기도 편하니, 더 이상 null 검사를 수동으로 하지않아도된다. 원하는 예외메세지도 위와 같이 지정할 수 있다.
```java
public class ObjectBean {
	private String str;

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = Objects.requireNonNull(str,"null이면 안돼!");
	}
}
```

* 하지만 이런 방법보다는 @Nullable null값을 허용하는 혹은 반대로 @NonNull 처럼 null값을 허용하지 않는 어노테이션등이 존재하기도 합니다.
```java
public void setStr(@NonNull String str) {
   this.str = str;
}
```

##
### 정리
* 메소드나 생성자를 작성할 때면 그 매개변수들에 어떤 제약이 있을지 생각하는 습관을 길러야합니다.
* 해당 제약들을 문서화하고, 메소드 코드 시작 부분에서 명시적으로 검사해야 합니다.
* 해당 작업은 실제 오류를 처음 걸러낼 수 있도록 도와줄 것이고, 시간을 많이 줄여줄 것을 보장합니다.



