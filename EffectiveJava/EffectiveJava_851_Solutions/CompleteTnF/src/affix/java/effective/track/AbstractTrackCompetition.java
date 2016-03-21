package affix.java.effective.track;

import java.util.Arrays;
import affix.java.effective.time.Time;
import affix.java.effective.track.TrackUtils.TrackCompetitionType;
import affix.java.effective.trackandfield.ResultBoard;
import affix.java.effective.trackandfield.TrackAndFieldCompetition;

public abstract class AbstractTrackCompetition implements TrackAndFieldCompetition{

	private final TrackCompetitionType type;
	private final String competitionName;
	private final TrackResult[] results;
	
	
	public AbstractTrackCompetition(TrackCompetitionType type, int n, String competitionName) {
		this.type = type;
		this.competitionName = competitionName;
		this.results = new TrackResult[n];
	}

	@Override
	public String getCompetitionName() {
		return competitionName;
	}
	
	public TrackCompetitionType getTrackCompetitionType() {
		return type;
	}
	
	/**
	 * @param x holding current competitor index
	 * @param result a result to set for index x
	 */
	protected void setResult(int x, TrackResult result) {
		results[x] = result;
	}
	
	@Override
	public TrackResult[] getResults() {
		calcStandings();
		TrackResult[] tempArray = new TrackResult[results.length];
		for(int i=0; i<results.length; i++){
			tempArray[i] = new TrackResult((Time) results[i].getResult(), results[i].getId());
		}
		return tempArray;
	}

	@Override
	public void printResults(){
		calcStandings();
		ResultBoard rb = ResultBoard.getInstance();
		rb.printResults(competitionName, getResults());		
	}
	
	protected void calcStandings(){
		Arrays.sort(results);
	}
}
