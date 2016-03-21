package affix.java.effective.field;

import affix.java.effective.field.FieldUtils.FieldCompetitionType;
import affix.java.effective.trackandfield.TrackAndFieldCompetition;
import affix.java.effective.trackandfield.TrackAndFieldResult;

public class LongJumpApp {

	private static String[] testCompetitors = {
		"Jessie Owens",
		"Bob Beamon",
		"Carl Lewis"
	};
	
	/**
	 * This test routine simulates a LongJump competition 
	 */
	public static void main(String[] args){

		TrackAndFieldCompetition theCompetition = null;

		System.out.println("**************************************************** ");
		System.out.println("=== A new LongJump competition is starting here! === ");
		theCompetition = new LongJumpCompetition(testCompetitors, "Ultimate Long Jump");			
		theCompetition.compete();

		System.out.println("=============== Final results ====================== ");
		theCompetition.printResults();		
		System.out.println("==================================================== ");

		TrackAndFieldResult winner = (TrackAndFieldResult)theCompetition.getWinningResult();
		if(FieldUtils.compareToWorldRecord((FieldResult)winner, FieldCompetitionType.LONG_JUMP)){
			System.out.println(" New world record by "+winner.getId()+ "!");
			System.out.println("====================================================");
		}

		System.out.println("**************** Individual results ****************");
		AbstractFieldCompetitor[] competitors = ((AbstractFieldCompetition)theCompetition).getCompetitors();
		for(AbstractFieldCompetitor who : competitors){
			System.out.println(who);
		}
		
	}
	
}
