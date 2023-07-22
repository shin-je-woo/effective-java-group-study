package chapter12.item85;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;

public class serializableTest {

    public static void main(String[] args) {

        // 직렬화 -> 역직렬화
        byte[] serializedMyUser = MyUser.getSerializedObject("jewoo", 30);
        System.out.println("serializedMyUser = " + Arrays.toString(serializedMyUser));
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(serializedMyUser)) {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            MyUser myUser = (MyUser) objectInputStream.readObject();
            System.out.println("myUser = " + myUser);
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }

        // 역직렬화 할 때 생성자의 제약 조건을 무시해버린다!
        serializedMyUser[81] = 20;
        try (ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(serializedMyUser)) {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream2);
            MyUser myUser = (MyUser) objectInputStream.readObject();
            System.out.println("myUser = " + myUser);
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }

    }
}
