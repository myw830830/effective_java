package affix.java.effective.moneyservice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	@SuppressWarnings("unchecked")
	public void readTransactions(String filename){
		Object obj = MoneyServiceUtils.readTransactions(filename);
		if (obj != null) {
			transactions = (List<Transaction>) obj;
		}

	}
	
	/**
	 * This method presents all Transaction objects currently stored in collection transactions
	 */
	public void printTransactions(){
		
		System.out.println("Transactions in collection");
		
		for (Transaction trans : transactions) {
			System.out.println(trans);
		}
		
		System.out.println("--------------------------------------------------------------------------------");
	}

	
	public static void main(String[] args) {

		// get all Transaction objects
		String filename = "Transactions.ser";
		TransactionCollector tc = new TransactionCollector();
		tc.readTransactions(filename);
		
		// print out Transactions to console
		tc.printTransactions();
		
		// sort Transactions in currency order
		tc.sortTransactionsByCurrency();
		tc.printTransactions();
		
		// check up all transactions for a specific currency
		String currencyCode = "EUR";
		tc.calculateTransactionsValueForCurrency(currencyCode);
		
		// produce the grand total for all transactions of a specific currency
		double totalTransactionsValue = tc.sumUpTransactionValue(currencyCode);
		System.out.println(String.format("Total value for transactions in %s in reference currency: %6.1f ", currencyCode, totalTransactionsValue));
		
		// create a Map<String, List<Transaction>> where Currency code is the key
		Map<String, List<Transaction>> transactionMap = tc.produceTransactionMap();
		tc.printTransactionMap(transactionMap);
	}


	/**
	 * Helper method focusing on Transaction objects of a specific currency
	 * @param currencyCode a String holding the required currency
	 */
	private void calculateTransactionsValueForCurrency(String currencyCode) {
		// Create stream from collection
		Stream<Transaction> transStream = transactions.stream();
		
		// add filter for chosen currency
		Stream<Transaction> currencyTransStream = transStream.filter(t -> t.getCurrencyCode().equals(currencyCode));
		
		// transform value to reference currency
		Stream<Double> refCurrTransStream = currencyTransStream.map(valueTransformFunction);
		
		// sort in falling order
		Stream<Double> sortRefCurrTransStream = refCurrTransStream.sorted(Comparator.reverseOrder());
		
		// finally print out to console
		System.out.println("----------------------------------------------------------------------------");
		System.out.println("Transactions for currency " + currencyCode +" transformed to reference currency");
		
		Consumer<Double> outputConsum = (value) -> System.out.println(String.format("Transaction value: %6.1f", value));

		sortRefCurrTransStream.forEach(outputConsum);
		
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
		
		// sum up in falling order
		total = transactions.stream().filter(t -> t.getCurrencyCode().equals(currencyCode))
				.map(valueTransformFunction).collect(Collectors.summingDouble(x -> x));
		
		return total;
	}
	
	private Map<String, List<Transaction>> produceTransactionMap() {
		
		Map<String, List<Transaction>> transactionMap = new TreeMap<>();
		
		transactionMap = transactions.stream()
				.collect(Collectors.groupingBy(Transaction::getCurrencyCode));
		
		return transactionMap;
	}
	
	private void printTransactionMap(Map<String, List<Transaction>> transactionMap) {
		
		System.out.println();
		System.out.println("----------------------------------------------------------------------------");
		System.out.println("Contents of transactionMap ordered by currency code");
		
		transactionMap.keySet().stream()
		.forEachOrdered( key -> System.out.println(key+" "+transactionMap.get(key)) );
	}
	
	/**
	 * Another helper method that calls a customized Comparator for sorting
	 * Transaction objects based on the defined criterion in the Comparator
	 */
	private void sortTransactionsByCurrency(){
		Collections.sort(transactions, Comparator.comparing(Transaction::getCurrencyCode)
				.thenComparing(Comparator.comparing(Transaction::getMode)));		
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
		int firstCriteria = t1.getMoney().getCurrencyCode().compareTo(t2.getMoney().getCurrencyCode());
		if(firstCriteria == 0){
			int secondCriteria = t1.getMode().compareTo(t2.getMode());
			return secondCriteria;
		}
		else{
			return firstCriteria;
		}
	}	
}