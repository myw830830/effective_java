package affix.java.effective.uslength;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestUSLengthCompare {

	private USLength u0, u1, u2, u3;
	
	@Before
	public void fixture(){
		u0 = null;
		u1 = new USLength();
		u2 = new USLength(5, 5);
		u3 = new USLength(5, 8);
	}
	
	@Test (expected = NullPointerException.class)
	public void testCompareToNullObject() {
		u1.compareTo(u0);
	}
		
	@Test
	public void testOrderRising(){
		int order = u1.compareTo(u2);		
		assertTrue(order < 0);
	}
	
	@Test
	public void testOrderFalling(){
		int order = u3.compareTo(u2);		
		assertTrue(order > 0);
	}

	@Test
	public void testOrderSame(){
		int order = u3.compareTo(new USLength(5, 8));		
		assertTrue(order == 0);
	}

}
