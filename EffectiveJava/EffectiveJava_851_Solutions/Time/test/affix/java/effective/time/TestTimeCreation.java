package affix.java.effective.time;

import org.junit.*;
import static org.junit.Assert.*;

public class TestTimeCreation {

	private Time defaultTime;
	private Time normalTime;
	
	@Before
	public void fixture() {
		defaultTime = new Time();
		normalTime = new Time(12, 23, 35, 0);
		System.out.println("Time Creation Test");
	}

	@Test
	public void defaultTimeConstructor(){
		assertNotNull(defaultTime);
		assertEquals(defaultTime, new Time(0, 0, 0, 0));
	}
	
	@Test
	public void normalTimeConstructor(){
		assertNotNull(normalTime);
		assertEquals(12, normalTime.getHour());
		assertEquals(23, normalTime.getMinute());
		assertEquals(35, normalTime.getSecond());
		assertEquals(0, normalTime.getHundred());
	}
	
//	@Ignore
	@Test (expected = IllegalArgumentException.class)
	public void testOddTimeConstructor(){
		@SuppressWarnings("unused")
		Time t =  new Time(12, 43, 75, 0);
		fail("Should raise an IllegalArgumentException");			
	}	
}
