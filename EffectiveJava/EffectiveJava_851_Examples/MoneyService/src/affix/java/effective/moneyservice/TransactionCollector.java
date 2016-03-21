package affix.java.effective.moneyservice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class TransactionCollector {

	private Map<String, Currency> currencyMap = null;
	private List<Transaction> transactions = null;
	
	/**
	 * Constructor setting up empty collections as fall-backs
	 */
	public TransactionCollector(){
		// get current info for accepted currencies
		currencyMap = CurrencyFileSupport.getCurrencyMap("Currencies.txt");
		transactions = new ArrayList<>();
	}
	
	/**
	 * This method deserializes a file holding Transaction objects
	 * @param filename a String holding the name of the original file
	 */
	public void readTransactions(String filename){
		// Add code here

	}
	
	/**
	 * This method presents all Transaction objects currently stored in collection transactions
	 */
	public void printTransactions(){
		
		System.out.println("Transactions in collection");
		// Add code here

		
		System.out.println("--------------------------------------------------------------------------------");
	}

	
	public static void main(String[] args) {

		// get all Transaction objects
		String filename = "TestTransactions.ser";
		TransactionCollector tc = new TransactionCollector();
		tc.readTransactions(filename);
		
		// print out Transactions to console
		tc.printTransactions();
		
//		// sort Transactions in currency order
//		tc.sortTransactionsByCurrency();
//		tc.printTransactions();
//		
//		// check up all transactions for a specific currency
//		String currencyCode = "EUR";
//		tc.calculateTransactionsValueForCurrency(currencyCode);
//		
//		// produce the grand total for all transactions of a specific currency
//		double totalTransactionsValue = tc.sumUpTransactionValue(currencyCode);
//		System.out.println(String.format("Total value for transactions in %s in reference currency: %6.1f ", currencyCode, totalTransactionsValue));

		
	}


	/**
	 * Helper method focusing on Transaction objects of a specific currency
	 * @param currencyCode a String holding the required currency
	 */
	private void calculateTransactionsValueForCurrency(String currencyCode) {
		// Add code here
		
		// Create stream from collection
		
		// add filter for chosen currency
		
		// transform value to reference currency
		
		// sort in falling order
		
		// finally print out to console
		System.out.println("----------------------------------------------------------------------------");
		System.out.println("Transactions for currency " + currencyCode +" transformed to reference currency");

		// Add code here
		
	}
	
	/**
	 * Defining Function valueTransformFunction used in the presentation method below
	 * This should be defined using a lambda expression wherein the value of a Transaction
	 * object should be transformed into reference Currency applying buy- or sell-rate
	 * The outcome should be a Double > 0 for SELL operations and < 0 for BUY operations
	 */
	private Function<Transaction, Double> valueTransformFunction = (Transaction t) -> {

			Currency curr = currencyMap.get(t.getMoney().getCurrencyCode());
			// separate handling of BUY/SELL
			if(t.getMode().equals(TransactionMode.BUY)){
				return -t.getMoney().getAmount() * curr.getBuyRate();
			}
			else{
				return t.getMoney().getAmount() * curr.getSellRate();
			}
		};


	/**
	 * This is a helper method that will go thru all Transaction objects and sum up the
	 * total value of those which match the passed Currency code
	 * @param currencyCode a String holding the Currency id to use
	 * @return a double holding the total value of all supplied transactions
	 */
	private double sumUpTransactionValue(String currencyCode) {
		double total = 0.0;
		
		// Add code here
		
		
		return total;
	}
	
	/**
	 * Another helper method that calls a customized Comparator for sorting
	 * Transaction objects based on the defined criterion in the Comparator
	 */
	private void sortTransactionsByCurrency(){
		// Add code here
		
	}
}

/**
 * This class defines a customized Comparator for Transaction objects
 * The criterion for comparison is here defined by the currency code
 * that reveals the Currency the Transaction is dealing with
 * For objects holding the same currency code a second comparison of TransactionMode
 * should be defined
 */
class TransactionComparator implements Comparator<Transaction>{
	
	@Override
	public int compare(Transaction t1, Transaction t2) {
		// Add code here
		
		return 0;
	}
	
}