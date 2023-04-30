package chapter1.item09;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TryWithResourceExample {
	public static void main (String[] args) {
		
		try (FileInputStream inputStream = new FileInputStream("filename.txt");
				FileOutputStream outputStream = new FileOutputStream("filename.txt")
				) {
		    // 파일에서 데이터를 읽는 코드 작성
		} catch (IOException e) {
		    // 예외 처리
		}
	}
}
