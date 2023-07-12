package chapter11.item78;

import java.util.concurrent.TimeUnit;

public class StopThread {

    private static boolean stopRequest;

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            int i = 0;
            while (!stopRequest) {
                i++;
            }
        });

        thread.start();

        TimeUnit.SECONDS.sleep(1);
        stopRequest = true;
    }
}