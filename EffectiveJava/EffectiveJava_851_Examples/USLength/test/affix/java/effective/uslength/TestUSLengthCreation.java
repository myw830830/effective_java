package affix.java.effective.uslength;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestUSLengthCreation {

	@Test
	public void testUSLengthDefault() {
		USLength testObject = new USLength();
		assertNotNull(testObject);
	}

	@Test
	public void testUSLengthIntIntNormal() {
		USLength testObject = new USLength(13, 5);
		assertNotNull(testObject);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUSLengthIntIntNegFoot() {
		@SuppressWarnings("unused")
		USLength testObject = new USLength(-7, 8);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUSLengthIntIntNegInch() {
		@SuppressWarnings("unused")
		USLength testObject = new USLength(7, -5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUSLengthIntIntInchOutOfRange() {
		@SuppressWarnings("unused")
		USLength testObject = new USLength(7, 12);
	}

}
