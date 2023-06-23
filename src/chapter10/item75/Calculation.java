package chapter10.item75;

public class Calculation {
    private int dividend;
    private int divisor;

    public double divide() {
        try {
            // 나눗셈 연산 수행
            return dividend / divisor;
        } catch (ArithmeticException e) {
            // 실패 순간에 관련된 매개변수와 필드 값 포함
            String message = String.format("나눗셈 오류 발생: dividend=%d, divisor=%d", dividend, divisor);
            throw new ArithmeticException(message);
        }
    }
}