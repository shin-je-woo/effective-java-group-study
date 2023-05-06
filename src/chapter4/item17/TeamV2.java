package chapter4.item17;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class TeamV2 {

    private final int ranking;
    private final Member owner;
    private final List<Member> members;

    // 생성자에서 참조타입필드는 방어적 복사
    private TeamV2(int ranking, Member owner, List<Member> members) {
        this.ranking = ranking;
        this.owner = new Member(owner.getName(), owner.getAge());
        this.members = new ArrayList<>(members);
    }

    public static TeamV2 valueOf(int ranking, Member representative, List<Member> members) {
        return new TeamV2(ranking, representative, members);
    }

    public int getRanking() {
        return ranking;
    }

    public Member getOwner() {
        return new Member(owner.getName(), owner.getAge());
    }

    // get메소드는 방어적 복사 or Collections.unmodifiableList 이용
    public List<Member> getMembers() {
        //return new ArrayList<>(members);
        return Collections.unmodifiableList(members);
    }
}
