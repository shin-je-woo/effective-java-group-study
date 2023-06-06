package chapter8.item50;

public class Main {
    public static void main(String[] args) {
        IntegerWrapper wrapper = new IntegerWrapper(42);
        IntegerWrapper wrapper2 = new IntegerWrapper(50);
        System.out.println(wrapper.getValue());

        wrapper.setValue(100);
        System.out.println(wrapper.getValue());

        System.out.println(wrapper2.getValue());
    }
}