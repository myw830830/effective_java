package affix.java.effective.generics;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

public class CheckGenericStackWildcard {

	private GenericStack<Number> genericNumbers;
	
	@Before
	public void setUp() throws Exception {
		genericNumbers = new GenericStack<Number>(6);
		assertNotNull(genericNumbers);
	}
	
	@Test
	public void testPush(){
		genericNumbers.push(new Long(1234567890));
		genericNumbers.push(new Double(23.1234));
		genericNumbers.push(new Float(3.14));
		
		Integer[] intValues = {777, 888, 999};
		for(int i=0; i<intValues.length; i++){
			genericNumbers.push(intValues[i]);
		}
		assertEquals(6, genericNumbers.size());
	}
	
	@Test
	public void testPop(){
		genericNumbers.push(new Long(1234567890));
		genericNumbers.push(new Double(23.1234));
		genericNumbers.push(new Float(3.14));
		
		Integer[] intValues = {777, 888, 999};
		for(int i=0; i<intValues.length; i++){
			genericNumbers.push(intValues[i]);
		}
		
		int n = genericNumbers.size();
		for(int i = 0; i<n; i++){
			Number temp = genericNumbers.pop();
			System.out.println("Element from GenericStack<Number> " + temp);
		}
		assertNull(genericNumbers.pop());
	}
	
	@Test
	public void testPushAll() {

		Integer[] intValues = {777, 888, 999};
		
		// NB! A workaround since you cannot cast arrays to generics, e.g. Iterable<T>
		genericNumbers.pushAll(Arrays.asList(intValues));	
		assertEquals(3, genericNumbers.size());
	}

	@Test
	public void testPopAll() {
		Integer[] intValues = {777, 888, 999};
		
		// NB! A workaround since you cannot cast arrays to generics, e.g. Iterable<T>
		genericNumbers.pushAll(Arrays.asList(intValues));	
		
		Collection<Object> sink = new ArrayList<Object>(); 
		genericNumbers.popAll(sink);
		
		for(Object temp : sink){
			System.out.println("Element from Collection<Object> " + temp);
		}
		assertNull(genericNumbers.pop());
	}

}
