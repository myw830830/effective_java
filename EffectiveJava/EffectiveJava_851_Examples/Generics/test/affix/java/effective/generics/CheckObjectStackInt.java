package affix.java.effective.generics;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CheckObjectStackInt {
	
	private ObjectStack theStack; 
	private final int CAPACITY = 12;
	
	@Before
	public void setUp() throws Exception {
		System.out.println("----- ObjectStack test -----");
		theStack = new ObjectStack(CAPACITY);
	}

	@Test
	public void testPush() {
		for(int i=0; i<CAPACITY; i++){
			int temp=(int)(10*Math.random());
			theStack.push(temp);
		}
		assertFalse(theStack.hasRoom());
	}

	@Test
	public void testPop() {
		
		for(int i=0; i<CAPACITY; i++){
			int temp=(int)(10*Math.random());
			theStack.push(temp);
		}
		
		while(theStack.hasNext()){
			// will crash in runtime
			String s = (String)theStack.pop();
			System.out.println("Stack element " + s);
		}
		assertNull(theStack.pop());
	}

	@Test
	public void testPrintStack(){
		theStack.printStack();
	}
	
}
