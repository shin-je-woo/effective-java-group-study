package chapter1.item01.subreturn;

public class SamsungPay implements Payment {
    @Override
    public void pay() {
        System.out.println("삼성페이로 결제합니다.");
    }
}
