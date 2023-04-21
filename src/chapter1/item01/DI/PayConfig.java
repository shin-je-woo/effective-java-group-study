package chapter1.item01.DI;

import chapter1.item01.subreturn.SamsungPay;

public class PayConfig {

    PayService payService() {
        // 의존관계 주입
        return new PayService(new SamsungPay());
    }

}
