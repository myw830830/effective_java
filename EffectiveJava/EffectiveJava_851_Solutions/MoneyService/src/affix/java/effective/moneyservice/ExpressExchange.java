package affix.java.effective.moneyservice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class ExpressExchange implements MoneyService {
	
	// Map holding all accepted Currencies and their rates
	private Map<String, Currency> currencyMap;
	
	// Map holding all Moneys available for this site
	private Map<String, Money> siteMoneyMap;
	
	// Specific instance defining reference Currency and available amount of it 
	private Money refMoney;
	
	// collection storing all Transactions in order of appearance
	private List<Transaction> transactions;
	
	/**
	 * Constructor defining reference Money
	 * @param refCurr holding code for reference Currency - default SEK
	 * @param amount value of reference Currency at start up
	 */
	public ExpressExchange(String refCurr, float amount){
		
		siteMoneyMap = new HashMap<String, Money>();
		refMoney = new Money(refCurr, amount);
		siteMoneyMap.put(refCurr, refMoney);
		transactions = new ArrayList<Transaction>();
	}
	

	/**
	 * This method will populate the collections of Currencies and Moneys supported by the site
	 * Every supported currency, and its current exchange rates, will be registered in a Map
	 * @param currencyMap - A map holding details for supported currencies
	 * @param startMoneys - An array holding available amount for each of the supported currencies 
	 */
	@Override	
	public void configureService(Map<String, Currency> currencyMap, Money[] startMoneys){
		
		this.currencyMap = currencyMap;
		
		for(Money money : startMoneys){
			siteMoneyMap.put(money.getCurrencyCode(), money);
		}
		
		// add empty mappings for Currencies not part of startMoneys
		for(Currency c : currencyMap.values()){
			siteMoneyMap.putIfAbsent(c.getCode(), new Money(c.getCode(), 0));
		}
	}
	
	// Define a lambda expression using a standard FunctionalInterface
	BiFunction<Currency, Integer, Double> buyValue 
	= (Currency curr, Integer x) -> { return curr.getBuyRate() * x; };
	
	/**
	 * When buying the transaction will always use reference Money for conversion. 
	 * @param buyOrder holding value and type of currency wanted
	 * @return double holding the value of an order
	 * @throws IllegalArgumentException if required Currency is not accepted
	 */
	@Override
	public double buyMoney(Order buyOrder) {
		
		double convertedMoney = 0.0;
		
		// Add code here
		// Check parameters for validity
		String targetCurrency = buyOrder.getCurrencyCode();
		if(!currencyMap.containsKey(targetCurrency)){
			throw new IllegalArgumentException("Currency " + targetCurrency + " is not supported!");
		}
			
		if(canAfford(buyOrder)){
			// Convert amount to reference money calling method convertMoney()
			//	convertedMoney = convertMoney(buyOrder);
			// Call prepared lambda
			convertedMoney = buyValue.apply(currencyMap.get(targetCurrency), buyOrder.getAmount());

			// Get current Money of target type from siteMoneyMap 
			Money targetMoney = siteMoneyMap.get(targetCurrency);
			// Adjust siteMoneyMap
			targetMoney.setAmount(targetMoney.getAmount() + buyOrder.getAmount());
			refMoney.setAmount(refMoney.getAmount() - convertedMoney);

			// Create a Transaction object, store in collection
			Transaction t = new Transaction(buyOrder);
			transactions.add(t);
		}
		
		return convertedMoney;
	}
		
	// Define a lambda expression using a standard FunctionalInterface
	BiFunction<Currency, Integer, Double> sellValue  
			= (Currency curr, Integer x) -> { return curr.getSellRate() * x;};

	/**
	 * When selling the transaction will always use reference Money for conversion. 
	 * @param sellOrder holding value and type of currency wanted
	 * @return double holding the value of an order in reference Money
	 * @throws IllegalArgumentException if required Currency is not accepted
	 */	
	@Override
	public double sellMoney(Order sellOrder)  {

		double convertedMoney = 0.0;
		String targetCurrency = sellOrder.getCurrencyCode();
		
		// Add code here
		if(!currencyMap.containsKey(targetCurrency)){
			throw new IllegalArgumentException("Currency " + targetCurrency + " is not supported!");
		}
		
		if(canAfford(sellOrder)){
			// Convert amount to reference money calling method convertMoney()
			// convertedMoney = convertMoney(sellOrder);
			// Call prepared lambda
			convertedMoney = sellValue.apply(currencyMap.get(targetCurrency), sellOrder.getAmount());

			// Get current Money of target type from siteMoneyMap 
			Money targetMoney = siteMoneyMap.get(targetCurrency);
			// Adjust siteMoneyMap
			targetMoney.setAmount(targetMoney.getAmount() - sellOrder.getAmount());
			refMoney.setAmount(refMoney.getAmount() + convertedMoney);

			// Create a Transaction object, store in collection
			Transaction t = new Transaction(sellOrder);
			transactions.add(t);
		}
		
		return convertedMoney;
	}	

	/**
	 * This is a state checking method to avoid throwing an Exception if the site cannot afford to buy a specific Currency
	 * @param orderData holds info of required amount in specific Currency to buy/sell
	 * @return boolean true if an Order can be carried out, otherwise false
	 */
	public boolean canAfford(Order orderData){

		int amount = orderData.getAmount();
		if(orderData.getMode() == TransactionMode.BUY){
			double temp = convertMoney(orderData);
		
			if(temp <= refMoney.getAmount()){	
				return true;
			}
		}
		else{		
			Money targetMoney = siteMoneyMap.get(orderData.getCurrencyCode());
			if(amount <= targetMoney.getAmount()){	
				return true;
			}			
		}
		
		return false;
	}
	
	/**
	 * This method will convert a Money from any type into reference Money
	 * @param anOrder holding order data
	 * @return double holding corresponding value in reference Money
	 */
	private double convertMoney(Order anOrder) {

		Currency fromCurrency = currencyMap.get(anOrder.getCurrencyCode());
		double rate = 0.0;
		
		switch(anOrder.getMode()){
		case BUY:
			rate = fromCurrency.getBuyRate();
			break;
		case SELL:
			rate = fromCurrency.getSellRate();
			break;
		}
		double value = rate * anOrder.getAmount();
		
		return value;
	}

	/**
	 * Defensive copy of Map entry
	 * @param currencyCode holding requested Currency
	 * @return Money a copy of what is stored in Map or null if not present
	 */
	@Override
	public Money getAvailableMoney(String currencyCode){
		
		Money copyObject = null;
		if(siteMoneyMap.containsKey(currencyCode)){
			copyObject = new Money(currencyCode, siteMoneyMap.get(currencyCode).getAmount());
		}
		return copyObject;
	}
	
	
	/**
	 * This method will print a report of the current contents of the moneyMap
	 * All Money items will be presented using their toString() method
	 */
	@Override
	public void printMoneyReport(String destination) {
		
		System.out.println("=========== ExpressExchange report ===========");
		for(String key : siteMoneyMap.keySet()){
			System.out.println(siteMoneyMap.get(key));
		}
		System.out.println();
		
		Money totalMoney = calculateTotalValue();
		System.out.println("Total value in reference money ");
		System.out.println(totalMoney);
		
		System.out.println(" ---------- Transactions ---------- ");
		for(Transaction t : transactions){
			System.out.println(t);
		}

	}
	
	
	/**
	 * This method will add up the total value for this site
	 * @return Money holding site value converted to reference currency
	 */
	private Money calculateTotalValue(){
		
		double totalValue = 0;
		
		for(String key : siteMoneyMap.keySet()){
			Money m = siteMoneyMap.get(key);
			double rate = currencyMap.get(m.getCurrencyCode()).getBuyRate();
			double value = m.getAmount()*rate;
			totalValue += value;
		}
		
		Money totalMoney = new Money(refMoney.getCurrencyCode(), totalValue);
		return totalMoney;
	}

	/**
	 * Getter of attribute currencyMap
	 */
	@Override
	public Map<String, Currency> getCurrencyMap() {
		return Collections.unmodifiableMap(currencyMap);
	}

	@Override
	public void shutDownService() {
		
		System.out.println("Debug: calling shutDownService()");
		MoneyServiceUtils.storeTransactions("Transactions.ser", transactions);
	}	
	
}
