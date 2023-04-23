package chapter1.item06;

public class RomanNumeralsTest {

    public static void main(String[] args) {
        String romanNumeral = "TEST";
        long start1 = System.currentTimeMillis();
        boolean result1 = RomanNumerals.isRomanNumeral(romanNumeral);
        long end1 = System.currentTimeMillis();
        System.out.printf("Result 1: %s, Time elapsed: %d ms\n", result1, end1 - start1);

        long start2 = System.currentTimeMillis();
        boolean result2 = RomanNumerals2.isRomanNumeral(romanNumeral);
        long end2 = System.currentTimeMillis();
        System.out.printf("Result 2: %s, Time elapsed: %d ms\n", result2, end2 - start2);

    }
}