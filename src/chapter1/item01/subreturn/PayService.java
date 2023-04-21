package chapter1.item01.subreturn;

public class PayService {

    private Payment payment;

    public PayService(Payment payment) {
        this.payment = payment;
    }

    public void doSomething() {
        // payService입장에서는 pay()에서 어떤 결제방식인지(삼성페이, 애플페이..)모른다.
        // 외부(PayConfig)에서 Payment의 구현체를 주입(의존관계 주입) 해주었기 때문에 추상화(Payment인터페이스)에 의존하여 자신의 역할을 수행할 수 있다.
        // 이렇게 되면 클라이언트(PayService)의 수정 없이(OCP원칙) 클라이언트의 일관성을 지킬 수 있다.
        payment.pay();
    }
}
