package affix.java.effective.field;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import affix.java.effective.trackandfield.TrackAndFieldCompetition;

/**
 * A general class for holding an array of result objects produced in a TrackAndFieldCompetition.
 * This class is abstract and should only be a holder of general data
 * Inherited classes will define the number of results to handle 
 */
abstract class AbstractFieldCompetition implements TrackAndFieldCompetition{
	
	private final String competitionName;	
	private final FieldResult[] results;
	
	/**
	 * Constructor setting up specific parameters for a field competition
	 * @param n number of participants in this competition
	 * @param name the name of this competition
	 */
	protected AbstractFieldCompetition(int n, String name){		
		results = new FieldResult[n];
		this.competitionName = name;
	}
		
	/**
	 * @see affix.java.effective.trackandfield.TrackAndFieldCompetition#getCompetitionName
	 */
	@Override
	public String getCompetitionName() {
		return competitionName;
	}
	
	/**
	 * Setter for an element in attribute results
	 * @param index position in array
	 * @param obj the FieldResult to store
	 */
	protected void setResult(int index, FieldResult obj){
		results[index] = obj;
	}
	
	/**
	 * @see affix.java.effective.trackandfield.TrackAndFieldCompetition#getResults
	 */
	@Override
	public FieldResult[] getResults(){
		return results.clone();
	}
	
	/**
	 * This calculates current competition standings by sorting all results
	 * in falling order by calling standard routines
	 */
	protected void calcStandings(){
		
		Arrays.sort(results);
		
		List<FieldResult> tempList = Arrays.asList(results);
		Collections.reverse(tempList);
		
		FieldResult[] tempResults = (FieldResult[]) tempList.toArray();
		
		for(int i=0; i<results.length; i++){
			results[i] = tempResults[i];
		}

	}
	
	/**
	 * @see affix.java.effective.trackandfield.TrackAndFieldCompetition#printResults
	 */
	@Override
	public void printResults(){
		
		calcStandings();
		
		System.out.println(" ---------------------------------------------------------- ");
		System.out.println(" Result from competition " + getCompetitionName());
		for(int i=0; i<results.length; i++){
			System.out.println(results[i]);
		}
		System.out.println(" ---------------------------------------------------------- ");
	}
	
}
