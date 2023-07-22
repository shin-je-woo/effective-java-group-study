package chapter12.item86;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class CapsuleTest {

    public static void main(String[] args) {

        MyUser2 jewoo = new MyUser2("jewoo", 30);
        try (FileOutputStream fileOutputStream = new FileOutputStream("serialiezedFile.txt")) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(jewoo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
