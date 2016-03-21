package affix.java.effective.track;

import affix.java.effective.time.Time;
import affix.java.effective.track.TrackUtils.TrackCompetitionType;

public class SteepleChaseApp {

	private static String[] testCompetitors = {
		"Penti Karvonen",
		"Anders Gärderud",
		"Moses Kiptanui"
	};

	public final static TrackResult worldRecord = new TrackResult(new Time(0, 7, 53, 10), "Moses Kiptanui"); 
	
	public static void main(String[] args){

		String competitionName = "A race with obstacles!";
		AbstractTrackCompetition theCompetition = new SteepleChaseCompetition(testCompetitors, competitionName);
		theCompetition.compete();
		System.out.println("=========================================================");
		theCompetition.printResults();
		System.out.println("=========================================================");

		TrackResult winningResult = (TrackResult) theCompetition.getWinningResult();
//		if(winningResult.compareTo(worldRecord) < 0){
//			System.out.println("New world record by "+winningResult.getId()+ "!");
//		}
		
		boolean status = TrackUtils.compareToWorldRecord(winningResult, TrackCompetitionType.STEEPLE_CHASE);
		if(status){
			System.out.println("New world record by "+winningResult.getId()+ "!");
		}
	}

}
