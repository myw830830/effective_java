package affix.java.effective.field;

import affix.java.effective.trackandfield.TrackAndFieldCompetition;
import affix.java.effective.uslength.USLength;

/**
 * This class is simulating a LongJump competition from start to end.
 * A set of imaginary competitors are defined and passed as parameters to
 * a LongJumpCompetition instance. The competition is simulated by creating
 * a number of random values for each competitor representing real performances.
 * Comparison of best result to current world record will complete the competition.
 */
public class LongJumpApp {

	private static String[] testCompetitors = {
		"Jessie Owens",
		"Bob Beamon",
		"Carl Lewis"
	};

	private static FieldResult worldRecord = new FieldResult(new USLength(29, 3), "Mike Powell");

	/**
	 * This test routine simulates a LongJump competition 
	 */
	public static void main(String[] args){

		System.out.println(" ********************************************************** ");
		System.out.println(" =====  A new LongJump competition is starting here!  ===== ");
		TrackAndFieldCompetition theCompetition = new LongJumpCompetition(testCompetitors, "Ultimate Long Jump");			
		theCompetition.compete();

		System.out.println(" ================== Final results ========================= ");
		theCompetition.printResults();
		System.out.println(" ========================================================== ");

		FieldResult winner = (FieldResult)theCompetition.getWinningResult();
		if(winner.compareTo(worldRecord) > 0){
			System.out.println(" New world record by "+winner.getId()+ "!");
		}
	}

}
