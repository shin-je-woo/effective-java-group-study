# 💡 private 생성자나 열거 타입으로 싱글턴임을 보증하라

## 1. 싱글턴

### 1) 싱글턴이란?
* 인스턴스를 오직 하나만 생성할 수 있는 클래스
* 전형적인 예로는 무상태 객체 및 설계상 유일해야 하는 시스템 컴포넌트를 들 수 있다.

### 1-1) 무상태 객체
* 상태(state)를 갖지 않는 객체
* 객체의 필드에 저장된 값이 변하지 않으며, 메서드의 반환값만이 주어진 입력에 의해 결정된다.
* 일반적으로 다수의 스레드에서 안전하게 공유될 수 있으며, 동시성 문제를 방지하는 데에 용이하다.
* 대표적인 예시로는 Math 클래스가 있다.
```java
/**
 * PI 변수는 final이기에 불변상태로 해당 클래스는 무상태 클래스
 */
public final class Math {
    private Math() {}
    
    public static final double PI = 3.14159265358979323846;
    
    public static double sin(double a) {
        // 사인 함수를 계산하는 코드
    }
    public static double cos(double a) {
        // 코사인 함수를 계산하는 코드
    }
}
```
### 1-2) 유일해야 하는 시스템 컴포넌트
 * 예시로는 Logger, Configuration등이 있음
```java
public class Logger {
// 싱글턴 패턴을 적용하기 위해 private static 필드 선언
private static final Logger INSTANCE = new Logger();

    // 생성자를 private으로 선언하여 외부에서 인스턴스 생성을 막음
    private Logger() {}
    
    // 외부에서 Logger 클래스의 인스턴스에 접근할 수 있는 메서드
    public static Logger getInstance() {
        return INSTANCE;
    }
}
```

## 2. 싱글턴을 만드는 방식과 문제점
### 1) public static final 필드 방식의 싱글턴
* public static 멤버가 final 필드인 방식
* 생성자는 private로 보호되며 Elvis.INSTANCE를 초기화할 때 딱 1번 호출되어 
Elvis 클래스가 초기화될 때 만들어진 인스턴스가 싱글턴임을 보장.

```java
public class Elvis {
public static final Elvis INSTANCE = new Elvis();

    private Elvis() {}
}
```

### 1-1) 장점
* 해당 클래스가 싱글턴임이 API에 명백히 드러남
* 보다 간결함

### 2) 정적 팩터리 방식의 싱글턴
* public static 멤버가 정적 팩터리 메서드인 방식
* Elvis.getInstance는 항상 같은 객체의 참조를 반환함
즉, 매개변수를 받지 않는 public 생성자가 만들어지며, 사용자는 코드만 보고 이 생성자가 자동 생성된 것인지 구분할 수 없다.
* 추상 클래스로 만든다고해도 하위 클래스로 인스턴스화를 할 수 있기에 막을 수 없다. 

```java
public class Elvis {
public static final Elvis INSTANCE = new Elvis();

    private Elvis() {}

    public static Elvis getInstance() {
        return INSTANCE;
    }
}
```

### 2-1) 장점
* API를 바꾸지 않고도 싱글턴이 아니게 변경 가능하다.
  * Elvis 클래스의 정적 팩터리 메서드 getInstance()에서 반환하는 객체를 다른 객체로 변경하더라도
  Elvis 클래스의 클라이언트 코드들은 변경 없이 여전히 getInstance() 메서드를 호출할 수 있으며, 
  반환되는 객체는 새로운 클래스의 인스턴스로 변경된다는 것

```java  
public class Elvis {
    public static final Elvis INSTANCE = new Elvis();

    private Elvis() {
    }

    public static Elvis getInstance() {
        //return INSTANCE;
        return new Elvis();
    }

    public void leaveTheBuilding() {
        System.out.println("Elvis.leaveTheBuilding");
    }

    public static void main(String[] args) {
        System.out.println(Elvis.getInstance());
        System.out.println(Elvis.getInstance());
    }
}
```

