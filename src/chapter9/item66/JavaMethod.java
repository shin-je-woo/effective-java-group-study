package chapter9.item66;

import java.math.BigInteger;

public class JavaMethod {
    public static void main(String[] args) {
        BigInteger num1 = new BigInteger("12345678901234567890");
        BigInteger num2 = new BigInteger("98765432109876543210");

        BigInteger result = num1.add(num2);

        // 결과 출력
        System.out.println("Result: " + result);
    }
}