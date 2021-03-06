package affix.java.effective.uslength;

import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestUSLengthMultipleData {

	private USLength batchOfData;
	
	/**
	 * This method will supply data to tests via inparam to constructor
	 * @return a collection of data that will be iterated over when referenced
	 */
	@Parameters
	public static List<USLength[]> paramSetUp(){

		return Arrays.asList(new USLength[][]{
				
				{new USLength(USLength.FOOT_MIN, USLength.INCH_MIN)},
				{new USLength(USLength.FOOT_MIN, USLength.INCH_MAX)},
				{new USLength(23, USLength.INCH_MIN)},
				{new USLength(7, USLength.INCH_MAX)}
		});
	}
	
	/**
	 * Constructor that interacts with method annotated using @Parameters
	 * @param params input data for multiple tests
	 * NB! Tests will look for data returned from static method paramSetUp()!
	 */
	public TestUSLengthMultipleData(USLength params){
		this.batchOfData = params;
	}
	
	/**
	 * This test will run tests using supplied test data by iterating over all
	 * available sets of data returned from paramSetUp. 
	 * A number of tests will be produced and run in succession.
	 */
	@Test
	public void dataDrivenCreation() {
		
		System.out.println("Running test using data " + batchOfData);
		USLength usl = batchOfData;

		String expected = formatOutput(usl);
		assertEquals(expected, usl.toString());
	}

	private String formatOutput(USLength usl){
		
		StringBuilder sb = new StringBuilder();
		sb.append("USLength: ");
		sb.append(usl.getFoot());
		sb.append("' ");
		sb.append(usl.getInch());
		sb.append("\"");
		return sb.toString();
	}


}
