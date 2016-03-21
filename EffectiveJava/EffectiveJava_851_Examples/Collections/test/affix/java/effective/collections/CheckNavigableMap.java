package affix.java.effective.collections;

import java.util.NavigableMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class CheckNavigableMap {

	private CountryNavigableMap theMap;
	
	public CheckNavigableMap(){
		theMap = new CountryNavigableMap();
		System.out.println("========================================================");
		System.out.println("Testing collection type " + theMap.getType());
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
	public void testHeadMap() {
		System.out.println("----------- testHeadMap() ----------- ");
		
		System.out.println("+++ Country objects numbered 1-100 ");
		NavigableMap<Integer, Country> hm = theMap.getHeadCodes(100);
		Assert.assertEquals(5, hm.size());
		Assert.assertEquals(44, (hm.firstKey()).intValue());
	}
	
	@Test
	public void testTailMap() {
		System.out.println("-----------  testTailMap() ----------- ");
		System.out.println("--- Country objects numbered 100 and above");
		NavigableMap<Integer, Country> tm = theMap.getTailCodes(100);
		Assert.assertEquals(4, tm.size());
		Assert.assertEquals(380, (tm.lastKey()).intValue());
	}

	@Test
	public void testSubMap() {
		System.out.println("-----------  testSubMap() ----------- ");
		System.out.println("*** Country objects from Scandinavia ");
		NavigableMap<Integer, Country> sm = theMap.getCodesInRange(45, 47);
		Assert.assertEquals(3, sm.size());
		Assert.assertEquals(47, (sm.floorKey(50)).intValue());
		Assert.assertEquals(45, (sm.ceilingKey(40)).intValue());
	}


}

