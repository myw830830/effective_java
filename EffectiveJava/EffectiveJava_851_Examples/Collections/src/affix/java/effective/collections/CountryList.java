package affix.java.effective.collections;

import java.util.*;

public class CountryList {
	
	// type safe Collection reference
	private List<Country> col;
	
	public CountryList(){
		col = new ArrayList<>();
	}
	
	public boolean addCountry(Country c){
		return col.add(c);
	}
	
	public boolean removeCountry(Country c){
		return col.remove(c);
	}
	
	public boolean containsCountry(Country test){
		return col.contains(test);
	}

	public int getNoOfCountries(){
		return col.size();
	}
	
	public void listCountries(){
		// Iterator used
		Iterator<Country> iter = col.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());			
		}
	}
	public void printAllCountries(){
		// for:each loop used
		for(Country temp: col){
			System.out.println(temp);
		}
	}
	
	public void emptyCollection(){
		
		// Iterator used
		Iterator<Country> iter = col.iterator();
		while(iter.hasNext()){
			Country temp = iter.next();
			iter.remove();
			System.out.println("Object removed from collection "+temp);			
		}
	}
	
	List<Country> getList() {
		return Collections.unmodifiableList(col);
	}

	public String getType() {
		return this.getClass().toString();
	}


}
