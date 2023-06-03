package chapter8.item55;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ExampleClass {
	public static void main (String [] args) {

		List<Integer> numbers = Arrays.asList(3, 1, 5, 2, 4);
		Integer maxNumber = max(numbers);
		System.out.println("Max number: " + maxNumber);
		
	}
	
	public static <E extends Comparable<E>> E max (Collection<E> c) {
		if(c.isEmpty())
	    	throw new IllegalArgumentException("빈 컬렉션");
	    
	    E result = null;
	    for (E e : c )
	    	if (result==null || e.compareTo(result) > 0)
	        	result = Objects.requireNonNull(e);
	            
	    return result;
	}
}
