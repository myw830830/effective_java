package affix.java.effective.uslength;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestUSLengthString {

	@Test
	public void testToString() {
		USLength u1 = new USLength(15, 9);
		assertEquals("USLength: 15' 9\"", u1.toString());
	}

	@Test
	public void testCreateUSLengthStringNormal() {
		USLength testObject = new USLength("13' 5\"");
		assertNotNull(testObject);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateUSLengthUnparsableString() {
		USLength testObject = new USLength("13, 5");
		assertNotNull(testObject);
	}
}
