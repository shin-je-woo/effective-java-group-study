package chapter10.item76;

import java.util.ArrayList;
import java.util.List;

public class TempExample {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        Integer[] array = list.toArray(new Integer[0]);

        // 배열의 값을 변경
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] + 1;
        }

        // 변경된 배열 출력
        for (int num : array) {
            System.out.println(num);
        }

        // 원래의 리스트는 변하지 않음
        for (int num : list) {
            System.out.println(num);
        }
    }
}