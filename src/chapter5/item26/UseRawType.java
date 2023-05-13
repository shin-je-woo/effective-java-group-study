package chapter5.item26;

import java.util.ArrayList;
import java.util.List;

public class UseRawType {
	public static void main (String []args) {
		
		List<String> stringList = new ArrayList<>();
		Class<? extends List> clazz = stringList.getClass();
		System.out.println(clazz.getName());
		
		
		boolean check = isList(stringList);
		System.out.println(check);
		
	}
	
	
	//obj가 List 타입인지 검사하는 메서드
	public static boolean isList(Object obj) {
	    return obj instanceof List;
	}
}
