package chapter1.item01.subreturn;

public class Example {

    public static void main(String[] args) {

        // 추상화에 의존할 수 있는 장점을 가진다.
        Payment galaxy = Payment.accessPayment("galaxy");
        galaxy.pay();

        Payment iphone = Payment.accessPayment("iphone");
        iphone.pay();

        // 개발자는 구체화에 의존하지 않고 추상화된 개념에 의존할 수 있다. SOLID - DIP
    }
}
