package chapter5.item26;

import java.util.ArrayList;
import java.util.List;

public class UnboundedWildcardExample {
    public static void main(String[] args) {
        List<?> list = new ArrayList<>();
        list = new ArrayList<String>(); // OK
        list = new ArrayList<Integer>(); // OK
        list = new ArrayList<Object>(); // OK

        //list.add("String"); 
        //list.add(1);  
        //list.add(new Object()); 
        //list.add(null);   

        printList(list);
    }

    private static void printList(List<?> list) {
        for (Object o : list) {
            System.out.println(o);
        }
    }
}