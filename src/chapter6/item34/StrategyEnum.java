package chapter6.item34;

//전략 열거 타입 패턴
public enum StrategyEnum {

	STRATEGY1 {
        @Override
        public void executeStrategy() {
            // 전략 1에 해당하는 코드 작성
        	// 평일 수당
        }
    },
    STRATEGY2 {
        @Override
        public void executeStrategy() {
            // 전략 2에 해당하는 코드 작성
        	// 평일 + 야근수당
        }
    },
    STRATEGY3 {
        @Override
        public void executeStrategy() {
            // 전략 3에 해당하는 코드 작성
        	// 주말 수당
        }
    };
	

    public abstract void executeStrategy();
}
