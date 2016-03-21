package affix.java.effective.time;

import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestTimeMultipleData {

	private Time batchOfData;
	
	/**
	 * This method will supply data to tests via inparam to constructor
	 * @return a collection of data that will be iterated over when referenced
	 */
	@Parameters
	public static List<Time[]> paramSetUp(){

		return Arrays.asList(new Time[][]{
				
				{new Time(Time.HOUR_MIN, Time.MINUTE_MIN, Time.SECOND_MIN, Time.HUNDRED_MIN)},
				{new Time(Time.HOUR_MIN, Time.MINUTE_MIN, Time.SECOND_MAX, Time.HUNDRED_MAX)},
				{new Time(Time.HOUR_MIN, Time.MINUTE_MAX, Time.SECOND_MIN, Time.HUNDRED_MIN)},
				{new Time(Time.HOUR_MIN, Time.MINUTE_MAX, Time.SECOND_MAX, Time.HUNDRED_MAX)},

				{new Time(Time.HOUR_MAX, Time.MINUTE_MIN, Time.SECOND_MIN, Time.HUNDRED_MIN)},
				{new Time(Time.HOUR_MAX, Time.MINUTE_MIN, Time.SECOND_MAX, Time.HUNDRED_MAX)},
				{new Time(Time.HOUR_MAX, Time.MINUTE_MAX, Time.SECOND_MIN, Time.HUNDRED_MIN)},
				{new Time(Time.HOUR_MAX, Time.MINUTE_MAX, Time.SECOND_MAX, Time.HUNDRED_MAX)}
		});
	}
	
	/**
	 * Constructor that interacts with method annotated using @Parameters
	 * @param params input data for multiple tests
	 * NB! Tests will look for data returned from static method paramSetUp()!
	 */
	public TestTimeMultipleData(Time params){
		this.batchOfData = params;
	}
	
	/**
	 * This test will run tests using supplied test data by iterating over all
	 * available sets of data returned from paramSetUp. 
	 * A number of tests will be produced and run in succession.
	 */
	@Test
	public void datadrivenCreation() {
		
		System.out.println("Running test using data " + batchOfData);
		Time td = batchOfData;

		String expected = formatOutput(td);
		assertEquals(expected, td.toString());
	}

	private String formatOutput(Time td){
		
		StringBuilder sb = new StringBuilder();
		if(td.getHour()<10){
			sb.append("0"+td.getHour()+":");
		}
		else{
			sb.append(td.getHour()+":");
		}
		if(td.getMinute()<10){
			sb.append("0"+td.getMinute()+":");
		}
		else{
			sb.append(td.getMinute()+":");
		}
		if(td.getSecond()<10){
			sb.append("0"+td.getSecond()+".");
		}
		else{
			sb.append(td.getSecond()+".");
		}
		if(td.getHundred()<10){
			sb.append("0"+td.getHundred());
		}
		else{
			sb.append(td.getHundred());
		}
		return sb.toString();
	}


}
