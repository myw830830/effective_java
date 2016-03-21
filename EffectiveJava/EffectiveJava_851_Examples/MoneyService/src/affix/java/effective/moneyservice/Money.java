package affix.java.effective.moneyservice;

/**
 * Instances of this class is used for transactions and for representing state for
 * specific currencies for an implementor of MoneyService
 */
public class Money {

	/**
	 * Code for Money object
	 */
	private final String currencyCode;
	/**
	 * Amount in chosen currency
	 */
	private double amount;
	
	/**
	 * Constructor
	 * @param currencyCode a String holding a valid Currency code 
	 * @param amount a double holding initial value
	 */
	public Money(String currencyCode, double amount){
		this.currencyCode = currencyCode;
		this.amount = amount;
	}


	/**
	 * @return the currency code
	 */
	public String getCurrencyCode() {
		return currencyCode;
	}
	
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}


	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		
		StringBuilder sb = new StringBuilder();
		sb.append("Money [Currency: ");
		sb.append(currencyCode);
		sb.append(" amount: ");	
		sb.append(String.format("%.2f", amount));
		sb.append(" ]");
		
		return sb.toString();
	}

}
