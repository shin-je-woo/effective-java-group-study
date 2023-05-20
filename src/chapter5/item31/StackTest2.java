package chapter5.item31;

import java.util.ArrayList;
import java.util.Arrays;

public class StackTest2 {

    public static void main(String[] args) {

        MyStackV3<Number> numberStack = new MyStackV3<>();
        numberStack.pushAll(Arrays.asList(1, 2, 3, 4));

        ArrayList<Number> numbers = new ArrayList<Number>();
        numberStack.popAll(numbers);

        ArrayList<Object> objects = new ArrayList<>();
        numberStack.popAllSuper(objects);
    }
}
