package affix.java.effective.moneyservice;

public class Order {

	private final String currencyCode;
	private final int amount;
	private final TransactionMode mode;
	
	/**
	 * Constructor for a money exchange transaction
	 * @param currencyCode the Currency involved
	 * @param amount the value to exchange
	 * @param mode type of transaction Buy/Sell
	 * @throws IllegalArgumentException if amount is not accepted
	 */
	public Order(String currencyCode, int amount, TransactionMode mode) {
		
		if(amount < 1){
			throw new IllegalArgumentException("Amount too low");
		}
		this.currencyCode = currencyCode;
		this.amount = amount;
		this.mode = mode;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public int getAmount() {
		return amount;
	}
	
	public TransactionMode getMode() {
		return mode;
	}

	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("Order [amount=");
		builder.append(amount);
		builder.append(", currencyCode=");
		builder.append(currencyCode);
		builder.append(", mode=");
		builder.append(mode);
		builder.append("]");
		return builder.toString();
	}

}
