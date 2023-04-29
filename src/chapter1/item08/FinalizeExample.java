package chapter1.item08;

public class FinalizeExample {

    public static void main(String[] args) {
        FinalizeObject obj1 = new FinalizeObject("Object 1");
        FinalizeObject obj2 = new FinalizeObject("Object 2");

        obj1 = null; // obj1을 null로 만들어 GC의 대상이 되도록 함
        System.gc(); // GC 호출
        // 대기
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // obj2의 finalize() 메소드 호출 확인
        obj2 = null;
        System.gc(); // GC 호출
    }

    private static class FinalizeObject {

        private String name;

        public FinalizeObject(String name) {
            this.name = name;
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println(name + " is finalized.");
        }
    }
}