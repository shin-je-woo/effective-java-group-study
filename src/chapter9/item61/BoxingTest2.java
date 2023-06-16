package chapter9.item61;

public class BoxingTest2 {

    private int intVal;
    private Integer integerVal;

    public int getIntVal() {
        return intVal;
    }

    public Integer getIntegerVal() {
        return integerVal;
    }

    public static void main(String[] args) {
        BoxingTest2 boxingTest2 = new BoxingTest2();
        int intVal = boxingTest2.getIntVal();
        Integer integerVal = boxingTest2.getIntegerVal();

        System.out.println("intVal = " + intVal); // 0
        System.out.println("integerVal = " + integerVal); // null
    }
}
