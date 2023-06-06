package chapter8.item50;

import java.util.Date;

public class Attack {
    public static void main(String[] args) {
        safeAttack1();
        attack2();
    }

    private static void attack1() {
        Date start = new Date();
        Date end = new Date();
        Period p = new Period(start, end);
        end.setYear(78);  // p의 내부를 변경했다!
        System.out.println(p);
    }

    private static void safeAttack1() {
        Date start = new Date();
        Date end = new Date();
        SafePeriod p = new SafePeriod(start, end);
        end.setYear(78);  // p의 내부를 변경했다!
        System.out.println(p);
    }

    private static void attack2() {
        Date start = new Date();
        Date end = new Date();
        Period p = new Period(start, end);
        p.end().setYear(78);  // p의 내부를 변경했다!
        System.out.println(p);
    }
}
