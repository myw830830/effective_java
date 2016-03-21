package affix.java.effective.moneyservice;


/**
 * This class represents a transaction performed by the MoneyService system.
 * A transaction is described by chosen Currency, actual amount, and buy/sell mode.
 * Class Money defines Currency and amount and is used frequently in the system.
 * Bookkeeping requires that all transaction also holds a unique id.
 * A Transaction object represents a value class and should be design as immutable.
 */
public final class Transaction {

	private final Money money;
	private final TransactionMode mode;
	private final int id;
	private static int uniqueId = 1;
	
	/**
	 * Constructor
	 * @param anOrder holding complete Order data
	 */
	public Transaction(Order anOrder){
		
		this.money = new Money(anOrder.getCurrencyCode(), anOrder.getAmount());
		this.mode = anOrder.getMode();
		this.id = uniqueId++;
	}


	/**
	 * @return the money
	 */
	public Money getMoney() {
		return new Money(money.getCurrencyCode(), money.getAmount());
	}

	/**
	 * @return the currencyCode
	 */
	public String getCurrencyCode() {
		return money.getCurrencyCode();
	}
	
	/**
	 * @return the mode
	 */
	public TransactionMode getMode() {
		return mode;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("Transaction [id=");
		builder.append(id);
		builder.append(", mode=");
		builder.append(mode);
		builder.append(", money=");
		builder.append(money);
		builder.append("]");
		return builder.toString();
	}
	
	public static void main(String[] args) throws InterruptedException{
		
		Money m1 = new Money("USD", 100);
		Money m2 = new Money("GBP", 50);
		
		// test some Transaction candidates
		Transaction t1 = new Transaction(new Order(m1.getCurrencyCode(), (int) m1.getAmount(), TransactionMode.BUY));
		System.out.println("Transaction: " + t1);
		Thread.sleep(1000);
		
		Transaction t2 = new Transaction(new Order(m2.getCurrencyCode(), (int) m2.getAmount(), TransactionMode.SELL));
		System.out.println("Transaction: " + t2);
		Thread.sleep(1000);

		Transaction t3 = new Transaction(new Order(m1.getCurrencyCode(), (int) m1.getAmount(), TransactionMode.SELL));
		System.out.println("Transaction: " + t3);
		Thread.sleep(2000);

		Transaction t4 = new Transaction(new Order(m2.getCurrencyCode(), (int) m2.getAmount(), TransactionMode.BUY));
		System.out.println("Transaction: " + t4);
		
	}
}
