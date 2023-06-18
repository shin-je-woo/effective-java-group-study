package chapter9.item59;

import java.util.*;

public class Test {
    static Random rnd = new Random();
    static int random(int n){
        return Math.abs(rnd.nextInt()) % n;
    }

    public static void main(String[] args) {
        for (int i = 2; i < 1000000000; i++) {
            if((i & (i - 1)) == 0)
                System.out.println(random(i));
        }

        System.out.println(Math.abs(Integer.MIN_VALUE));

    }
}
