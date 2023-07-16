package chapter11.item81;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Executor executor = Executors.newFixedThreadPool(4); // 원하는 스레드 풀을 사용
        int concurrency = 4; // 동시에 실행될 작업자 스레드의 개수를 지정

        Runnable action = () -> {
            System.out.println("작업 수행");
        };

        try {
            long elapsedTime = ConcurrentTimer.time(executor, concurrency, action);
            System.out.println("작업 소요 시간: " + elapsedTime + " ns");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}