package chapter8.item49;

import java.util.Objects;

public class ObjectClass {
	public static void main(String[] args) {

		ObjectBean ob = new ObjectBean();
		
		ob.setStr("11");
		System.out.println(ob.getStr());
		
		ob.setStr(null);
		ob.getStr();

		
	}
}
