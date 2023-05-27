# 💡 @Override 애너테이션을 일관되게 사용하라

## 메서드 재정의할 때 @Override를 꼭 달아야 할까?
### equals()와 hashCode() 재정의 예시 코드
```java
public class Bigram {
    private final char first;
    private final char second;

    public Bigram(char first, char second) {
        this.first = first;
        this.second = second;
    }

    public boolean equals(Bigram b){
        return b.first == first && b.second == second;
    }

    public int hashCode(){
        return 31 * first + second;
    }

    public static void main(String[] args) {
        Set<Bigram> s = new HashSet<>();
        for (int i=0; i < 10; i++){
            for (char ch = 'a'; ch <= 'z'; ch++){ // 알파벳 26개
                s.add(new Bigram(ch, ch));
            }
        }
        System.out.println(s.size());
    }
}
```
> equals(Bigram)는 매개변수 타입이 다르므로 다중정의를 한 것이다. equals(Object)가 올바르게 재정의한 것이다.

## ✔ @Override 애너테이션 역할
```java
@Override
public boolean equals(Bigram b){
        return b.first == first && b.second == second;
    }
    
// java: method does not override or implement a method from a supertype
```
> @Override 애너테이션을 추가하면 메서드가 슈퍼타입에서 상속받은 메서드를 오버라이드하거나 구현하지 않았다는 컴파일 오류가 발생한다.   
> 애너테이션을 통해 컴파일 단계에서 본래 의도대로 빠르게 수정할 수 있다.

### 올바르게 재정의한 equals 메서드
```java
@Override
public boolean equals(Object o) {
    if (!(o instanceof Bigram)) return false;
    Bigram b = (Bigram) o;
    return b.first == first && b.second == second;
}
```

### 💡마무리
> 상위 클래스의 메서드를 재정의할 때는 모든 메서드에 @Override를 달자.    
> 제외해도 되는 경우는 구체 클래스에서 추상 메서드를 구현할 때 뿐이다.   
> 인터페이스의 메서드를 재정의할 때도 디폴트 메서드와 구분 및 시그니처가 올바른지 확신하기 위해서도 좋다.