package affix.java.effective.time;

import org.junit.*;
import static org.junit.Assert.*;

public class TestTimePresentation{

	private Time defaultTime;
	private Time mixedTime;
	private Time fullTime;
	
	@Before
	public void fixture() {
		defaultTime = new Time();
		mixedTime = new Time(8, 12, 5, 0);
		fullTime = new Time( 23, 59, 59, 99);
		System.out.println("Time Presentation Test");
	}
	
	@Test
	public void defaultToString() {		
		assertEquals("00:00:00.00", defaultTime.toString());
	}
	@Test
	public void mixedToString() {
		assertEquals("08:12:05.00", mixedTime.toString());
	}
	
	@Test
	public void almostMidnightToString() {
		assertEquals("23:59:59.99", fullTime.toString());
	}

}
