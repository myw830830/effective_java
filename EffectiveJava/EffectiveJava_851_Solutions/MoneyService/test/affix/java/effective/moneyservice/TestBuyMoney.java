package affix.java.effective.moneyservice;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestBuyMoney extends BaseTestCurrencies {
	
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
	public void testBuyMoneyNormal() {
		
		// get current value in ref currency
		double refStartValue = ms.getAvailableMoney(refCurrency.getCode()).getAmount(); 
		
		// get current value in target currency
		Currency targetCurr = ms.getCurrencyMap().get("USD");
		double targetStartValue = ms.getAvailableMoney("USD").getAmount();
		
		// perform buy operation
		double transactionValue = 0;
		Order anOrder = new Order("USD", 100, TransactionMode.BUY);
		transactionValue = ms.buyMoney(anOrder);

		// make sure that transaction is ok
		double expectedValue = 100 * targetCurr.getBuyRate(); 
		assertEquals(expectedValue, transactionValue, 0.1);

		// make sure that target value after operation is ok
		assertEquals(targetStartValue + 100, ms.getAvailableMoney("USD").getAmount(), 0.1);

		// make sure that ref value after operation is ok	
		assertEquals(refStartValue - expectedValue, ms.getAvailableMoney("SEK").getAmount(), 0.1);
		
	}

	@Test (expected = IllegalArgumentException.class)
	public void testBuyNegativeMoney(){
		
		Order anOrder = new Order("GBP", -250, TransactionMode.BUY);
		@SuppressWarnings("unused")
		double refValue = ms.buyMoney(anOrder);
		
		fail("Negative amount is not allowed");
	}
	
	
	@Test
	public void testBuyMoneyTooMuch(){	
		
		Order anOrder = new Order("USD", 10000, TransactionMode.BUY);
		double refValue = ms.buyMoney(anOrder);
		assertEquals(0, refValue, 0.001);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testBuyUnacceptedMoney() {
		
		Order anOrder = new Order("Lire", 1000, TransactionMode.BUY);
		@SuppressWarnings("unused")
		double refValue = ms.buyMoney(anOrder);
		
		fail("Lire is not available");	
	}
		
	@Test 
	public void testBuyAcceptedMoneyMissingInSite() {

		Order anOrder = new Order("CNY", 1000, TransactionMode.BUY);
		double refValue = ms.buyMoney(anOrder);
		assertEquals(1280, refValue, 1);
	}
}
