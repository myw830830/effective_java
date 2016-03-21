package affix.java.effective.collections;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CheckHashSet {

	private CountryCollection theCollection;
	
	public CheckHashSet(){
		theCollection = new CountryCollection("Set");
		System.out.println("========================================================");
		System.out.println("Testing collection type "+theCollection.getType());
		System.out.println("========================================================");
	}
	
	@Before
	public void setUp() throws Exception {
			
		Country aCountry = new Country();
		theCollection.addCountry(aCountry);	
		aCountry = new Country("Denmark", 45, true);
		theCollection.addCountry(aCountry);	
		aCountry = new Country("Norway", 47, false);
		theCollection.addCountry(aCountry);	
		aCountry = new Country("Finland", 358, true);
		theCollection.addCountry(aCountry);		
		aCountry = new Country("Iceland", 354, false);
		theCollection.addCountry(aCountry);	
		aCountry = new Country("England", 44, true);
		theCollection.addCountry(aCountry);			
		aCountry = new Country("Ireland", 353, true);
		theCollection.addCountry(aCountry);		
		aCountry = new Country("Germany", 49, true);
		theCollection.addCountry(aCountry);	
		aCountry = new Country("Ukraine", 380, false);
		theCollection.addCountry(aCountry);			
		aCountry = new Country("Sweden", 46, true);
		theCollection.addCountry(aCountry);
		
		System.out.println(" +++ Collection created +++ ");
		theCollection.printAllCountries();
	}

	@Test
	public void testGetNoOfObjects() {
		System.out.println("-----------  testGetNoOfObjects ----------- ");
		Assert.assertEquals(9, theCollection.getNoOfCountries());
//		theCollection.printAllCountries();
	}
	
	@Test
	public void testAddObject() {
		System.out.println("----------- testAddObject() ----------- ");
		Country test = new Country("Spain", 34, true);
		theCollection.addCountry(test);	
		System.out.println("--- Assert added object is in collection --- ");
		Assert.assertEquals(theCollection.containsCountry(test), true);
//		theCollection.printAllCountries();
	}
	
	@Test
	public void testAddNullObject() {
		System.out.println("-----------  testAddNullObject() ----------- ");
		theCollection.addCountry(null);	
//		theCollection.printAllCountries();
	}

	@Test
	public void testRemoveObject() {
		System.out.println("-----------  testRemoveObject() ----------- ");
		Country test = new Country("Germany", 49, true);
		Assert.assertEquals(theCollection.removeCountry(test), true);
		System.out.println("--- Assert removed object is not in collection --- ");
		Assert.assertEquals(theCollection.containsCountry(test), false);
//		theCollection.printAllCountries();
	}

	@Test
	public void testRemoveNonexistingObject() {
		System.out.println("-----------  testRemoveNonexistingObject() ----------- ");
		Country test = new Country("Switzerland", 41, false);
		Assert.assertEquals(theCollection.removeCountry(test), false);
//		theCollection.printAllCountries();
	}


}
