package chapter1.item06;

public class AutoBoxing {
    public static void main(String[] args) {
        long startTime1 = System.currentTimeMillis();
        long result1 = sumUsingLong();
        long endTime1 = System.currentTimeMillis();

        long startTime2 = System.currentTimeMillis();
        long result2 = sumUsinglong();
        long endTime2 = System.currentTimeMillis();

        System.out.println("Using Long: " + result1 + " in " + (endTime1 - startTime1) + "ms");
        System.out.println("Using long: " + result2 + " in " + (endTime2 - startTime2) + "ms");
    }

    private static Long sumUsingLong() {
        Long sum = 0L;
        for (long i = 0; i <= Integer.MAX_VALUE; i++) {
            sum += i;
        }
        return sum;
    }

    private static long sumUsinglong() {
        long sum = 0L;
        for (long i = 0; i <= Integer.MAX_VALUE; i++) {
            sum += i;
        }
        return sum;
    }
}