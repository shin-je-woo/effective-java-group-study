package chapter1.item08;

import java.lang.ref.Cleaner;

public class CleanerExample {

    public static void main(String[] args) throws Exception {
        try (CleanerObject obj1 = new CleanerObject("Object 1");
             CleanerObject obj2 = new CleanerObject("Object 2")) {
            // do something
        }
    }

    private static class CleanerObject implements AutoCloseable {

        private String name;
        private Cleaner.Cleanable cleanable;

        public CleanerObject(String name) {
            this.name = name;
            this.cleanable = Cleaner.create().register(this, () -> System.out.println(this.name + " is cleaned."));
            
            //() -> 람다식을 이용해 Runnable 구현
        }

        @Override
        public void close() throws Exception {
            System.out.println(name + " is closed.");
            cleanable.clean();
        }
    }
}