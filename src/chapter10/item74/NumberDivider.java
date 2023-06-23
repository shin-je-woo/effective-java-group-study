package chapter10.item74;

public class NumberDivider {
    /**
     * 두 숫자를 나눕니다.
     *
     * @param dividend 나누어지는 수 
     * @param divisor  나누는 수
     * @return 나눈 결과
     * @throws IllegalArgumentException 0으로 나눌 때 발생합니다. (검사예외)
     * @throws ArithmeticException    계산 오류가 발생할 때 발생합니다. (비검사예외)
     */
    public static double divide(int dividend, int divisor) throws IllegalArgumentException, ArithmeticException {
        if (divisor == 0) {
            throw new IllegalArgumentException("나누는 수는 0이 될 수 없습니다.");
        }
        return dividend / divisor;
    }
}
