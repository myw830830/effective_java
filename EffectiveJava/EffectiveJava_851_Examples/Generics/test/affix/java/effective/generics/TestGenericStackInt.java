package affix.java.effective.generics;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestGenericStackInt {
	
	private GenericStack<Integer> theStack; 
	private final int N = 12;
	
	@Before
	public void setUp() throws Exception {
		System.out.println("----- GenericStack<Integer> test -----");
		theStack = new GenericStack<Integer>(N);
	}

	@Test
	public void testPush() {
		for(int i=0; i<N; i++){
			int temp=(int)(10*Math.random());
			theStack.push(temp);
		}
		assertEquals(N, theStack.size());
	}

//	@Test
//	public void testPop() {
//		
//		for(int i=0; i<N; i++){
//			int temp=(int)(10*Math.random());
//			theStack.push(temp);
//		}
//		
//		while(theStack.hasNext()){
//			// will not compile
//			String s = (String)theStack.pop();
//		}
//
//		Assert.assertNull(theStack.pop());
//	}

	@Test
	public void testHasRoom(){
		for(int i=0; i<N; i++){
			int temp=(int)(10*Math.random());
			theStack.push(temp);
		}
		assertEquals(false, theStack.hasRoom());
	}
	
}
