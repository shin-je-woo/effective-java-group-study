package chapter1.item03;

import java.util.function.Supplier;

public class Concert {

    public void start(Supplier<Singer> supplier) {
        Singer singer = supplier.get();
        singer.sing();
    }

    public static void main(String[] args) {
        Concert concert = new Concert();
        concert.start(Elvis::getInstance);
    }
}
