package chapter12.item85;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public final class MyUser implements Serializable {

    private final String name;
    private final int age;

    public MyUser(String name, int age) {
        if (age < 30) {
            throw new IllegalArgumentException("30살 이상만 생성할 수 있다.");
        }
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public static byte[] getSerializedObject(String name, int age) {
        byte[] bytes;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            MyUser myUser = new MyUser(name, age);
            objectOutputStream.writeObject(myUser);
            bytes = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bytes;
    }

    @Override
    public String toString() {
        return "MyUser{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
