package chapter6.item34;

import java.util.Arrays;

enum Color {
    RED,
    GREEN,
    BLUE
}

public class EnumClass {
	public static void main (String [] args) {
    	/*
    	 * values(): 이 메서드는 열거 타입의 모든 열거 상수를 배열로 반환합니다. 
    	 * 열거 상수들은 열거 타입에 선언된 순서대로 배열에 저장됩니다.
    	 */
		Color [] color = Color.values();
    	System.out.println(Arrays.toString(color));
    	
    	/*
    	 * valueOf(String name): 이 메서드는 주어진 이름에 해당하는 열거 상수를 반환합니다.
    	 * 주어진 이름과 일치하는 상수가 없을 경우 IllegalArgumentException이 발생합니다.
    	 */
    	Color valOf = Color.valueOf("RED");
    	System.out.println(valOf);
    	
    	/*
    	 * name(): 이 메서드는 열거 상수의 이름을 문자열로 반환합니다.
    	 */
    	Color name = Color.BLUE;
    	System.out.println(name.name());
    	
    	/*
    	 * ordinal(): 이 메서드는 열거 상수의 순서(0부터 시작하는 인덱스)를 반환합니다.
    	 */
    	Color ordinal = Color.GREEN;
    	System.out.println(ordinal.ordinal());
    	
    }
}