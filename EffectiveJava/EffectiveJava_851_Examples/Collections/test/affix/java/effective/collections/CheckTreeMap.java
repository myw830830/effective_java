package affix.java.effective.collections;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CheckTreeMap {

	private CountryMap theMap;
	
	public CheckTreeMap(){
		theMap = new CountryMap("Tree");
		System.out.println("========================================================");
		System.out.println("Testing collection type "+theMap.getType());
		System.out.println("========================================================");
	}
	
	@Before
	public void setUp() throws Exception {
			
		Country aCountry = new Country();
		theMap.addCountry(aCountry);	
		aCountry = new Country("Denmark", 45, true);
		theMap.addCountry(aCountry);	
		aCountry = new Country("Norway", 47, false);
		theMap.addCountry(aCountry);	
		aCountry = new Country("Finland", 358, true);
		theMap.addCountry(aCountry);		
		aCountry = new Country("Iceland", 354, false);
		theMap.addCountry(aCountry);	
		aCountry = new Country("England", 44, true);
		theMap.addCountry(aCountry);			
		aCountry = new Country("Ireland", 353, true);
		theMap.addCountry(aCountry);		
		aCountry = new Country("Germany", 49, true);
		theMap.addCountry(aCountry);	
		aCountry = new Country("Ukraine", 380, false);
		theMap.addCountry(aCountry);			
		aCountry = new Country("Sweden", 46, true);
		theMap.addCountry(aCountry);
		
		System.out.println(" +++ Map created +++ ");
		theMap.printAllCountries();
	}
	
	@Test
	public void testGetNoOfObjects() {
		System.out.println("-----------  testGetNoOfObjects ----------- ");
		Assert.assertEquals(9, theMap.getNoOfCountries());
//		theMap.printAllCountries();
	}

	@Test
	public void testAddObject() {
		System.out.println("----------- testAddObject() ----------- ");
		Country test = new Country("Spain", 34, true);
		theMap.addCountry(test);	
		System.out.println("--- Assert added object is in collection --- ");
		Assert.assertEquals(theMap.containsCountry(test), true);
//		theMap.printAllCountries();
	}
	
	@Test (expected = NullPointerException.class)
	public void testAddNullObject() {
		System.out.println("-----------  testAddNullObject() ----------- ");
		theMap.addCountry(null);	
//		theMap.printAllCountries();
	}

	@Test
	public void testRemoveObject() {
		System.out.println("-----------  testRemoveObject() ----------- ");
		Country test = new Country("Germany", 49, true);
		Assert.assertEquals(theMap.removeCountry(test), true);
		System.out.println("--- Assert removed object is not in collection --- ");
		Assert.assertEquals(theMap.containsCountry(test), false);
//		theMap.printAllCountries();
	}

	@Test
	public void testRemoveNonexistingObject() {
		System.out.println("-----------  testRemoveNonexistingObject() ----------- ");
		Country test = new Country("Switzerland", 41, false);
		Assert.assertEquals(theMap.removeCountry(test), false);
//		theMap.printAllCountries();
	}
	
}
