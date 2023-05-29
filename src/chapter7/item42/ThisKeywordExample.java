package chapter7.item42;

public class ThisKeywordExample {
    private String message = "Hello";

    public void test() {
        // 람다 표현식에서의 this
        Runnable lambda = () -> System.out.println(this.message);

        // 익명 클래스에서의 this
        Runnable anonymousClass = new Runnable() {
            private String message = "World";
            @Override
            public void run() {
                System.out.println(this.message);
            }
        };
    }

    public static void main(String[] args) {
        ThisKeywordExample example = new ThisKeywordExample();
        example.test();
    }
}
