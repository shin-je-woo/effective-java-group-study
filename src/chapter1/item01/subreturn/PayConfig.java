package chapter1.item01.subreturn;

public class PayConfig {

    PayService payService() {
        return new PayService(new SamsungPay());
    }

}
