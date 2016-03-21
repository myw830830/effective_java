package affix.java.effective.collections;

import java.util.*;

public class CountryMap {
	
	// typesafe Map reference
	private Map<Integer, Country> countryMap;
	
	public CountryMap(String type){
		// choose parameterized type
		if(type.equals("Hash")){
			countryMap = new HashMap<>();
		}
		else{
			countryMap = new TreeMap<>();
		}
	}
	
	public void addCountry(Country c){
		int cc = c.getNumber();
		countryMap.put(cc, c);
	}
	
	public boolean removeCountry(Country c){
		int cc = c.getNumber();
		return (countryMap.remove(cc) != null);
	}
	
	public boolean containsCountry(Country c){		
		int cc = c.getNumber();
		return countryMap.containsKey(cc);
	}
	
	public int getNoOfCountries(){
		return countryMap.size();
	}
	
	public void listCountries(){		
		Set<Integer> keySet = countryMap.keySet();		
		// parameterized Iterator used
		Iterator<Integer> keyIter = keySet.iterator();
		while(keyIter.hasNext()){
			Integer key = keyIter.next();
			Country temp = countryMap.get(key);
			System.out.println(temp);
		}		
	}	
	
	public void printAllCountries(){		
		Set<Integer> keys = countryMap.keySet();		
		// for:each loop used
		for(Integer key : keys){
			Country temp = countryMap.get(key);
			System.out.println(temp);			
		}
	}	
	
	public void emptyMap(){		
		// Iterator used
		Iterator<Integer> iter = (countryMap.keySet()).iterator();
		while(iter.hasNext()){
			Integer key = iter.next();
			Country temp = countryMap.get(key);
			iter.remove();
			System.out.println("Object removed from collection "+temp);			
		}
	}

	public String getType() {
		return this.getClass().toString();
	}

}
