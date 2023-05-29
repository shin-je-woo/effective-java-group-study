package chapter7.item43;

import java.util.function.Function;

class MathUtils {
    public static int square(int number) {
        return number * number;
    }
}
public class MethodReferenceExample {
    public static void main(String[] args) {
        Function<Integer, Integer> squareFunction = MathUtils::square;
        int result = squareFunction.apply(5);
        System.out.println(result);
    }
}