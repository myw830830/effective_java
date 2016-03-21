package affix.java.effective.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class GenericsCharacteristics {

	public static void main(String[] args) {
		
		Number obj = new Integer(12);
		System.out.println("An integer held by a Number reference " + obj);
		
		// using various sub types as producers
		GenericStack<Number> genericNumbers = new GenericStack<Number>(6);
		genericNumbers.push(new Long(1234567890));
		genericNumbers.push(new Double(23.1234));
		genericNumbers.push(new Float(3.14));
		
		// using generic type as producer
		Integer[] intValues = {777, 888, 999};
		for(int i=0; i<intValues.length; i++){
			genericNumbers.push((Number)intValues[i]);
		}
		
		// using generic type as consumer
		int n = genericNumbers.size();
		for(int i = 0; i<n; i++){
			Number temp = genericNumbers.pop();
			System.out.println("Element from GenericStack<Number> " + temp);
		}	
		
		System.out.println(" ----------------------- ");
		
		// NB! A workaround since you cannot cast arrays to generics, e.g. Iterable<T>
		// using a sub type as producer
		genericNumbers.pushAll(Arrays.asList(intValues));	
		
		// using generic type as consumer
		Collection<Number> sink = new ArrayList<Number>(); 
		genericNumbers.popAll(sink);
		
		for(Number temp : sink){
			System.out.println("Element from Collection<Number> " + temp);
		}	
		
		System.out.println(" ======================= ");
		
		// using a sub type as producer
		genericNumbers.pushAll(Arrays.asList(intValues));
		
		// using a super type as consumer
		Collection<Object> numSink = new ArrayList<Object>(); 
		genericNumbers.popAll(numSink);
		for(Object temp : numSink){
			System.out.println("Element from Collection<Object> " + temp);
		}	
	}

}
