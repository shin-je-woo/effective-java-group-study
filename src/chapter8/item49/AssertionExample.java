package chapter8.item49;

public class AssertionExample {
    public static void main(String[] args) {
        int value = -1;
        assert value > 0 : "value는 양수여야 합니다."; // value가 0보다 작은 경우 예외 발생
        
        System.out.println("프로그램이 정상적으로 실행되었습니다.");
    }
}
