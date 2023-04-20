package chapter1.item02;

public class BuilderPattern {
	
	private  final String coffeeBeans ;
	private  final Integer water;
	private  final Integer sugar;
	private  final Integer cream;
	private  final Integer syrup;
	
	public static class CoffeeBuilder {
		
		//필수 매개변수
		private  final String coffeeBeans ;
		private  final Integer water;
		
		//선택 매개변수
		private  Integer sugar = 0;
		private  Integer cream = 0;
		private  Integer syrup = 0;
		
		public CoffeeBuilder(String coffeeBeans, int water) {
			this.coffeeBeans=coffeeBeans;
			this.water=water;
		}
		
		public CoffeeBuilder sugar(int val) {
			sugar=val;
			return this;
		}
		
		public CoffeeBuilder cream(int val) {
			cream=val;
			return this;
		}
		
		public CoffeeBuilder syrup(int val) {
			syrup=val;
			return this;
		}
		
		public BuilderPattern build() {
			return new BuilderPattern(this);
		}
		
	}
	
	private BuilderPattern (CoffeeBuilder builder) {
		coffeeBeans = builder.coffeeBeans;
		water = builder.water;
		sugar = builder.sugar;
		cream = builder.cream;
		syrup = builder.syrup;
	}
	
	@Override
	public String toString() {
		return "Item : {coffeeBeans = " + coffeeBeans + ", water = " +water + ", sugar = " + sugar + ", cream = " + cream + ", syrup = " + syrup;
	}
	
	public static void main(String [] args) {
		
		//객체 설정및 호출방법 1
		BuilderPattern.CoffeeBuilder builder = new BuilderPattern.CoffeeBuilder("원두커피", 300);
		builder.sugar(3);
		builder.cream(2);
		builder.syrup(2);
		BuilderPattern item = builder.build();

		System.out.println(item);
		
		//객체 설정및 호출방법 2		
		BuilderPattern newItem = new BuilderPattern
				.CoffeeBuilder("새로운원두커피", 3).cream(2).syrup(3).build();
		System.out.println(newItem);
		
	}
}
