package chapter8.item55;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class OptionalClass {

	public static void main(String[] args) {
		
		List<Integer> numbersOptional = null;
//		Optional<Integer> maxnum = Optional.ofNullable(numbersOptional).flatMap(OptionalClass::maxOptional);
		Optional<Integer> maxnum = maxOptional(numbersOptional);
		System.out.println("Max number: " + maxnum);
		
	}
	
	public static <E extends Comparable<E>> Optional<E> maxOptional(Collection<E> c) {
		   if (c.isEmpty())
		   		return Optional.empty();
		        
		   E result = null;
		   for (E e : c)
		   		if(result==null||e.compareTo(result) > 0)
		        	result = Objects.requireNonNull(e);
		            
		   return Optional.of(result);
	}

}
