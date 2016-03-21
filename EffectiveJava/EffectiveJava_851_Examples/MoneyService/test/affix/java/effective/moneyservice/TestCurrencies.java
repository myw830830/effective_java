package affix.java.effective.moneyservice;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class TestCurrencies extends BaseTestCurrencies {
	
	@Before
	public void fixture() throws Exception{
		super.setUp();
		// set up initial amount for a number of Currencies for this MoneyService
		Money[] startMoneys = 
		{ 
				new Money("EUR", 3000), 
				new Money("USD", 2000), 
				new Money("GBP", 1500),				
				new Money("NOK", 10000), 
				new Money("DKK", 10000), 
				new Money("CHF", 1500),
				new Money("RUB", 12000),
				new Money("AUD", 500)		
		};	
		
		// configure site
		ms.configureService(currMap, startMoneys);

	}
	
	@Test
	public void testAcceptedCurrency() {
		assertTrue(ms.getCurrencyMap().containsKey("EUR"));
		assertTrue(ms.getCurrencyMap().containsKey("GBP"));
		assertTrue(ms.getCurrencyMap().containsKey("NOK"));
	}
	
	@Test
	public void testUnacceptedCurrency() {
		assertFalse(ms.getCurrencyMap().containsKey("Lire"));
		assertFalse(ms.getCurrencyMap().containsKey("Rupie"));
		assertFalse(ms.getCurrencyMap().containsKey("YEN"));
	}

	
	@Ignore
	@Test (expected = IllegalArgumentException.class)
	public void testChangeOfBuyRate(){
		Currency temp = currMap.get("NOK");
		temp.setBuyRate(-1.25);
		
		fail("Negative buyRate is invalid for Currency!");
	}
	
	@Ignore	
	@Test(expected = IllegalArgumentException.class)
	public void testChangeOfSellRate(){
		Currency temp = currMap.get("NOK");
		temp.setSellRate(-1.35);
		
		fail("Negative sellRate is invalid for Currency!");
	}
	
}
