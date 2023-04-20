package chapter1.item01;

/**
 * String Constant Pool 예제
 */
public class StringExample {

    public static void main(String[] args) {

        /**
         * Heap의 상수풀 외부영역에 생성
         */
        String str1 = new String("신제우");
        String str2 = new String("신제우");

        System.out.println(str1 == str2);
        System.out.println(System.identityHashCode(str1));
        System.out.println(System.identityHashCode(str2));


        /**
         * Heap의 문자열 상수풀에 생성
         * Java7 이상부터 String Constant Pool은 힙 위에 올라가 GC의 영향을 받는다.
         */
        String str3 = "신제우";
        String str4 = "신제우";

        System.out.println(str3 == str4);
        System.out.println(System.identityHashCode(str3));
        System.out.println(System.identityHashCode(str4));

        /**
         * String의 intern()
         */
        String intern = str1.intern();
        System.out.println(System.identityHashCode(intern));
        System.out.println(intern == str3);

    }
}
