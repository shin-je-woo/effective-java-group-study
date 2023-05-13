package chapter4.item20.Skeletal;

public interface Animal {
    void move(); // 추상 메서드
    default void run() { // 디폴트 메서드
        System.out.println("Animal.run");
    }
}

