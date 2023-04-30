package chapter3.item14;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.TreeSet;

public class SetTest {
    public static void main(String[] args) {
        final BigDecimal bigDecimal1 = new BigDecimal("1.0");
        final BigDecimal bigDecimal2 = new BigDecimal("1.00");

        final HashSet<BigDecimal> hashSet = new HashSet<>();
        hashSet.add(bigDecimal1);
        hashSet.add(bigDecimal2);

        System.out.println(hashSet.size());

        final TreeSet<BigDecimal> treeSet = new TreeSet<>();
        treeSet.add(bigDecimal1);
        treeSet.add(bigDecimal2);

        System.out.println(treeSet.size());
    }
}
