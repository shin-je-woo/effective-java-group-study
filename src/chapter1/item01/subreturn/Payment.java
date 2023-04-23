package chapter1.item01.subreturn;

import java.lang.reflect.Constructor;

public interface Payment {

    void pay();

    static Payment accessPayment(String phone) {
        if ("galaxy".equals(phone)) {
            return new SamsungPay();
        } else if ("iphone".equals(phone)) {
            return new ApplePay();
        } else if ("kakaopay".equals(phone)) {
            Payment kakaoPay = null;
            try {
                Constructor<?> kakaoPayConstructor = Class.forName("org.kakao.sample.KakaoPay").getConstructor();
                kakaoPay = (Payment) kakaoPayConstructor.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return kakaoPay;
        } else {
            throw new IllegalStateException("올바르지 않은 기종입니다.");
        }
    }
}
