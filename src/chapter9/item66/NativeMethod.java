package chapter9.item66;

public class NativeMethod {
    // 네이티브 메서드 선언
    private native int performCalculation(int num1, int num2);

    // 동적 라이브러리 파일 로드
    static {
        System.loadLibrary("native_library");
    }

    public static void main(String[] args) {
        NativeMethod example = new NativeMethod();

        int result = example.performCalculation(10, 20);

        // 결과 출력
        System.out.println("Result: " + result);
    }
}