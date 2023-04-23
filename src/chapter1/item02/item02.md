# 💡 생성자에 매개변수가 많다면 빌더를 고려하라

### 개요
* 클래스의 인자들을 객체화하기 위해서 특정 클래스를 만드는 경우가 있습니다.
이때 필수로 받아야할 인자, 선택적으로 받아야할 인자가 구분되는 경우가 있는데요,
아래와 같은 3가지 디자인 패턴의 예시를 통해서 사용방법을 알아봅시다.


✅ 점층적 생성자 패턴 (Telescoping Constructor Pattern)
* 사용자가 원치 않는 매개변수도 어쩔수없이 지정해야한다.
* 코드 작성 및 가독성이 좋지 않다.
* 원하는 매개변수의 조합에따라 생성자가 끝도 없이 많아질 수 있다
* 일일히 사용자가 올바르게 원하는 데이터가 입력되어있는지 확인 해야 한다.

## 코드
```java
public class Coffee {
	//필수 요소
	private  String coffeeBeans ;
	private  Integer water;
	
	//선택 요소
	private  Integer sugar;
	private  Integer cream;
	private  Integer syrup;
	
	public Coffee(String coffeeBeans , Integer water) {
		this(coffeeBeans, water, null);
	}
	
	public Coffee(String coffeeBeans , Integer water, Integer sugar) {
		this(coffeeBeans, water, sugar, null);
	}
	
	public Coffee(String coffeeBeans , Integer water, Integer sugar, Integer cream) {
		this(coffeeBeans, water, sugar, cream, null);
	}
	
	public Coffee(String coffeeBeans , Integer water, Integer sugar, Integer cream, Integer syrup) {
		this.coffeeBeans = coffeeBeans;
		this.water = water;
		this.sugar = sugar;
		this.cream = cream;
		this.syrup = syrup;
	}
}
```
커피를 표현하는 클래스를 만들어 봤는데요.
원두와 물 같은 경우는 필수적으로 들어가야하니, 생성자를 만드는 가장 작은 단위를 아래와 같이 만드는 것입니다.


### 가장 작은 생성자의 단위 (필수 인자값 설정)
```java
	public Coffee(String coffeeBeans , Integer water) {
		this(coffeeBeans, water, null);
	}
```

이후에 본인만의 커피를 만들기 위해 크림, 설탕 등이 필요할 때는 아래와 같이
추가적으로 생성자를 만들어 미리 만들어놓은 생성자에 매개변수를 입력해주면 됩니다.

```java
    public Coffee(String coffeeBeans , Integer water, Integer sugar) {
		this(coffeeBeans, water, sugar, null);
	}
```

## 호출 방식

```java
필수 요소들만 입력할 때
Coffee Item = new Coffee("브라질 원두커피",200);

선택적 요소중 하나인 설탕만 넣을 경우
Coffee Item = new Coffee("브라질 원두커피",200,3);
```

✅ 자바빈즈 패턴 (JavaBeans Pattern)

## 코드
```java
public class BeansPattern {
	
	private  String coffeeBeans ;
	private  Integer water;
	private  Integer sugar;
	private  Integer cream;
	private  Integer syrup;
	public String getCoffeeBeans() { return coffeeBeans; }
	public void setCoffeeBeans(String coffeeBeans) { this.coffeeBeans = coffeeBeans; }

	public Integer getWater() { return water; }
	public void setWater(Integer water) { this.water = water; }

	public Integer getSugar() { return sugar; }
	public void setSugar(Integer sugar) { this.sugar = sugar; }

	public Integer getCream() { return cream; }
	public void setCream(Integer cream) { this.cream = cream; }

	public Integer getSyrup() { return syrup; }
	public void setSyrup(Integer syrup) { this.syrup = syrup; }
}
```


