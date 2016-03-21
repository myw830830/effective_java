package affix.java.effective.moneyservice;

import java.util.Map;

public interface MoneyService {
	
	/**
	 * This method should be used to set up the application
	 * @param currencyMap - A map holding details for supported currencies
	 * @param startMoneys - An array holding available amount for each of the supported currencies 
	 */
	public void configureService(Map<String, Currency> currencyMap, Money[] startMoneys);
	

	/**
	 * This method is used for a buying transaction 
	 * @param orderData holding value, currencyCode and transaction mode 
	 * @return double holding the value of an order
	 */
	public double buyMoney(Order orderData);
	
	
	/**
	 * This method is used for a selling transaction 
	 * @param target holding code for currency
	 * @param orderData holding value, currencyCode and transaction mode 
	 * @return double holding the value of an order
	 */
	public double sellMoney(Order orderData);

	/**
	 * This method is used to print a report presenting current currencies and their amount
	 * for an implementation of the MoneyService interface
	 * @param destination holding where to write the report
	 */
	public void printMoneyReport(String destination);
	
	
	/**
	 * This method will export the data held by an implementor of this interface 
	 * @return Map holding available currencies 
	 */
	public Map<String, Currency> getCurrencyMap();
		
	/**
	 * This method exports the current amount for a specified currency
	 * held by an implementor of MoneyService
	 * @param currencyCode String holding currency code
	 * @return Money holding current value for required currency or null if missing
	 */
	public Money getAvailableMoney(String currencyCode);
	
	/**
	 * This method shuts down the service properly, i.e. closing db connection, 
	 * storing data for future recovery etc
	 */
	public void shutDownService();
}
