package affix.java.effective.moneyservice;

import static org.junit.Assert.*;
//import org.junit.Ignore;
import org.junit.Test;

public class TestMoney extends BaseTestCurrencies{

//	@Ignore
	@Test (expected = IllegalArgumentException.class)
	public void testMoneyCreation() {
		@SuppressWarnings("unused")
		Money temp = new Money("USD", -150);
		
		fail("Negative amount is invalid for Money!");
	}

}
