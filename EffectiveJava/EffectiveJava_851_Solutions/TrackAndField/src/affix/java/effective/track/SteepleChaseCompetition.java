package affix.java.effective.track;


public class SteepleChaseCompetition extends AbstractTrackCompetition{

	private final SteepleChaseCompetitor[] competitors;
	
	public SteepleChaseCompetition(String[] ids, String competitionName){	
				
		super(ids.length, competitionName);
		
		competitors = new SteepleChaseCompetitor[ids.length];
		
		for(int i=0; i<ids.length; i++){
			competitors[i] = new SteepleChaseCompetitor(ids[i]);
		}
	}
	
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
			TrackResult tr = competitors[i].doRun();
			setResult(i, tr);
		}
	}

}
