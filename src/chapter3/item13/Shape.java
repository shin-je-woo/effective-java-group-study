package chapter3.item13;

public abstract class Shape implements Cloneable {

    private int area;
    public abstract int getArea();

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

//    @Override
//    protected final Object clone() throws CloneNotSupportedException {
//        throw new CloneNotSupportedException();
//    }
}



