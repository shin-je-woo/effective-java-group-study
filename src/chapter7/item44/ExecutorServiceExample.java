package chapter7.item44;
import java.util.concurrent.*;

public class ExecutorServiceExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Callable을 사용한 submit
        executorService.submit((Callable<String>) () -> {
            return null;
        });

        // Runnable을 사용한 submit
       executorService.submit((Runnable)() -> {
        });

    }
}

