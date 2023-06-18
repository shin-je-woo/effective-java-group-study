package chapter9.item57;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class Test {
    public static void main(String[] args) {

        Collection<String> c = new HashSet<>();
        Collection<String> c2 = new HashSet<>();
        c.add("1");
        c.add("2");
        c.add("3");

        for (Iterator<String> i = c.iterator(); i.hasNext();){
            String s = i.next();
            System.out.println(s);
        }

        /*for (Iterator<String> i2 = c2.iterator(); i.hasNext();){
            String s = i2.next();
            System.out.println(s);
        }*/

        Iterator<String> i = c.iterator();
        while (i.hasNext()){
            System.out.println(i.next());
        }

        Iterator<String> i2 = c2.iterator();
        while (i.hasNext()){    // 버그
            System.out.println(i2.next());
        }
    }
}
