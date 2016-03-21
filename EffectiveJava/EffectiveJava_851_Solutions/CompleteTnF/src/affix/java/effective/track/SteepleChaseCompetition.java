package affix.java.effective.track;

import affix.java.effective.track.TrackUtils.TrackCompetitionType;
import affix.java.effective.trackandfield.AbstractCompetitor;

public class SteepleChaseCompetition extends AbstractTrackCompetition {

	private final SteepleChaseCompetitor[] competitors;
	
	public SteepleChaseCompetition(String[] names, String competitionName) {
		super(TrackCompetitionType.STEEPLE_CHASE, names.length, competitionName);
		competitors = new SteepleChaseCompetitor[names.length];
		
		for(int i=0; i<names.length; i++){
			competitors[i] = SteepleChaseCompetitor.createSteepleChaseCompetitor(names[i]);
		}
	}
			
	public void compete(){
		for(int i=0; i<competitors.length; i++){			
			competitors[i].doRun();
		}
		collectCurrentResults();
	}
	
	private void collectCurrentResults(){
		for(int i=0; i<competitors.length; i++){
			setResult(i, competitors[i].getResult());
		}
		super.calcStandings();
	}

	/**
	 * @see affix.java.effective.trackandfield.TrackAndFieldCompetition#getCompetitors
	 */
	@Override
	public AbstractCompetitor[] getCompetitors(){
		AbstractCompetitor[] tempList = new AbstractCompetitor[competitors.length];
		for(int i=0; i<competitors.length; i++){
			tempList[i++] = competitors[i];
		}
		return tempList;
	}
}
