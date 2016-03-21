package affix.java.effective.collections;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class CheckPriorityQueue {

	private CountryQueue theQueue;
	
	public CheckPriorityQueue(){
		theQueue = new CountryQueue("Queue");
		System.out.println("========================================================");
		System.out.println("Testing collection type "+theQueue.getType());
		System.out.println("========================================================");
	}
	
	@Before
	public void setUp() throws Exception {
			
		Country aCountry = new Country();
		theQueue.addCountry(aCountry);	
		aCountry = new Country("Denmark", 45, true);
		theQueue.addCountry(aCountry);	
		aCountry = new Country("Norway", 47, false);
		theQueue.addCountry(aCountry);	
		aCountry = new Country("Finland", 358, true);
		theQueue.addCountry(aCountry);		
		aCountry = new Country("Iceland", 354, false);
		theQueue.addCountry(aCountry);	
		aCountry = new Country("England", 44, true);
		theQueue.addCountry(aCountry);			
		aCountry = new Country("Ireland", 353, true);
		theQueue.addCountry(aCountry);		
		aCountry = new Country("Germany", 49, true);
		theQueue.addCountry(aCountry);	
		aCountry = new Country("Ukraine", 380, false);
		theQueue.addCountry(aCountry);			
		aCountry = new Country("Sweden", 46, true);
		theQueue.addCountry(aCountry);
		
		System.out.println(" +++ Queue created +++ ");
		theQueue.printAllCountries();
	}
		
	@Test
	public void testGetNoOfObjects() {
		System.out.println("-----------  testGetNoOfObjects ----------- ");
		Assert.assertEquals(10, theQueue.getNoOfCountries());
//		theQueue.printAllCountries();
	}
	
	@Test
	public void testAddObject() {
		System.out.println("----------- testAddObject() ----------- ");
		Country test = new Country("Spain", 34, true);
		theQueue.addCountry(test);	
		System.out.println("--- Assert added object is in collection --- ");
		Assert.assertEquals(theQueue.containsCountry(test), true);
//		theQueue.printAllCountries();
	}
	
	@Test (expected = NullPointerException.class)
	public void testAddNullObject() {
		System.out.println("-----------  testAddNullObject() ----------- ");
		theQueue.addCountry(null);	
//		theQueue.printAllCountries();
	}

	@Test
	public void testRemoveObject() {
		System.out.println("-----------  testRemoveObject() ----------- ");
		Country test = new Country("Germany", 49, true);
		Assert.assertEquals(theQueue.removeCountry(test), true);
		System.out.println("--- Assert removed object is not in collection --- ");
		Assert.assertEquals(theQueue.containsCountry(test), false);
//		theQueue.printAllCountries();
	}

	@Test
	public void testRemoveNonexistingObject() {
		System.out.println("-----------  testRemoveNonexistingObject() ----------- ");
		Country test = new Country("Switzerland", 41, false);
		Assert.assertEquals(theQueue.removeCountry(test), false);
//		theQueue.printAllCountries();
	}

}
