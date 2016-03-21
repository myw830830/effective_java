package affix.java.effective.track;

import affix.java.effective.time.Time;
import affix.java.effective.trackandfield.TrackAndFieldCompetition;

public class SteepleChaseApp {
	
	private static String[] testCompetitors = {
		"Penti Karvonen",
		"Anders Gärderud",
		"Moses Kiptanui"
	};
	
	public final static TrackResult worldRecord = new TrackResult(new Time(0, 7, 53, 10), "Moses Kiptanui"); 
	
	public static void main(String[] args){
		
		System.out.println("********************************************************");
		System.out.println("=== A new SteepleChase competition is starting here! ===");
//		SteepleChaseCompetition theCompetition = new SteepleChaseCompetition(testCompetitors, "TestCompetition");
		TrackAndFieldCompetition theCompetition = new SteepleChaseCompetition(testCompetitors, "");
		
		theCompetition.compete();
		
		TrackResult winner = (TrackResult) theCompetition.getResults()[0];
		if(winner.compareTo(worldRecord) < 0){
			System.out.println(" New world record by "+winner.getId()+ "!");
		}
	}
	
}
