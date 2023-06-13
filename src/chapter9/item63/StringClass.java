package chapter9.item63;

public class StringClass {
	public static void main(String [] args) {
		long startTime = System.currentTimeMillis();

		// 문자열 연결 코드
		String result = "";
		for (int i = 0; i < 10000; i++) {
		    result += i;
		}

		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;
		System.out.println("연결 시간: " + elapsedTime + "밀리초");
		
	}
}
