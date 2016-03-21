package affix.java.effective.collections;

import java.util.*;

public class CountryCollection {
	
	// type safe Collection reference
	private Collection<Country> col;
	
	public CountryCollection(String type){
		// choose parameterized type
		if(type.equals("List")){
			col = new ArrayList<>();
		}
		else{
			if(type.equals("Set")){
				col = new HashSet<>();
			}		
			else{
				col = new TreeSet<>();
			}
		}
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

	public String getType() {
		return this.getClass().toString();
	}

}
