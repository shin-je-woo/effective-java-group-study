package chapter1.item05.di;

public class FixDiscountPolicy implements DiscountPolicy {

    private final int discountAmount = 1000;

    // 1000원 고정 할인
    @Override
    public int discount(int price) {
        System.out.println("고정할인이 적용되었습니다.");
        return price - discountAmount;
    }
}
