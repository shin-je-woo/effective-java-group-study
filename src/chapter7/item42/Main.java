package chapter7.item42;

abstract class Test {
    abstract void study();
}

public class Main {
    public static void main(String[] args) {
        Test effctive = new Test() {
            @Override
            void study() {
                System.out.println("Effctive");
            }
        };
    }
}