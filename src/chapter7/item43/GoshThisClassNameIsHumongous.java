package chapter7.item43;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GoshThisClassNameIsHumongous {
    public static void action() {
        System.out.println("GoshThisClassNameIsHumongous.action");
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(GoshThisClassNameIsHumongous::action);
        service.execute(()-> action());
    }
}