## 호출방식
```java
	public static void main(String[] args) {
		BeansPattern Item = new BeansPattern();
		
		//객체 설정 방법
		Item.setCoffeeBeans("원두커피");
		Item.setWater(3);
		Item.setSugar(2);
		Item.setSyrup(1);
		
		//객체 호출 방법
		Item.getCoffeeBeans();
		Item.getWater();
		Item.getSugar();
		Item.getSyrup();
		
	}
```
## **장점**
* 생성자 패턴 보완
* 가독성이 좋다. 객체를 설정, 호출이 쉽다.
* 객체를 추가할 때도 간단하다.
* 메소드의 이름을 통해서 객체가 뭘 의미하는지 파악할 수 있다.

## **단점**
* 일관성 문제
* 불변성 문제

자바빈즈 패턴은 결국 객체를 하나 만들기 위해 메소드를 여러번 호출해야되고, 
완전히 생성되기 전까지는 일관성이 무너진다. 또 이런 일관성이 무너지는 문제 때문에, 클래스를 불변으로 만들 수 없는 단점이 있다.


✅ 빌더 패턴 (Builder Pattern)

## 코드

```java
public class BuilderPattern {
	
	private  final String coffeeBeans ;
	private  final Integer water;
	private  final Integer sugar;
	private  final Integer cream;
	private  final Integer syrup;
	
	public static class CoffeeBuilder {
		
		//필수 매개변수
		private  final String coffeeBeans ;
		private  final Integer water;
		
		//선택 매개변수
		private  Integer sugar = 0;
		private  Integer cream = 0;
		private  Integer syrup = 0;
		
		public CoffeeBuilder(String coffeeBeans, int water) {
			this.coffeeBeans=coffeeBeans;
			this.water=water;
		}
		
		public CoffeeBuilder sugar(int val) {
			sugar=val;
			return this;
		}
		
		public CoffeeBuilder cream(int val) {
			cream=val;
			return this;
		}
		
		public CoffeeBuilder syrup(int val) {
			syrup=val;
			return this;
		}
		
		public BuilderPattern build() {
			return new BuilderPattern(this);
		}
		
	}
	
	private BuilderPattern (CoffeeBuilder builder) {
		coffeeBeans = builder.coffeeBeans;
		water = builder.water;
		sugar = builder.sugar;
		cream = builder.cream;
		syrup = builder.syrup;
	}
}
```
* Builder 패턴도 결국 생성자를 호출하는 원리이기때문에, 필수 생성자를 이와같이 선택하게 할 수 있습니다.
* 메소드에서 this를 반환하는 것은 이것을 이용해서 다른 함수를 계속 호출하려는 의도 입니다.


## 호출 방식

```java
		//객체 설정및 호출방법 1
		BuilderPattern.CoffeeBuilder builder = new BuilderPattern.CoffeeBuilder("원두커피", 300);
		builder.sugar(3);
		builder.cream(2);
		builder.syrup(2);
		BuilderPattern item = builder.build();
		
		//객체 설정및 호출방법 2		
		BuilderPattern newItem = new BuilderPattern
				.CoffeeBuilder("새로운원두커피", 3).cream(2).syrup(3).build();
```

객체를 생성 및 호출하는 방식은 두가지가 있는데요.

### 첫 번째 방식
* 만들어지는 Builder 패턴을 새로운 객체로 선언함과 동시에, 필수 생성자를 입력해주고,
* getter,setter와 같은 방식으로 원하는 객체들을 설정하고 마지막으로 build()를 호출하는 방식이 있습니다.



### 두 번째 방식
* 위에서 메소드의 return 방식을 return this를 이용했기 때문에, 다른 함수들을 이어서 계속 호출 할 수 있습니다. *cream().syrup().sugar()... 이후엔 마지막에 build()를 이용해 저장 및 호출하는 방식입니다.




### 정리

* 생성자나 정적 팩토리가 처리해야 할 매개변수가 많다면 빌더 패턴을 선택하는게 더 낫다.
* 매개변수 중 다수가 필수가 아니거나 같은 타입이면 특히 더 그렇다.
* 빌더는 점층적 생성자보다 클라이언트 코드를 읽고 쓰기가 훨씬 간결하고, 자바빈즈의 일관성이 무너지는 단점을 보완했기 때문에 훨씬 안전하다.

