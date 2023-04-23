# 💡 다 쓴 객체 참조를 해제하라
### 가비지 컬렉터가 있지만 메모리 관리가 필요한 이유
* 가비지 컬렉터(GC)가 놓치는 참조(Reference)가 존재한다.   
Reference counting(래퍼런스 카운팅 또는 참조횟수)가 하나라도 있으면 GC의 대상이 안된다.   
즉, 객체 참조 하나를 살려두면 그 객체뿐 아니라 그 객체가 참조하는 모든 객체를 회수하지 못한다.   
이로 인해 누적된 메모리 누수로 성능 저하나 심하면 OutOfMemoryError를 일으킬 수 있다.

### 참조 해제 해법
* null 처리로 명시적 해제를 해준다.   
참조를 다 썼을 때 null 처리(참조 해제)를 하여 GC의 대상이 된다.

  
### 메모리 누수를 막는 캐시 이용법
1. 백그라운드 스레드(ScheduledThreadPoolExecutor 같은)를 활용한다.
   - 어떤 작업을 일정 시간 지연 후에 수행하거나, 일정 시간 간격으로 주기적으로 실행할 때 사용   


2. 새 엔트리를 추가할 때 부수 작업으로 수행하는 방법으로 엔트리를 청소한다.
   - LRU(Leatest Recently Used) : 가장 오랫동안 참조하지 않은 페이지를 제거하는 알고리즘으로 메모리를 유지하는 방법이다.
```java
public class LinkedHashMapTest {
    public static void main(String[] args) {
        final int MAX_ENTRIES = 5;
        Map<Integer, String> map = new LinkedHashMap<>(MAX_ENTRIES){

            // LRU 페이지 알고리즘 API 제공
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
                return size() > MAX_ENTRIES;
            }
        };
```

3. WeakHashMap을 사용해 캐시를 만든다.
   - 사전지식 - 약한 참조(weak reference)
   - WeakReference 객체를 이용하며 `myNumber`에 null이 되면 GC 대상이 된다.
   - `메모리가 부족하지 않아도` 가비지 컬렌션이 이루어진다.
```java
// 약한 참조(weak reference)
    Integer myNumber = 10;
    WeakReference<Integer> weakReference = new WeakReference<>(myNumber);
```
  - WeakHashMap 자료구조는 자바의 Reference 종류 중 WeakReference 특성을 이용하는 HashMap이다.
  - 넣어놨던 key의 값이 null이 된다면, 해당 EntrySet은 가비지 컬렌션 처리된다.
```java
// WeakReference를 이용하는 HashMap
        Map<User, String> map = new WeakHashMap<>();

        User key1 = new User();
        User key2 = new User();

        map.put(key1, "key1");
        map.put(key2, "key2");

        key1 = null;
```
  - 주의점 - String은 new가 아닌 리터럴 방식으로 생성된 경우 (String a = "abc";) Java String Constant Pool이 참조하고 있기 때문에   
  key가 null이여도 GC 대상이 아니다. 이는 Integer와 같은 Class의 (-127 ~ 128) 값들도 마찬가지다.   
  JVM은 상수 풀(pool)에 해당 값을 보관하고 있다.
  - 해결방안: 클래스로 래핑하는 방식을 사용한다.