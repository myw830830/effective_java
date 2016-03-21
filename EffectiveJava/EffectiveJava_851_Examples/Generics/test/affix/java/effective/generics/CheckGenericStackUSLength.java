package affix.java.effective.generics;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import affix.java.effective.uslength.USLength;

public class CheckGenericStackUSLength {

	private GenericStack<USLength> theStack;
	
	@Before
	public void setUp() throws Exception {
		System.out.println("----- GenericStack<USLength> test -----");
		theStack = new GenericStack<USLength>(10);
	}

	@Test
	public void testPop(){
		assertEquals(null, theStack.pop());
	}
	
	@Test
	public void testPush(){
		for(int i=0; i<8; i++){
			theStack.push(new USLength(i, i));
		}
		assertEquals(8, theStack.size());
		assertEquals(new USLength(7, 7), theStack.pop());		
	}
}
