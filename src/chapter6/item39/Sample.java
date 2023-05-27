package chapter6.item39;

public class Sample {
    @Test public static void m1() {}
    public static void m2() {}
    @Test public static void m3() {
        throw new RuntimeException("땡!");
    }
    @Test public static void m4(int num) {}
    @Test public void m5() {}
    @Test public static void m7() {
        throw new RuntimeException("땡!");
    }
}
