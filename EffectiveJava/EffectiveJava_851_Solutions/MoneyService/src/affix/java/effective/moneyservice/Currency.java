package affix.java.effective.moneyservice;

import java.io.Serializable;

/**
 * This class defines vital info for used Currencies
 * 
 * This class can be used as an immutable class, which has the effect that 
 * you cannot change exchange rates in a running system - not realistic
 * The other solution is to support dynamic change in rates - provide 
 * support for setters for buy/sell-rates.
 */
public class Currency implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private final String code;
	private final String country;
	private double buyRate;
	private double sellRate;
	
	/**
	 * Constructor
	 * @param country A String holding issuing country for a currency 
	 * @param code A String holding a unique shorthand for a currency
	 * @param buyRate A double holding current buy rate
	 * @param sellRate A double holding current sell rate
	 * @throws IllegalArgumentException if buyRate or sellRate is < 0
	 */
	public Currency(String code, String country, double buyRate, double sellRate) {
		if(buyRate < 0){
			throw new IllegalArgumentException("Argument buyRate must be positive!");
		}
		if(sellRate < 0){
			throw new IllegalArgumentException("Argument sellRate must be positive!");
		}		
		this.code = code;
		this.country = country;
		this.buyRate = buyRate;
		this.sellRate = sellRate;
	}
		
	/**
	 * @return the sellRate
	 */
	public double getSellRate() {
		return sellRate;
	}
	/**
	 * @param sellRate the sellRate to set
	 * @throws IllegalArgumentException if sellRate is 0 or negative
	 */
	public void setSellRate(double sellRate) {
		if(sellRate <= 0){
			throw new IllegalArgumentException("Value of sellRate (" + sellRate + ") should be > 0!");
		}
		this.sellRate = sellRate;
	}
	/**
	 * @return the buyRate
	 */
	public double getBuyRate() {
		return buyRate;
	}
	/**
	 * @param buyRate the buyRate to set
	 * @throws IllegalArgumentException if buyRate is 0 or negative
	 */
	public void setBuyRate(double buyRate) {
		if(buyRate <= 0){
			throw new IllegalArgumentException("Value of buyRate (" + buyRate + ") should be > 0!");
		}
		this.buyRate = buyRate;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Currency [code=");
		builder.append(code);
		builder.append(", country=");
		builder.append(country);
		builder.append(", buyRate=");
		builder.append(String.format("%.2f", buyRate));		
		builder.append(", sellRate=");
		builder.append(String.format("%.2f", sellRate));
		builder.append("]");
		return builder.toString();
	}
	
}
