package chapter6.item41;

import java.io.*;

public class MakerInterfaceTest {
    public static void main(String[] args) {
        File f = new File("test.txt");
        try{
            ObjectOutputStream objOutputStream = new ObjectOutputStream(new FileOutputStream(f));

            // 객체 직렬화 및 출력
            objOutputStream.writeObject(new SomeObject("yoon", "yoon@naver.com"));

            objOutputStream.close();

            System.out.println("객체가 성공적으로 직렬화되었습니다.");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
