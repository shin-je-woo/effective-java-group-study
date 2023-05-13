package chapter4.item20.Simulate;

public class Dog extends AbstractDog implements Runable{

    private MyRunable myRunable = new MyRunable();

    @Override
    public void run() {
        this.myRunable.run();
    }

    @Override
    public void move() {
        System.out.println("Dog.move");
    }

    @Override
    public void sit() {
        System.out.println("Dog.sit");
    }

    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.move();
        dog.sit();
        dog.run();
    }

    private class MyRunable extends AbstractRun { //내부클래스를 생성하여 다중상속 효과 (시뮬레이트 다중상속)
        @Override
        public void run() {
            System.out.println("MyRunable.run");
        }
    }

}

