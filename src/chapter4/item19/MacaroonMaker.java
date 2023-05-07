package chapter4.item19;

/**
 * 마카롱 만드는 기계를 추상화한 클래스
 */
public class MacaroonMaker {

    /**
     *
     * @param       count 상품 갯수
     *
     * @implSpec    마카롱을 만들기 위해 갯수를 입력 받는다.
     *              입력받은 갯수가 0보다 작거나 같을 경우 AssertionError를 발생시킨다.
     *              insertPeeling 메서드를 재정의함으로써 마카롱 종류를 변경할 수 있다.
     *
     * @see         MacaroonMaker#insertPeeling()
     */
    public void make(int count){
        System.out.println("마카롱 뚜껑을 굽습니다.");
        insertPeeling();
        System.out.println("마카롱이 " + count + "개 완성되었습니다.");
    }

    /**
     * 마카롱 필링을 결졍한다.
     *
     * @implSpec    make 메서드 내부에서 사용되며,
     *              메서드 재정의 시, 마카롱 종류를 변경할 수 있다.
     *
     * @since       2023.05.07
     */
    public void insertPeeling(){
        System.out.println("앙글레즈 버터 그림을 넣습니다.");
    }
}
