package affix.java.effective.track;

import java.util.Arrays;
import affix.java.effective.trackandfield.TrackAndFieldCompetition;


public abstract class AbstractTrackCompetition implements TrackAndFieldCompetition {

	private final String competitionName;	
	private final TrackResult[] results;
	
	protected AbstractTrackCompetition(int n, String name){
		results = new TrackResult[n];
		this.competitionName = name;
	}
	
	
	@Override
	public String getCompetitionName() {
		return competitionName;
	}

	@Override
	public TrackResult[] getResults() {
		Arrays.sort(results);
		return results.clone();
	}

	protected void setResult(int index, TrackResult obj){
		results[index] = obj;
	}
	
	@Override
	public void printResults() {
		
		Arrays.sort(results);
		
		System.out.println("-----------------------------------------");
		System.out.println("Results from " + getCompetitionName());
		for(int i=0; i<results.length; i++){
			System.out.println(results[i]);
		}
		System.out.println("-----------------------------------------");		
	}

}
