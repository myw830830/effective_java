package affix.java.effective.track;

import java.util.Arrays;

import affix.java.effective.track.TrackResult;
import affix.java.effective.trackandfield.TrackAndFieldCompetition;


public abstract class AbstractTrackCompetition implements TrackAndFieldCompetition {

	private final String competitionName;	
	private final TrackResult[] results;
	
	
	/**
	 * Constructor setting up specific parameters for a field competition
	 * @param n number of participants in this competition
	 * @param name the name of this competition
	 */
	protected AbstractTrackCompetition(int n, String name){
		results = new TrackResult[n];
		this.competitionName = name;
	}
	
	@Override
	public String getCompetitionName() {
		return competitionName;
	}
	
	/**
	 * Setter for an element in attribute results
	 * @param index position in array
	 * @param obj the TrackResult to store
	 */
	protected void setResult(int index, TrackResult obj){
		results[index] = obj;
	}

	@Override
	public TrackResult[] getResults() {
		return results.clone();
	}
	
	@Override
	public void printResults() {
		System.out.println(" ---------------------------------------------------------- ");
		System.out.println(" Result from competition " + getCompetitionName());
		for(int i=0; i<results.length; i++){
			System.out.println(results[i]);
		}
		System.out.println(" ---------------------------------------------------------- ");
	}
	
	/**
	 * This calculates current competition standings by sorting all results
	 * in falling order by calling standard routines
	 */
	protected void sortResults() {
		Arrays.sort(results);
	}

}
