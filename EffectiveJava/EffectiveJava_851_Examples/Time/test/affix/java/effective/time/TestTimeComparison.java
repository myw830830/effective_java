package affix.java.effective.time;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestTimeComparison {

	private Time firstTime;
	private Time secondTime;
	private Time midnightTime;
	private Time miniTime;

	@Before
	public void fixture() {
		firstTime = new Time(8, 12, 25, 5);
		secondTime = new Time( 7, 55, 42, 12);
		midnightTime = new Time(23, 59, 50, 0);
		miniTime = new Time(0, 0, 25, 0);
		System.out.println("Time Subtracting Test");
	}
	@Test
	public void testCompareEquals() {
		int result = firstTime.compareTo(new Time(8, 12, 25, 5));
		System.out.println("Equals comparison "+result);		
		assertTrue("Time objects should compare correctly", result==0);
	}
	@Test
	public void testCompareNotEquals() {
		int result = firstTime.compareTo(miniTime);
		System.out.println("Not equals comparison "+result);		
		assertFalse("Time objects should compare correctly", result==0);
	}
	@Test
	public void testCompareGreaterThan() {
		int result = firstTime.compareTo(secondTime);
		System.out.println("Greater than comparison "+result);		
		assertEquals("Time objects should compare correctly", 1, result);
	}
	@Test
	public void testCompareLessThan() {
		int result = secondTime.compareTo(midnightTime);
		System.out.println("Less than comparison "+result);		
		assertEquals("Time objects should compare correctly", -1, result);
	}

}
