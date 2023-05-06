package chapter4.item15;

import java.util.Arrays;
import java.util.List;

public class ExampleCode {
	public static void main(String[] args) {

		PublicStaticFiled ps = new PublicStaticFiled();
		
		int [] data = ps.VALUES;
		
		System.out.println("변경전");
		System.out.println(Arrays.toString(data));
		System.out.println(Arrays.toString(ps.VALUES));
		
		data[0] = 3;

		System.out.println("\n변경후");
		System.out.println(Arrays.toString(data));
		System.out.println(Arrays.toString(ps.VALUES));

	}
}
