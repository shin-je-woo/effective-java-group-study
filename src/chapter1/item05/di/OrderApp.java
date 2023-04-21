package chapter1.item05.di;

public class OrderApp {

    public static void main(String[] args) {
        OrderConfig orderConfig = new OrderConfig();
        OrderService orderService = orderConfig.orderService();

        orderService.orderItem(20000);
    }

}
