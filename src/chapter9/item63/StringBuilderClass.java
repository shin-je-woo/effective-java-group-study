package chapter9.item63;

public class StringBuilderClass {
	public static void main(String [] args) {
		long startTime = System.currentTimeMillis();

		// StringBuilder를 사용한 문자열 연결 코드
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < 10000; i++) {
		    result.append(i);
		}
		String finalResult = result.toString();

		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;
		System.out.println("연결 시간: " + elapsedTime + "밀리초");
	}
}
