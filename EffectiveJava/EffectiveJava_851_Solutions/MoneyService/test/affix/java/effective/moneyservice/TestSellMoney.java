package affix.java.effective.moneyservice;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class TestSellMoney extends BaseTestCurrencies {

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
	public void testSellMoneyNormal() {
		
		// get current value in ref currency
		double refStartValue = ms.getAvailableMoney(refCurrency.getCode()).getAmount(); 

		// get current value in target currency
		Currency targetCurr = ms.getCurrencyMap().get("USD");
		double targetStartValue = ms.getAvailableMoney("USD").getAmount();

		// perform sell operation
		double transactionValue = 0;
		Order anOrder = new Order("USD", 100, TransactionMode.SELL);
		transactionValue = ms.sellMoney(anOrder);	

		// make sure that transaction is ok
		double expectedValue = 100 * targetCurr.getSellRate(); 
		assertEquals(expectedValue, transactionValue, 0.1);

		// make sure that target value after operation is ok
		assertEquals(targetStartValue - 100, ms.getAvailableMoney("USD").getAmount(), 0.1);

		// make sure that ref value after operation is ok	
		assertEquals(refStartValue + expectedValue, ms.getAvailableMoney("SEK").getAmount(), 0.1);

	}

	@Test (expected = IllegalArgumentException.class)
	public void testSellNegativeMoney() {

		Order anOrder = new Order("GBP", -250, TransactionMode.SELL);
		@SuppressWarnings("unused")
		double refValue = ms.sellMoney(anOrder);
		
		fail("Negative amount is not allowed");
	}

	@Test
	public void testSellMoneyTooMuch(){

		Order anOrder = new Order("CHF", 1000000, TransactionMode.SELL);
		double refValue = ms.sellMoney(anOrder);
		assertEquals(0, refValue, 0.001);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testSellUnacceptedMoney() {

		Order anOrder = new Order("Lire", 1000, TransactionMode.SELL);
		@SuppressWarnings("unused")
		double refValue = ms.sellMoney(anOrder);

		fail("Lire is not available");	
	}

	@Test
	public void testSellAcceptedMoneyMissingInSite() {

		Order anOrder = new Order("CNY", 1000, TransactionMode.SELL);	
		double refValue = ms.sellMoney(anOrder);
		assertEquals(0, refValue, 0.001);
	}
}
