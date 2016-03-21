package affix.java.effective.moneyservice;

import java.io.Serializable;

/**
 * This class is used for handling values in any Currency
 * Instances of this class is used for transactions and for representing state for
 * specific currencies for an implementor of MoneyService
 */
public class Money implements Serializable{

	private static final long serialVersionUID = 1L;
	
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
	 * @throws IllegalArgumentException if amount is negative
	 */
	public Money(String currencyCode, double amount){
		if(amount < 0){
			throw new IllegalArgumentException("Amount cannot be negative!");
		}
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
	 * @throws IllegalArgumentException if amount is 0 or negative
	 */
	public void setAmount(double amount) {
		if(amount <= 0){
			throw new IllegalArgumentException("Value of amount ("+amount+") should be > 0");
		}
		this.amount = amount;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((currencyCode == null) ? 0 : currencyCode.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Money)) {
			return false;
		}
		Money other = (Money) obj;
		if (Double.doubleToLongBits(amount) != Double
				.doubleToLongBits(other.amount)) {
			return false;
		}
		if (currencyCode == null) {
			if (other.currencyCode != null) {
				return false;
			}
		} else if (!currencyCode.equals(other.currencyCode)) {
			return false;
		}
		return true;
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
