package chapter5.item30;

import java.util.function.BinaryOperator;

public class GenericSingletonFactory {

    private static final BinaryOperator<Object> FIRST = (t1, t2) -> t1;

    // FIRST를 제네릭으로 변환해주는 정적팩토리 메서드
    // 제네릭이 아니라면 findFirst 메서드를 타입별로 만들어야 함
    @SuppressWarnings("unchecked")
    public static <T> BinaryOperator<T> findFirst() {
        return (BinaryOperator<T>) FIRST;
    }

    public static void main(String[] args) {
        BinaryOperator<String> firstFuncString = findFirst();
        String[] stringArr = {"A", "B", "C"};
        String strVal = "D";
        Integer er = 1;
        for (String s : stringArr) {
            System.out.println(firstFuncString.apply(s, strVal));
        }

        BinaryOperator<Integer> firstFuncInteger = findFirst();
        int[] intArr = {1, 2, 3};
        int intVal = 4;
        for (int i : intArr) {
            System.out.println(firstFuncInteger.apply(i, intVal));
        }
    }
}
