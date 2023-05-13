package chapter4.item21.runtimeEx;

public interface test {
    default void test(){
        System.out.println("test.test");
    }
}
