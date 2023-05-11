# 💡 아이템25  톱레벨 클래스는 한 파일에 하나만 담으라

## 개요
* 소스 파일 하나에 톱레벨 클래스를 여러개 선언하더라도 문제가 되는 것은 아닙니다 다만 이런 경우 득이 없을 뿐, 심각한 위험을 감수해야된다. 이번에 나오는 예시를 보면 알지만 한 클래스를 여러가지로 정의하게 되면 **어느 소스 파일을 먼저 컴파일 하냐**에 따라 달리지기 때문입니다


### ✅ 톱 클래스 란?
* 다른 클래스 내부에 중첩되지 않은 클래스를 말합니다. 즉, 다른 클래스 내부에 선언되지 않고, 독립적으로 파일에 선언된 클래스를 말합니다.


### 코드예시 1 (TopClass.java)
```java
//﻿(TopClass.java)

class TopClass {
	static final String name = "Top "; 
}

class SubClass {
	static final String name = "Sub ";	
}
```

### Main method
```java
public class MainClass {
	public static void main(String []args) {
		System.out.println(TopClass.name + SubClass.name);
	}
}
```
* 뭐 이때까지만 보더라도, 크게 문제가 되지 않습니다. 이제 우연히 TopClass.java에서 사용한 똑같은 두 클래스를 담은 SubClass.java가 있다고 가정 해봅시다.

### SubClass.java
```java
//﻿(SubClass.java)

class SubClass {
	static final String name = "Sub "; 
}

class TopClass {
	static final String name = "Top ";	
}
```

사실 이렇게되면 아래와 같이 컴파일 오류가 나긴합니다.
![image](https://github.com/shin-je-woo/effective-java-group-study/assets/91134556/91469b43-e83c-43df-a68e-4e4b782e9e9e)

* 먼저, 위와같이 "The type TopClass is already defined" 오류가 발생하는 경우, 두 개 이상의 클래스가 같은 이름으로 정의되어 있기 때문입니다. 


그렇다면 이런 경우 궁금한점이 하나 있는데요, 어떻게 하면 자바는 먼저 정의된 클래스와 나중에 정의된 클래스를 구분할까요?
#### 컴파일 순서 확인★

Java 파일을 컴파일할 때, 컴파일 순서에 따라 클래스가 정의됩니다. 따라서 클래스가 먼저 정의된 파일이 먼저 컴파일되어야 합니다. 만약 두 개 이상의 클래스가 같은 이름으로 정의되어 있다면, 컴파일 오류가 발생하기 전에 어느 파일에서 클래스를 정의했는지 확인할 수 있습니다.
### 쉽게 말해 SubClass를 먼저 만들었냐, TopClass를 먼저 만들었냐의 차이에 따라 사용가능한 Class가 결정되어지는 것입니다.



## 해결책 
```java
public class MainClass {
	public static void main(String []args) {
		System.out.println(TopClass.name + SubClass.name);
	}
	
	private static class TopClass {
		static final String name = "Top "; 
	}
	
	private static class SubClass {
		static final String name = "Sub ";	
	}
}
```
* 당연하지만 간단한 해결방안을 제시합니다.
* 톱 레벨 클래스들은 서로 다른 소스파일로 분리하는 것입니다.
* private으로 선언하면 접근 범위도 최소로 관리할 수 있기 때문에, 효과적일 것입니다.

### 정리
이펙티브 자바에서는 톱레벨 클래스는 한 파일에 하나만 담아라는 규칙을 제시하고 있으며,
이를 통해서 얻는 이점은 다음과 같습니다.

### ✅ 코드 가독성 향상
톱레벨 클래스를 하나의 파일에 한 개씩만 정의하면, **코드의 구조가 더욱 명확해지고 가독성이 향상**됩니다. 이는 다른 개발자들이 코드를 이해하고 유지보수할 때 도움이 됩니다.

### ✅ 클래스 이름 충돌 방지
서로 다른 클래스의 이름이 같은 경우, 코드를 컴파일할 때 문제가 발생할 수 있습니다. 하나의 파일에 여러 클래스를 정의하는 경우, 이러한 충돌이 발생할 확률이 더욱 높아집니다. 그러나 톱레벨 클래스를 한 파일에 하나씩 정의하면, 클래스 이름 **충돌을 방지**할 수 있습니다.

### ✅ 클래스 의존성 최소화
여러 클래스를 한 파일에 정의하면, 이 파일이 변경되면 해당 파일 내의 모든 클래스가 컴파일되어야 합니다. 하지만 톱레벨 클래스를 한 파일에 하나씩 정의하면, 필요한 클래스만 컴파일하면 되므로 **의존성을 최소화**할 수 있습니다.
