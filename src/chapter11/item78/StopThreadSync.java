package chapter11.item78;

import java.util.concurrent.TimeUnit;

public class StopThreadSync {

    private static boolean stopRequested;

    private static synchronized void requestStop() {
        stopRequested = true;
    }

    private static synchronized boolean stopRequest() {
        return stopRequested;
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            int i = 0;
            while (!stopRequest()) {
                i++;
            }
        });

        thread.start();

        TimeUnit.SECONDS.sleep(1);
        requestStop();
    }
}