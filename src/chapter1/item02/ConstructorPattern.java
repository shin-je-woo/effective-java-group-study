package chapter1.item02;

public class ConstructorPattern {

	//필수 매개변수
	private  String coffeeBeans ;
	private  Integer water;
	
	//선택 매개변수
	private  Integer sugar;
	private  Integer cream;
	private  Integer syrup;
	
	// 필수 요소만 포함된 생성자
	public ConstructorPattern(String coffeeBeans , Integer water) {
		this(coffeeBeans, water, null);
	}
	
	// 필수요소 + 1개의 선택적매개변수를 받는 생성자
	public ConstructorPattern(String coffeeBeans , Integer water, Integer sugar) {
		this(coffeeBeans, water, sugar, null);
	}
	
	// 2개의 선택적 매개변수 필요
	public ConstructorPattern(String coffeeBeans , Integer water, Integer sugar, Integer cream) {
		this(coffeeBeans, water, sugar, cream, null);
	}
	
	// 3개의 선택적 매개변수 필요 
	public ConstructorPattern(String coffeeBeans , Integer water, Integer sugar, Integer cream, Integer syrup) {
		this.coffeeBeans = coffeeBeans;
		this.water = water;
		this.sugar = sugar;
		this.cream = cream;
		this.syrup = syrup;
	}

	@Override
	public String toString() {
		return "Item : {coffeeBeans = " + coffeeBeans + ", water = " +water + ", sugar = " + sugar + ", cream = " + cream + ", syrup = " + syrup;
	}
	
	public static void main(String[] args) {
		ConstructorPattern Item = new ConstructorPattern("11",2);
		
		System.out.println(Item);
	}
}