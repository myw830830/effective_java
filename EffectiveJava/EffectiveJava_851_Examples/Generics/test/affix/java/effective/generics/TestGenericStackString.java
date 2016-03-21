package affix.java.effective.generics;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestGenericStackString {
	
	private GenericStack<String> theStack; 
	private final String testStrings[] = {"One", "Two", "Three"};
	
	@Before
	public void setUp() throws Exception {
		System.out.println("----- GenericStack<String> test -----");
		theStack = new GenericStack<String>(5);
	}

	@Test
	public void testPush() {
		for(int i=0; i<testStrings.length; i++){
			theStack.push(testStrings[i]);
		}
		assertEquals(testStrings.length, theStack.size());
	}

	@Test
	public void testPop() {
		
		for(int i=0; i<testStrings.length; i++){
			theStack.push(testStrings[i]);
		}
		
		while(theStack.hasNext()){
			String s = theStack.pop();
			System.out.println("String from stack " + s);
		}
		assertNull(theStack.pop());
	}

	@Test
	public void testHasRoom(){
		assertEquals(true, theStack.hasRoom());
	}
}
