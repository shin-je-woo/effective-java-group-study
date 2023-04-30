package chapter3.item13;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class HashSetExample {

    public static void main(String[] args) {
        Set<String> hashSet = new HashSet<>();
        hashSet.add("keesun");
        hashSet.add("whiteship");
        System.out.println("HashSet: " + hashSet);

        Set<Integer> hashSet2 = new HashSet<>();
        hashSet2.add(1);
        hashSet2.add(2);
        System.out.println("HashSet: " + hashSet2);

        Set<String> treeSet = new TreeSet<>(hashSet);
        Set<Integer> treeSet2 = new TreeSet<>(hashSet2);

        System.out.println("TreeSet: " + treeSet);
        System.out.println("treeSet2: " + treeSet2);
    }
}
