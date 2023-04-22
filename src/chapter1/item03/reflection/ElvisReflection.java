package chapter1.item03.reflection;

public class ElvisReflection {
    public static final ElvisReflection INSTANCE = new ElvisReflection();

    private ElvisReflection() {
        if (INSTANCE != null) {
            throw new IllegalStateException();
        }
    }
    public static ElvisReflection getInstance() {
        return INSTANCE;
    }

    public void leaveTheBuilding() {
    }

}
