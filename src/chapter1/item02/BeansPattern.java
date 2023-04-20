package chapter1.item02;

public class BeansPattern {
	
	private  String coffeeBeans ;
	private  Integer water;
	private  Integer sugar;
	private  Integer cream;
	private  Integer syrup;
	
	public String getCoffeeBeans() {
		return coffeeBeans;
	}
	public void setCoffeeBeans(String coffeeBeans) {
		this.coffeeBeans = coffeeBeans;
	}
	public Integer getWater() {
		return water;
	}
	public void setWater(Integer water) {
		this.water = water;
	}
	public Integer getSugar() {
		return sugar;
	}
	public void setSugar(Integer sugar) {
		this.sugar = sugar;
	}
	public Integer getCream() {
		return cream;
	}
	public void setCream(Integer cream) {
		this.cream = cream;
	}
	public Integer getSyrup() {
		return syrup;
	}
	public void setSyrup(Integer syrup) {
		this.syrup = syrup;
	}
	
	@Override
	public String toString() {
		return "Item : {coffeeBeans = " + coffeeBeans + ", water = " +water + ", sugar = " + sugar + ", cream = " + cream + ", syrup = " + syrup;
	}
	
	public static void main(String[] args) {
		BeansPattern Item = new BeansPattern();
		
		//객체 설정 방법
		Item.setCoffeeBeans("원두커피");
		Item.setWater(3);
		Item.setSugar(2);
		Item.setSyrup(1);
		
		//객체 호출 방법
		Item.getCoffeeBeans();
		Item.getWater();
		Item.getSugar();
		Item.getSyrup();
		
		System.out.println(Item);
		
	}
}
