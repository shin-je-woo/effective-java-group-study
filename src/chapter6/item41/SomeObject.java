package chapter6.item41;

import java.io.Serializable;

public class SomeObject implements Serializable {
    private String name;
    private String email;

    public SomeObject(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
