package affix.java.effective.moneyservice;

import java.util.Map;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * This is where the application MoneyService starts
 * 
 * The main application class ExpressExchange, the model, will be set up from here.
 * A GUI that simplifies user interaction will hold and communicate with the model instance. 
 * 
 * Reference currency is set to SEK in order to keep complexity down
 */
public class ExpressExchangeApp {

	public static void main(String[] args){
		
		// define reference currency
		Currency refCurrency = new Currency("SEK", "Sweden", 1.0, 1.0);
		final MoneyService ms = new ExpressExchange(refCurrency.getCode(), 10260);	

		// get current info for accepted currencies 
		Map<String, Currency> currMap = CurrencyFileSupport.getCurrencyMap("Currencies.txt");
	
		// set up initial amount for a number of Currencies
		Money[] startMoneys = 
		{ 
				new Money("EUR", 3000), 
				new Money("USD", 2000), 
				new Money("GBP", 1500),				
				new Money("NOK", 10000), 
				new Money("DKK", 10000), 
				new Money("CHF", 1500),
				new Money("RUB", 12000),
				new Money("AUD", 500),
				new Money("CNY", 10000)
		};	
		
		// configure site
		ms.configureService(currMap, startMoneys);
		
		// present accepted currencies for MoneyService
		System.out.println("Available currencies: ");
		for(String s : currMap.keySet()){
			System.out.println(currMap.get(s));
		}
					
		System.out.println("=================================");
		ms.printMoneyReport("Console");		
		System.out.println("=================================");
		
		
		Runnable guiRunner = new Runnable(){
			public void run(){
				JFrame jf = new ExpressExchangeGUI(ms);
				jf.setSize(350, 200);
				jf.setTitle("ExpressExchange");
				jf.setVisible(true);
			}
		};
		SwingUtilities.invokeLater(guiRunner);
	}
	
	
}
