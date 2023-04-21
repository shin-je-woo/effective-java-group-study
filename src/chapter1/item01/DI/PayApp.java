package chapter1.item01.DI;

public class PayApp {

    public static void main(String[] args) {
        PayConfig payConfig = new PayConfig();
        PayService payService = payConfig.payService();

        payService.doSomething();
    }

}
