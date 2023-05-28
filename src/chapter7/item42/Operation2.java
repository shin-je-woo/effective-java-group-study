package chapter7.item42;

import java.util.function.DoubleBinaryOperator;

public enum Operation2 {
    //각 열거 타입 상수의 동작을 람다로 구현해 생성자에 넘김.
    PLUS  ("+", (x, y) -> x + y),
    MINUS ("-", (x, y) -> x - y),
    TIMES ("*", (x, y) -> x * y),
    DIVIDE("/", (x, y) -> x / y);

    private final String symbol;
    //java.util.function의 함수 인터페이스
    //Double 타입 인수 2개를 받아 Double 타입 결과를 돌려줌.
    private final DoubleBinaryOperator op;

    //생성자는 람다를 인스턴스 필드로 저장 (DoubleBinaryOperator 인터페이스 변수에 할당)
    Operation2(String symbol, DoubleBinaryOperator op) {
        this.symbol = symbol;
        this.op = op;
    }

    @Override public String toString() { return symbol; }

    //apply 메서드에서 필드에 저장된 람다를 호출한다.
    public double apply(double x, double y) {
        return op.applyAsDouble(x, y);
    }
}