package affix.java.effective.time;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestTimeEqualsAndHashcode {

	private Time t0, t1, t2, t3;
	
	@Before
	public void fixture(){
		t0 = null;
		t1 = new Time();
		t2 = new Time(0, 1, 2, 3);
		t3 = new Time(23, 55, 12, 88);
	}
	
	@Test
	public void testHashCode() {
		int t1hc = t1.hashCode();
		int t2hc = t2.hashCode();
		assertFalse(t1hc == t2hc);
	}

	@Test 
	public void testEqualsNullObject() {
		assertNull(t0);
		assertFalse(t1.equals(t0));
	}
	
	@Test 
	public void testEqualsContents() {
		assertEquals(new Time(0, 1, 2, 3), t2);
	}
	
	@Test 
	public void testEqualsNotSame() {
		assertNotSame(new Time(23, 55, 12, 88), t3);
	}

}
