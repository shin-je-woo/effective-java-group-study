package chapter1.item05.di;

public class OrderConfig {

    OrderService orderService() {
        // 의존관계 주입
        return new OrderService(new FixDiscountPolicy());
    }

}
