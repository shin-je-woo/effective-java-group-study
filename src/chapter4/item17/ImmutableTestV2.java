package chapter4.item17;

import java.util.ArrayList;

public class ImmutableTestV2 {

    public static void main(String[] args) {

        Member owner = new Member("owner", 30);

        Member member1 = new Member("member1", 10);
        Member member2 = new Member("member2", 20);

        ArrayList<Member> members = new ArrayList<>();
        members.add(member1);
        members.add(member2);

        TeamV2 teamV2 = TeamV2.valueOf(10, owner, members);
        owner.setAge(40);
        members.add(new Member("member3", 30));

        // TeamV2는 생성할 때 방어적 복사를 수행했기 때문에 변경X
        System.out.println("teamV2.owner.age = " + teamV2.getOwner().getAge()); //30
        System.out.println("teamV2.members.size = " + teamV2.getMembers().size()); //2

        // But, members리스트 안의 요소들은 얕은 복사였기 때문에 변경가능성이 있다.
        member1.setAge(100);

        //TeamV2의 getMember는 Collections.unmodifiableList를 반환하기 때문에 add와 같은 변경자가 실행되면 UnsupportedOperationException이 발생한다.
        teamV2.getMembers().add(owner);
    }
}
