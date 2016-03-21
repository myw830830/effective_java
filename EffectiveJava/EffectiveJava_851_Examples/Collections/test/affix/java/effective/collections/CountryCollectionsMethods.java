package affix.java.effective.collections;

import java.util.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CountryCollectionsMethods {
	
	private CountryList theList;
	
	public CountryCollectionsMethods(){
		theList = new CountryList();
		System.out.println("========================================================");
		System.out.println("Testing collection type "+theList.getType());
		System.out.println("========================================================");
	}
	
	@Before
	public void setUp() throws Exception {
			
		Country aCountry = new Country();
		theList.addCountry(aCountry);	
		aCountry = new Country("Denmark", 45, true);
		theList.addCountry(aCountry);	
		aCountry = new Country("Norway", 47, false);
		theList.addCountry(aCountry);	
		aCountry = new Country("Finland", 358, true);
		theList.addCountry(aCountry);		
		aCountry = new Country("Iceland", 354, false);
		theList.addCountry(aCountry);	
		aCountry = new Country("England", 44, true);
		theList.addCountry(aCountry);			
		aCountry = new Country("Ireland", 353, true);
		theList.addCountry(aCountry);		
		aCountry = new Country("Germany", 49, true);
		theList.addCountry(aCountry);	
		aCountry = new Country("Ukraine", 380, false);
		theList.addCountry(aCountry);			
		
		System.out.println(" +++ Collection created +++ ");
		theList.printAllCountries();
	}


	@Test
	public void checkMaxValue(){
		
		Country max = Collections.max(theList.getList());
		System.out.println("Max value: " + max);
		Assert.assertEquals(new Country("Ukraine", 380, false), max);
	}
	
	@Test
	public void checkMinValue(){
		
		Country min = Collections.min(theList.getList());
		System.out.println("Min value: " + min);
		Assert.assertEquals(new Country("England", 44, true), min);
	}

	@Test
	public void checkSorting(){	
		
		List<Country> tempCol = new ArrayList<>(theList.getList());
		Collections.sort(tempCol);
		System.out.println("Sorted values");
		for(Country c: tempCol){
			System.out.println(c);
		}
		Assert.assertEquals(new Country("Denmark", 45, true), tempCol.get(1));
	}
		
	@Test
	public void checkReverseOrder(){	
		
		List<Country> tempCol = new ArrayList<>(theList.getList());
		Collections.sort(tempCol);
		Collections.reverse(tempCol);
		System.out.println("Values in reverse order");
		for(Country c: tempCol){
			System.out.println(c);
		}
		Assert.assertEquals(new Country("Finland", 358, true), tempCol.get(1));
	}
	
	@Test
	public void checkRandomOrder(){
		
		List<Country> tempCol = new ArrayList<>(theList.getList());
		Collections.shuffle(tempCol);
		System.out.println("Shuffled values");
		theList.listCountries();
		Assert.assertEquals(9, theList.getNoOfCountries());
	}

}
