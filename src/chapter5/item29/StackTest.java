package chapter5.item29;

public class StackTest {

    public static void main(String[] args) {
        MyStackV1 myStackV1 = new MyStackV1();
        myStackV1.push(1);
        myStackV1.push("1");
        String pop1 = (String) myStackV1.pop();
//        String pop2 = (String) myStackV1.pop(); // ClassCastException 발생!

        MyStackV2<String> myStackV2 = new MyStackV2<>();
//        myStackV2.push(1); // 컴파일  에러
        myStackV2.push("1");
        String pop3 = myStackV2.pop();

    }

}
