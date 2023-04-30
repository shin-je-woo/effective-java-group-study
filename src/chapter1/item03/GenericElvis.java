package chapter1.item03;

public class GenericElvis<T> {
    public static final GenericElvis<Object> INSTANCE = new GenericElvis<>();

    private GenericElvis() {
    }

    public static <T> GenericElvis<T> getInstance() {
        return (GenericElvis<T>) INSTANCE;
    }

    public void leaveTheBuilding() {
        System.out.println("GenericElvis.leaveTheBuilding");
    }

    public static void main(String[] args) {
        GenericElvis<String> instance = GenericElvis.getInstance();
        GenericElvis<Integer> instance2 = GenericElvis.getInstance();
        System.out.println("instance2 = " + instance2);
        System.out.println("instance = " + instance);
    }
}
