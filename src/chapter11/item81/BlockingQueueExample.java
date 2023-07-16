package chapter11.item81;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        BlockingQueue<String> workingQueue = new LinkedBlockingQueue<>();

        executorService.execute(() -> { // 생산자 태스크
            try {
                int i=0;
                while (true) {
                    i++;
                    workingQueue.put(i + "번 작업");
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.execute(() -> { // 소비자 태스크
            try {
                System.out.println();
                while (true) {
                    String take = workingQueue.take();
                    System.out.println(take);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }
}
