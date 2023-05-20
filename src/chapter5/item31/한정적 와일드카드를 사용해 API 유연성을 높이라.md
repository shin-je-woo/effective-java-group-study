# Previous
* 타입 매개변수는 `불공변` 이다. (아이템28에서 학습)
* 잠깐 정리하자면, `List<String>` 은 `List<Object>` 의 하위 타입이 아니다.
* `List<Object>` 에는 어떤 객체든 넣을 수 있지만, `List<String>` 에는 문자열만 넣을 수 있다.
* 즉, `List<String>` 은 `List<Object>` 의 역할을 제대로 수행하지 못한다. (공변이라고 가정한다면 이는 리스코프 치환 원칙을 위배하는 것이다.)
* 불공변은 유연하지 않다는 문제가 있다. 유연하지 않은 예시와 이를 해결하는 방법을 살펴보자.

# 💡 한정적 와일카드의 필요성
* 아이템 29에서 진행한 Stack 예제 코드를 떠올려 보자.
* 여기에 `pushAll` 메서드를 추가해보자.
```java
public void pushAll(Iterable<E> src) {
    for (E e : src) {
        push(e);
    }
}
```
* 컴파일은 정상적으로 수행되지만, 문제가 있다.
* src의 타입이 스택의 타입과 일치하면 문제가 없겠지만, 다를 경우 불공변 특성으로 인해 컴파일 에러가 발생한다.
```java
MyStackV3<Number> numberStack = new MyStackV3<>();

List<Number> numberList = Arrays.asList(1, 2, 3, 4);
numberStack.pushAll(numberList);
while (!numberStack.isEmpty()) {
    System.out.println(numberStack.pop()); // 4, 3, 2, 1 출력
}

List<Integer> intList = Arrays.asList(1, 2, 3, 4);
numberStack.pushAll(intList); // 컴파일 에러!
```
![image](https://github.com/shin-je-woo/effective-java-group-study/assets/39439576/9e31cb3f-b256-4018-9eb4-12b9885df9b5)
* 논리적으로는 `Number` 를 담는 스택이 `Integer` 도 담을 수 있어야 할 것 같지만, 제네릭에서는 이를 허용하지 않는다.
* 해결방법은???
* `한정적 와일드카드` 를 이용해 이 문제를 해결해보자.

# 💡 PECS: producer - extends
```java
public void pushAllExtends(Iterable<? extends E> src) {
    for (E e : src) {
        push(e);
    }
}
```
* `Iterable<? extends E>` : `E의 Iterable` 이 아니라 `E의 하위 타입의 Iterable` 이라는 뜻
* 이제 스택에 E의 하위 타입이면 모두 push할 수 있게 되었다.
```java
MyStackV3<Number> numberStack = new MyStackV3<>();
List<Integer> intList = Arrays.asList(1, 2, 3, 4);
List<Double> doubleList = Arrays.asList(5.0, 6.0, 7.0);
numberStack.pushAllExtends(intList);
numberStack.pushAllExtends(doubleList);
```
* 위와 같이 `Number` 타입의 스택에 `Integer` , `Double` 타입을 넣어도 오류가 발생하지 않는다.

# 💡 PECS: consumer - super
* 이제 pushAll과 짝을 이루는 popAll 메서드를 작성해보자.
* popAll 메서드는 스택 안의 모든 원소를 주어진 컬렉션으로 옮겨 담는 메서드이다.
* 먼저 한정적 와일카드를 사용하지 않고 작성해보자.
```java
public void popAll(Collection<E> dst) {
    while (!isEmpty()) {
        dst.add(pop());
    }
}
```
* 주어진 컬렉션의 타입과 스택의 타입이 같다면 아무 문제 없이 정상동작 한다.
* 그러나, 타입이 다를 경우 공변성에 의해 컴파일 오류가 발생하게 된다.
* `Number` 타입 스택의 원소를 `Object` 타입의 컬렉션으로 옮기려 한다고 하면 아래 예제코드와 같이 컴파일 에러가 발생한다.
```java
MyStackV3<Number> numberStack = new MyStackV3<>();
numberStack.pushAll(Arrays.asList(1, 2, 3, 4));

ArrayList<Number> numbers = new ArrayList<Number>();
numberStack.popAll(numbers);
for (Number number : numbers) {
    System.out.println(number);
}

ArrayList<Object> objects = new ArrayList<>();
numberStack.popAll(objects); // 컴파일 에러
```
* 논리적으로 생각해보면 Number타입이 Object타입의 하위타입이기 때문에 가능할 것 같지만, 이번에도 역시 제네릭이 불공변하기 때문에 허용하지 않는다.
* 이번에도 `한정적 와일드카드` 를 이용해 이 문제를 해결해보자.
```java
public void popAllSuper(Collection<? super E> dst) {
    while (!isEmpty()) {
        dst.add(pop());
    }
}
```
* `Collection<? super E>` : `E의 Collection` 이 아니라 `E의 상위 타입의 Collection` 이라는 뜻
* 이제 스택에서 꺼내쓰는 것도 유연한 타입을 사용할 수 있게 되었다.
```java
MyStackV3<Number> numberStack = new MyStackV3<>();
numberStack.pushAll(Arrays.asList(1, 2, 3, 4));

ArrayList<Number> numbers = new ArrayList<Number>();
numberStack.popAll(numbers);

ArrayList<Object> objects = new ArrayList<>();
numberStack.popAllSuper(objects);
```

#### 📌 제네릭의 유연성을 극대화하려면 원소의 생산자나 소비자용 입력 매개변수에 와일드카드 타입을 사용하라.
> 참고)   
> 입력 매개변수가 생산자와 소비자 역할을 동시에 한다면 타입을 정확히 지정해야 하는 상황으로 와일드카드 타입을 쓰면 안된다.

