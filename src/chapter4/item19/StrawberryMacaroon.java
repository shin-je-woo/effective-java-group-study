package chapter4.item19;

public class StrawberryMacaroon extends MacaroonMaker{
    /**
     * 기본 버터크림 대신 딸기 맛을 넣는다.
     */
    @Override
    public void insertPeeling() {
        System.out.println("딸기맛 버터크림을 넣습니다.");
    }
}
