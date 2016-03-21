package affix.java.effective.collections;

import java.util.Iterator;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeMap;

public class CountryNavigableMap {
	
	// typesafe TreeMap reference
	private NavigableMap<Integer, Country> countryMap;
	
	public CountryNavigableMap(){
		countryMap = new TreeMap<>();
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
	
	public NavigableMap<Integer, Country> getHeadCodes(int headEnd){	
		
		NavigableMap<Integer, Country> headMap = countryMap.headMap(headEnd, true);
		
		// parameterized Iterator used
		Iterator<Integer> keyIter = headMap.keySet().iterator();
		while(keyIter.hasNext()){
			Integer key = keyIter.next();
			Country temp = countryMap.get(key);
			System.out.println(temp);
		}		
		System.out.println("Highest Key "+headMap.floorKey(headEnd));

		return headMap;
	}	
	
	public NavigableMap<Integer, Country> getTailCodes(int tailStart){
		
		NavigableMap<Integer, Country> tailMap = countryMap.tailMap(tailStart, false);
		
		// parameterized Iterator used
		Iterator<Integer> keyIter = tailMap.keySet().iterator();
		while(keyIter.hasNext()){
			Integer key = keyIter.next();
			Country temp = countryMap.get(key);
			System.out.println(temp);
		}		
		System.out.println("Lowest Key "+tailMap.ceilingKey(tailStart));
		
		return tailMap;
	}
	
	
	public NavigableMap<Integer, Country> getCodesInRange(int from, int to){		
		NavigableMap<Integer, Country> subMap = countryMap.subMap(from, true, to, true);
		
		NavigableSet<Integer> keys = subMap.navigableKeySet();
	
		// for:each loop used
		for(Integer key : keys){
			Country temp = countryMap.get(key);
			System.out.print(temp.getNumber()+" ");			
			System.out.println(temp);			
		}
		
		int mid = (from + to)/2;
		System.out.println("Number for mid range object "+mid);
		System.out.println("Key next to center(lower)  "+keys.floor(mid));
		System.out.println("Key next to center(higher) "+keys.ceiling(mid));
		
		return subMap;
	}

	public String getType() {
		return this.getClass().toString();
	}	
	
}
