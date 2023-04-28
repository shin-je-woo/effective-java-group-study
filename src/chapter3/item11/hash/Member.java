package chapter3.item11.hash;

public class Member {

    private String name;
    private int age;

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Member)) return false;
        Member member = (Member) o;
        return member.name.equals(name) && member.age == age;
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(name, age);
//    }
}
