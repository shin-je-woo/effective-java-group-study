package chapter5.item26;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RawType {
	public static void main(String[] args) {
	    List<String> str = new ArrayList<>();
	    
	    unsafeAdd(str, 42);
	    
	    // 컴파일은 정상작동하지만, ClassCastException이 발생
        // List<String>에 추가된  Integer 객체를 String으로 형변환하려고 시도할 때 발생	    
	    String s = str.get(0);	// 컴파일러가 자동으로 형변환 코드를 넣어줌
	}

	// raw type인 List 이용
	private static void unsafeAdd(List list, Object o) {
	    list.add(o);
	}
}
