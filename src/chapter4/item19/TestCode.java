package chapter4.item19;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class TestCode {
    public static void main(String[] args) {

        // 훅을 위한 protected 메서드
        MacaroonMaker standardFlavor = new MacaroonMaker();
        standardFlavor.make(1);
        System.out.println("-------------------------------");
        StrawberryMacaroon strawberryFlavor = new StrawberryMacaroon();
        strawberryFlavor.make(1);

        /* 동작 순서
         * 1. 상위 클래스 생성자 호출 -> 생성자 내 하위 클래스에서 재정의한 메서드 있음으로 호출됨 -> 현재 instant는 초기화 되지 않은 상태
         * 2. 하위 클래스 생성자 호출
         * */
        SubClass sub = new SubClass();
        sub.overrideMe();


        // private 도우미 메서드 예정
        AddInt addInt = new AddInt();
        System.out.println("1~10까지의 합 (55) : " + addInt.addAll(1,2,3,4,5,6,7,8,9,10));
    }
}
