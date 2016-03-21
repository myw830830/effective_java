package affix.java.effective.uslength;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestUSLengthEqualsAndHashcode {

	private USLength u0, u1, u2, u3;
	
	@Before
	public void fixture(){
		u0 = null;
		u1 = new USLength();
		u2 = new USLength(5, 5);
		u3 = new USLength(5, 8);
	}
	
	@Test
	public void testHashCode() {
		int u1hc = u1.hashCode();
		int u2hc = u2.hashCode();
		assertFalse(u1hc == u2hc);
	}

	@Test 
	public void testEqualsNullObject() {
		assertNull(u0);
		assertFalse(u1.equals(u0));
	}
	
	@Test 
	public void testEqualsContents() {
		assertEquals(new USLength(5, 5), u2);
	}
	
	@Test 
	public void testEqualsNotSame() {
		assertNotSame(new USLength(5, 8), u3);
	}

}