* 두 번째 장점은 원한다면 정적 팩터리를 제네릭 싱글턴 팩터리로 만들 수 있다.
```java  
public class GenericElvis<T> {
    public static final GenericElvis<Object> INSTANCE = new GenericElvis<>();

    private GenericElvis() {
    }

    public static <T> GenericElvis<T> getInstance() {
        return (GenericElvis<T>) INSTANCE;
    }

    public void leaveTheBuilding() {
        System.out.println("GenericElvis.leaveTheBuilding");
    }

    public static void main(String[] args) {
        GenericElvis<String> instance = GenericElvis.getInstance();
        GenericElvis<Integer> instance2 = GenericElvis.getInstance();
        System.out.println("instance = " + instance);
        System.out.println("instance2 = " + instance2);
    }
}
```
* 정적 팩터리의 메서드 참조를 공급자로 사용할 수 있다.
```java  
public interface Singer {
    void sing();
}
```
```java  
public class Concert {

    public void start(Supplier<Singer> supplier) {
        Singer singer = supplier.get();
        singer.sing();
    }

    public static void main(String[] args) {
        Concert concert = new Concert();
        concert.start(Elvis::getInstance);
    }
}
```
### 3) 위 2개의 방식의 한계점
### 3-1) 리플렉션 API의 private 생성자 접근
* 리플렉션 API : 클래스의 정보를 검사하고 조작하는 자바 API.
* AccessibleObject.setAccessible을 사용하여 private 생성자를 호출할 수 있다.
```java
import java.lang.reflect.Constructor;  

public class ReflectionAttack {
      public static void main(String[] args) throws Exception {
      Constructor<Elvis> constructor = Elvis.class.getDeclaredConstructor();
      constructor.setAccessible(true);
      Elvis elvis1 = constructor.newInstance();
      Elvis elvis2 = Elvis.getInstance();
      System.out.println(elvis1 == elvis2); // false
      }
  }
```

* 이러한 공격을 방어하기 위해서는 생성자를 수정하여, 두번째 객체가 생성되려할때 예외를 던지면 된다.

```java
public class Elvis {
    private static final Elvis INSTANCE = new Elvis();
    
    private Elvis() {
        if (INSTANCE != null) {
            throw new IllegalStateException();
        }
    }

    public static Elvis getInstance() {
        return INSTANCE;
    }
}
```


### 3-2) 직렬화 시 단순히 Serializable 구현한다고 선언하는 것만으로는 부족
* 직렬화 : 객체의 상태를 네트워크 전송등을 위해 바이트 스트림으로 변환하는 기술 (Serializable 구현으로 사용)
* 역직렬화를 할 때 직렬화된 객체의 정보를 읽어 새로운 객체가 생성하게 됨.
* 이를 방지하기 위해서는 readResolve() 메서드롤 구현하여 싱글톤 객체를 return하면 된다.

```
private Object readResolve(){
    // 역직렬화 시 자동으로 호출
    return INSTANCE;
}
```



### 4) 열거 타입 방식의 싱글턴 (대부분 상황에서 가장 좋은 방법)
```java
public enum ElvisEnum {
  INSTANCE;

  public void leaveTheBuilding(){

  }
  public static void main(String[] args) {
    ElvisEnum instance = ElvisEnum.INSTANCE;
  }
}
```
* 원소가 하나인 열거 타입을 선언한다. 더 간결하고 추가 노력 없이 직렬화 할 수 있음
* 리플렉션 내부코드에 enum 예외처리가 되어 있어 리플렉션 공격에서도 제2의 인스턴스가 생기는 일을 완벽히 막아준다.
```java
//라인 실행 시 오류  
Constructor<ElvisEnum> declaredConstructor = ElvisEnum.class.getDeclaredCo
```
* 역직렬화시에도 같은 객체를 반환해줌 
```java
public class EnumElvisSerialization {

  public static void main(String[] args) {
    try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream("elvis.obj"))) {
      out.writeObject(ElvisEnum.INSTANCE);
    } catch (IOException e) {
      e.printStackTrace();
    }

    try (ObjectInput in = new ObjectInputStream(new FileInputStream("elvis.obj"))) {
      ElvisEnum elvis = (ElvisEnum) in.readObject();
      System.out.println(elvis == ElvisEnum.INSTANCE);
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}
```
* 단, 만들려는 싱글턴이 Enum 외의 클래스를 상속해야 한다면 사용불가하다. (열거 타입이 다른 인터페이스를 구현하도록 선언할 수는 있다)

