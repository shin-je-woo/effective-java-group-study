package chapter8.item52;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    // Thread 생성자 호출
    Thread thread = new Thread(System.out::println);

    // ExecutorService의 submit 메서드 호출
    ExecutorService exec = Executors.newCachedThreadPool();
//    exec.submit(System.out::println);
}
