package chapter4.item17;

import java.util.List;

public final class TeamV1 {

    private final int ranking;
    private final Member owner;
    private final List<Member> members;

    private TeamV1(int ranking, Member owner, List<Member> members) {
        this.ranking = ranking;
        this.owner = owner;
        this.members = members;
    }

    public static TeamV1 valueOf(int ranking, Member representative, List<Member> members) {
        return new TeamV1(ranking, representative, members);
    }

    public int getRanking() {
        return ranking;
    }

    public Member getOwner() {
        return owner;
    }

    public List<Member> getMembers() {
        return members;
    }
}
