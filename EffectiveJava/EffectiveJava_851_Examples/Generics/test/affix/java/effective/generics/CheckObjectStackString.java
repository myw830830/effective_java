package affix.java.effective.generics;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CheckObjectStackString {
	
	private ObjectStack theStack; 
	private final int CAPACITY = 5;
	private final String testStrings[] = {"One", "Two", "Three", "Four", "Five"};
	
	@Before
	public void setUp() throws Exception {
		System.out.println("----- GenericStack<String> test -----");
		theStack = new ObjectStack(CAPACITY);
	}

	@Test
	public void testPush() {
		for(int i=0; i<CAPACITY; i++){
			theStack.push(testStrings[i]);
		}
		assertFalse(theStack.hasRoom());
	}

	@Test
	public void testPop() {
		
		for(int i=0; i<CAPACITY; i++){
			theStack.push(testStrings[i]);
		}
		
		while(theStack.hasNext()){
			String s = (String) theStack.pop();
			System.out.println("String from stack " + s);
		}
		assertNull(theStack.pop());
	}

	@Test
	public void testPrintStack(){
		theStack.printStack();
	}
	
}
