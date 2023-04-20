package chapter1.item01.subreturn;

public interface Payment {
    void pay();
    static Payment accessPayment(String phone) {
        if ("galaxy".equals(phone)) {
            return new SamsungPay();
        } else if ("iphone".equals(phone)) {
            return new ApplePay();
        } else {
            throw new IllegalStateException("올바르지 않은 기종입니다.");
        }
    }
}
