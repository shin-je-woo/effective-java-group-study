package chapter4.item15;

 class DefaultClass {
	private int value;

	DefaultClass(int value) {
		this.value=value;
	}
	
	int getValue() {
		return value;
	}

	void setValue(int value) {
		this.value = value;
	}

	/*
	 * package-private으로 선언되어 있습니다. 따라서 같은 패키지 내의 클래스에서만 접근할 수 있습니다. 
	 */
	
}
