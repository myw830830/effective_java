package affix.java.effective.field;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import affix.java.effective.field.FieldUtils.FieldCompetitionType;
import affix.java.effective.trackandfield.ResultBoard;
import affix.java.effective.trackandfield.TrackAndFieldCompetition;
import affix.java.effective.uslength.USLength;

/**
 * A general class for holding an array of result objects produced in a TrackAndFieldCompetition.
 * This class is abstract and should only be a holder of general data
 * Inherited classes will define the number of results to handle 
 */
abstract class AbstractFieldCompetition implements TrackAndFieldCompetition{
	
	private final FieldResult[] results;

	private final FieldCompetitionType type;

	private final String competitionName;

	protected AbstractFieldCompetitor[] competitors;

	/**
	 * Constructor setting up specific parameters for a field competition
	 * @param type one of defined by enum FieldCompetionType
	 * @param n number of participants in this competition
	 * @param name the name of this competition
	 */
	public AbstractFieldCompetition(FieldCompetitionType type, int n, String name){
		this.type = type;
		results = new FieldResult[n];
		this.competitionName = name;
		competitors = new AbstractFieldCompetitor[n];
	}

	/**
	 * Getter for attribute type
	 * @return enum FieldCompetitionType holding special type 
	 */
	public FieldCompetitionType getFieldCompetionType(){
		return type;
	}
	
	/**
	 * @see affix.java.effective.trackandfield.TrackAndFieldCompetition#getName
	 */
	@Override
	public String getCompetitionName() {
		return competitionName;
	}
	
	/**
	 * @see affix.java.effective.trackandfield.TrackAndFieldCompetition#getCompetitors
	 */
	@Override
	public AbstractFieldCompetitor[] getCompetitors(){
		AbstractFieldCompetitor[] tempList = new AbstractFieldCompetitor[competitors.length];
		for(int i=0; i<competitors.length; i++){
			tempList[i] = competitors[i];
		}
		return tempList;
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
		calcStandings();
		FieldResult[] tempArray = new FieldResult[results.length];
		for(int i=0; i<results.length; i++){
			tempArray[i] = new FieldResult((USLength) results[i].getResult(), results[i].getId());
		}
		return tempArray;
	}
	
	/**
	 * This method collects best result from every competitor
	 * sorts these results in falling order by calling standard routines
	 */
	protected void collectCurrentResults(){
		// collect best result from each competitor, store in result array
		for(int c=0; c<competitors.length; c++){
			FieldResult[] attempts = competitors[c].getPerformedAttempts();
			Arrays.sort(attempts);
			FieldResult best = attempts[attempts.length-1];
			this.setResult(c, best);
		}
		calcStandings();
	}
	
	/**
	 * This calculates current competition standings by sorting all results
	 * in falling order by calling standard routines
	 */
	protected void calcStandings(){
		Arrays.sort(results);
		List<FieldResult> tempList = Arrays.asList(results);
		Collections.reverse(tempList);
		for(int i=0; i<results.length; i++){
			results[i] = tempList.get(i);
		}
	}
	
	/**
	 * @see affix.java.effective.trackandfield.TrackAndFieldCompetition#printResult
	 */
	@Override
	public void printResults(){
		calcStandings();
		ResultBoard rb = ResultBoard.getInstance();
		rb.printResults(competitionName, getResults());
	}
	
}
