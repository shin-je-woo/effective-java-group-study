package chapter7.item43;

import chapter7.item42.TestInterface;

import java.util.function.Supplier;

interface G1 {
    <E extends Exception> Object m() throws E;
}

interface G2 {
    <F extends Exception> String m() throws Exception;
}

interface G extends G1, G2 {
    <F extends Exception> String m() throws F;
}

public class GenericFunction {
    public static <F extends Exception> String myMethod() throws F {
        return "Result";
    }

    public static void main(String[] args) {
        // 제네릭 함수 타입의 구현 메서드 참조
        G myMethod = GenericFunction::myMethod;
        System.out.println("myMethod = " + myMethod.m());
    }
}
