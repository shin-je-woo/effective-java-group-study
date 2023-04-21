package chapter1.item05.di;

public class OrderService {

    private final DiscountPolicy discountPolicy;

    public OrderService(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    public void orderItem(int itemPrice) {
        // OrderService 입장에서는 discount()가 어떤 할인방식인지(정액할인 or 고정할인) 모른다.
        // 외부(OrderConfig)에서 DiscountPolicy 의 구현체를 주입(의존관계 주입) 해주었기 때문에 추상화에 의존하여 자신의 역할을 수행할 수 있다.
        // 이렇게 되면 클라이언트(OrderService)의 수정 없이(OCP원칙) 다양한 할인정책을 도입할 수 있다.
        int discountedPrice = discountPolicy.discount(itemPrice);
        System.out.println("아이템 가격 = " + itemPrice);
        System.out.println("할인된 가격 = " + discountedPrice);
    }
}
