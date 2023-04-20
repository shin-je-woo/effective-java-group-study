package chapter1.item01.subreturn;

public class ApplePay implements Payment {

    @Override
    public void pay() {
        System.out.println("애플페이로 결제합니다.");
    }
}
