package chapter3.item11.hash;

import java.util.HashMap;

public class HashCodeTest {

    public static void main(String[] args) {

        HashMap<Member, String> map = new HashMap<Member, String>();
        Member member = new Member("jewoo", 30);
        map.put(member, "member.get() 하면 값이 나올까요?");

        System.out.println(map.get(new Member("jewoo", 30)));
    }
}
