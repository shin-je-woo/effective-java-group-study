package chapter4.item17;

import java.util.ArrayList;

public class ImmutableTestV1 {

    public static void main(String[] args) {

        Member owner = new Member("owner", 30);

        Member member1 = new Member("member1", 10);
        Member member2 = new Member("member2", 20);

        ArrayList<Member> members = new ArrayList<>();
        members.add(member1);
        members.add(member2);

        // TeamV1은 불변인 것처럼 보인다.
        TeamV1 teamV1 = TeamV1.valueOf(10, owner, members);
        owner.setAge(40);
        members.add(new Member("member3", 30));

        // TeamV1 내부의 상태가 변경되었다.
        System.out.println("teamV1.owner.age = " + teamV1.getOwner().getAge()); //40
        System.out.println("teamV1.members.size = " + teamV1.getMembers().size()); //3
    }
}
