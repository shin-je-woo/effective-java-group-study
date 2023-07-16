package chapter11.item80;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        exec.execute(()-> System.out.println("Hello World!"));
        exec.shutdown();
    }
}
