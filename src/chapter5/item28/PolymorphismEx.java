package chapter5.item28;

public class PolymorphismEx {

    private PolymorphismEx() {
        throw new AssertionError();
    }

    public static void swap(Object[] array, int a, int b) {
        Object temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static void main(String[] args) {
        Integer[] intArr = {10, 20, 30};
        PolymorphismEx.swap(intArr, 0, 2);
        for (Integer integer : intArr) {
            System.out.println("integer = " + integer);
        }

        String[] strArr = {"1번", "2번", "3번"};
        PolymorphismEx.swap(strArr, 0, 2);
        for (String s : strArr) {
            System.out.println("s = " + s);
        }

//        List<String>[] stringLists = new List<String>[1]; // 1
//        List<Integer> intList = List.of(42);	            // 2
//        Object[] objects = stringLists;	                // 3
//        objects[0] = intList;                             // 4
//        String s = stringLists[0].get(0);                 // 5
    }

}
