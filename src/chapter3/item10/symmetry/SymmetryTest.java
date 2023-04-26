package chapter3.item10.symmetry;

public class SymmetryTest {

    public static void main(String[] args) {
        CaseString caseString = new CaseString("Jewoo");
        String string = "jewoo";

        System.out.println(caseString.equals(string)); // true
        System.out.println(string.equals(caseString)); // false
        // CaseString은 대소문자를 무시하지만, String은 대소문자까지 비교한다.
        // 따라서, 대칭성 위반이다.
    }
}
