package chapter4.item20.Skeletal;

public abstract class AbstractAnimal implements Animal {

    @Override
    public void move() {
        System.out.println("AbstractAnimal.move");
    }

    public void makeSound(){}
}