package chapter1.item03;

public class Elvis implements Singer{
    public static final Elvis INSTANCE = new Elvis();

    private Elvis() {
    }

    public static Elvis getInstance() {
        //return INSTANCE;
        return new Elvis();
    }

    public void leaveTheBuilding() {
        System.out.println("Elvis.leaveTheBuilding");
    }

    public static void main(String[] args) {
        System.out.println(Elvis.getInstance());
        System.out.println(Elvis.getInstance());
    }

    @Override
    public void sing() {
        System.out.println("나는가수다");
    }
}
