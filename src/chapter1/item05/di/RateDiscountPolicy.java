package chapter1.item05.di;

public class RateDiscountPolicy implements DiscountPolicy {

    private final int discountPercent = 10;

    // 10% 할인
    @Override
    public int discount(int price) {
        System.out.println("정액할인이 적용되었습니다.");
        return price * (100 - discountPercent) / 100;
    }
}
