package chapter12.item86;

import java.io.Serializable;

public class MyUser2 implements Serializable {


    private static final long serialVersionUID = -2997024306412495021L;
    private final String name;
    private final int age;

    public MyUser2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
