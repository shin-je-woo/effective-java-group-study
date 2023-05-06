package chapter4.item15;

public class PublicClass {
	private int value;

	public PublicClass(int value) {
		this.value=value;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	/*
	 * private으로 선언되어, 외부에서 직접 접근할 수 없습니다. 
	 * 대신, getter와 setter 메서드를 통해 필드에 접근할 수 있도록 하고 있습니다.
	 * 
	 * public 클래스의 인스턴스 필드는 되도록 public으로 만들지 말자
	 * 불변을 보장할수 없다
	 */
	
}
