package chapter7.item42;

import java.io.*;


public class LambdaSerializationExample {
    public static void main(String[] args) {
        SerializableLambda lambda = () -> System.out.println("Hello");

        try {
            // 직렬화
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("lambda.txt"));
            out.writeObject(lambda);
            out.close();

            // 역직렬화
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("lambda.txt"));
            SerializableLambda serializableLambda = (SerializableLambda) in.readObject();
            in.close();

            // 실행
            serializableLambda.run();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

@FunctionalInterface
interface SerializableLambda   {
    void run();
}