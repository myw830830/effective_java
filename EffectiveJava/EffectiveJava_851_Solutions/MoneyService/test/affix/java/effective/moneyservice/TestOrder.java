package affix.java.effective.moneyservice;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestOrder {

	private Order anOrder;
	
	@Before
	public void setUp(){
		anOrder = new Order("USD", 100, TransactionMode.BUY);
	}
	
	@Test
	public void testOrder() {
		assertNotNull(anOrder);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testOrderNegativeAmount() {
		@SuppressWarnings("unused")
		Order badOrder = new Order("GBP", -50, TransactionMode.SELL);
		fail("Negative amount is not accepted!");
	}
	
	@Test
	public void testGetCurrencyCode() {
		assertEquals("USD", anOrder.getCurrencyCode());
	}
	
	@Test
	public void testGetAmount() {
		assertEquals(100, anOrder.getAmount());
	}

	@Test
	public void testGetMode() {
		assertEquals(TransactionMode.BUY, anOrder.getMode());
	}

}
