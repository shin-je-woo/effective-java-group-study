package chapter9.item60;

import java.math.BigDecimal;

public class BigDecimalTest {

    public static void main(String[] args) {
        BigDecimal budget = new BigDecimal("1.55");
        BigDecimal stock = new BigDecimal("1.35");
        BigDecimal result = budget.subtract(stock);
        System.out.println("budget - stock = " + result);

    }
}
