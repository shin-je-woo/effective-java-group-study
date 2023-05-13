package chapter4.item20.Skeletal;

public class Dog extends AbstractAnimal {

    @Override
    public void makeSound() {
        System.out.println("Dog.makeSound");
    }

    public void sit(){
        System.out.println("Dog.sit");
    }

    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.move(); //골격클래스 재정의
        dog.run(); //인터페이스 디폴트 메서드
        dog.sit(); //자체 메서드
    }

}
