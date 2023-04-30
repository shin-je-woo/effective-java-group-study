package chapter1.item09;

import java.io.FileInputStream;
import java.io.IOException;

public class TryFinallyExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		FileInputStream inputStream = null;
		try {
		    inputStream = new FileInputStream("filename.txt");
		    // 파일에서 데이터를 읽는 코드 작성
		} catch (IOException e) {
		    // 예외 처리
		} finally {
		    if (inputStream != null) {
		        try {
		            inputStream.close();
		        } catch (IOException e) {
		            // 예외 처리
		        }
		    }
		}
		
	}
}
