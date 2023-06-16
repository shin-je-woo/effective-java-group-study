package chapter9.item61;

public class BoxingTest4 {

    private Integer integerVal;

    public Integer getIntegerVal() {
        return integerVal;
    }

    public static void main(String[] args) {
        BoxingTest4 boxingTest4 = new BoxingTest4();
        if (boxingTest4.getIntegerVal() > 0) { // NullPointerException 발생!!
            System.out.println("0보다 큰 값입니다.");
        }
    }
}