# 💡 PECS 공식 (producer-extends, consumer-super)
* PECS 공식은 와일드카드를 정의하는 상황을 이해하는데 도움이 된다.
* 매개변수화 타입 T가 생산자( `producer` )라면 `<? extends T>` 를 사용
* 매개변수화 타입 T가 소비자( `consumer` )라면 `<? super T>` 를 사용
* 주의! 앞서 얘기했지만, 입력 매개변수가 생산자와 소비자역할을 모두 수행한다면 와일드카드 적용 X
* 이 공식을 기억하고, 아이템30에서 봤던 union 메서드를 다시 살펴보자.
```java
public static <E> Set<E> union(Set<E> set1, Set<E> set2) {
    Set<E> result = new HashSet<>(set1);
    result.addAll(set2);
    return result;
}
```
* 입력 매개변수인 `set1` 과 `set2` 모두 E의 생성자(producer)이므로 PECS공식에 따라 다음과 같이 선언해야 한다.
```java
public static <E> Set<E> union(Set<? extends E> set1, Set<? extends E> set2)
```
* 주의! **반환타입에는 한정적 와일드카드 타입을 사용하면 안된다.**
* 클라이언트에서도 와일드카드 타입을 써야하기 때문에 오히려 유연성이 떨어질 수 있다.

# 💡 메서드 선언에 타입 매개변수가 한 번만 나오면 와일드카드로 대체하라
* 타입 매개변수와 와일드카드에는 공통되는 부분이 많아서, 메서드를 정의할 때 둘 중 어느 것을 사용해도 괜찮을 때가 있다.
```java
public static <E> void swap(List<E> list, int i, int j) // 타입 매개변수 
public static void swap(List<?> list, int i, int j)    // 와일드카드
```
* 위 두 메서드는 둘다 큰 문제는 없다.
* 때문에 기본적으로 메서드 선언에 타입 매개변수가 한번만 등장한다면 와일드 카드로 대체한다.
* 이 경우 타입 매개변수가 한 번만 등장하므로 2번 메서드가 낫다.
* 단, 두번째 swap은 다음과 같은 문제점이 있다.
```java
public static void swap(List<?> list, int i, int j) {
	list.set(i, list.set(j, list.get(i)));
}
```
* 위 코드는 컴파일 에러가 발생한다. 
* 비한정적 와일드카드 타입 `List<?>` 에는 `null` 외에는 값을 넣을 수 없기 때문이다.
* 이를 해결하기 위한 가장 좋은 방법은 와일드카드 타입을 실제 타입으로 바꿔주는 `private 도우미 메서드`이다.
```java
public static void swap(List<?> list, int i, int j) {
    swapHelper(list, i, j);
}

private static <E> void swapHelper(List<E> list, int i, int j) {
    list.set(i, list.set(j, list.get(i)));
}
```
* 이제 도우미 메서드가 타입을 알고 있기 때문에 컴파일에러 없이 동작한다.
