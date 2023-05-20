package chapter5.item32;

import java.util.List;

public class Dangerous {

    static void dangerous(List<String>... stringList){
        List<Integer> integers = List.of(42);
        Object[] objects = stringList;
        objects[0] = integers;
        String s = stringList[0].get(0); //ClassCastException
    }

    public static void main(String[] args) {
        List<String> strings = List.of("A");
        dangerous(strings);
    }
}
