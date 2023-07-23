package chapter12.item88;

import java.util.Date;

public class Attack {
    public static void main(String[] args) {
        MutablePeriod mp = new MutablePeriod();
        Period2 p = mp.period;
        Date pEnd = mp.end;

        pEnd.setYear(78);
        System.out.println(p);

        pEnd.setYear(69);
        System.out.println(p);
    }
}
