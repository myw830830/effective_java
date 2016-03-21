package affix.java.effective.moneyservice;

import java.io.*;
import java.util.*;

/**
 * This class sets up a Map holding vital info for a MoneyService implementor by reading
 * current data from a configuration file.
 */
public class CurrencyFileSupport{

	private CurrencyFileSupport() {;}
	
	/**
	 * This method reads info stored in a file holding data for code, country, buy rate and sell rate
	 * for every available Currency
	 * 
	 * @param configurator holding the name of the file to look for configuration data
	 * @return Map<String, Currency> holds a collection of Currencies mapped to their code 
	 */
	public static Map<String, Currency> getCurrencyMap(String configurator){
		
		File currencyFile = new File(configurator);
		
		Map<String, Currency> currencyMap = new TreeMap<String, Currency>();
		
		// Java7 try-with-resources
		try (BufferedReader br = new BufferedReader(new FileReader(currencyFile));){			
			while(br.ready()){
				String line = br.readLine();
				String[] parts = line.split("\t");
				String code = parts[0];
				String country = parts[1];
				Float buy = Float.parseFloat(parts[2]);
				Float sell = Float.parseFloat(parts[3]);
				Currency temp = new Currency(code, country, buy, sell);
				currencyMap.put(code, temp);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return currencyMap;
	}
	
}
