package affix.java.effective.track;

import affix.java.effective.track.AbstractTrackCompetition;
import affix.java.effective.track.SteepleChaseCompetitor;

public class SteepleChaseCompetition extends AbstractTrackCompetition {

	private final SteepleChaseCompetitor[] competitors;
	
	public SteepleChaseCompetition(String[] ids, String competitionName){	
		super(ids.length, competitionName);		
		competitors = new SteepleChaseCompetitor[ids.length];		
		for(int i=0; i<ids.length; i++){
			competitors[i] = new SteepleChaseCompetitor(ids[i]);
		}
	}

//	public String getCompetitionName() {
//		return super.getCompetitionName();
//	}
	
	public SteepleChaseCompetitor[] getCompetitors() {
		SteepleChaseCompetitor[] temp = new SteepleChaseCompetitor[competitors.length];
		for(int i=0; i<competitors.length; i++){
			temp[i] = competitors[i];
		}
		return temp;
	}
	
	public void compete(){
		// produce a race where every Competitor has one attempt
		for(int i=0; i<competitors.length; i++){
			competitors[i].doRun();			
			super.setResult(i, competitors[i].getResult());
		}		
		super.sortResults();
		printResults();
	}
}
