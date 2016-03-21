package affix.java.effective.moneyservice;

import java.util.*;

public class BaseTestCurrencies {
	
	// define reference currency
	protected Currency refCurrency;

	// get current info for accepted currencies 
	protected Map<String, Currency> currMap;

	// define implementor handle
	protected MoneyService ms;	
	
	protected void setUp() {
		refCurrency = new Currency("SEK", "Sweden", 1.0, 1.0);
		currMap = CurrencyFileSupport.getCurrencyMap("Currencies.txt");
		ms = new ExpressExchange(refCurrency.getCode(), 10260);
		System.out.println("BaseTestCurrencies setUp() finished");
	}

}
