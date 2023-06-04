# 💡 null이 아닌, 빈 컬렉션이나 배열을 반환하라

### null 반환의 단점
- null을 반환하는 메서드를 사용하면 항상 방어 코드를 넣어야 한다.

### 빈 컬렉션과 배열 반환하는 예
성능 최적화를 위해 매번 똑같은 빈 불변 객체를 반환하게 하였다.
```java
public List<User> getUsers(){
    return users.isEmpty() ? Collections.emptyList()
        : new ArrayList<>(users);
}
```
```java
private static final User[] EMPTY_USER_ARRAY = new User[0];
public User[] getUsers(){
    //return users.toArray(EMPTY_USER_ARRAY);
    return users.toArray(new User[user.size()]);
}
```
> toArray에 넘기는 배열을 미리 할당하는 건 성능이 떨이질 수 있기 때문에 바로 생성해서 넘겨주는 걸 추천한다.    

### 💡마무리
> null을 반환하는 API는 사용하기 어렵고 오류 처리 코드가 늘어난다. 그렇다고 성능이 좋지도 않다.