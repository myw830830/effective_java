package affix.java.effective.field;

import affix.java.effective.field.FieldUtils.FieldCompetitionType;
import affix.java.effective.trackandfield.TrackAndFieldCompetition;
import affix.java.effective.trackandfield.TrackAndFieldResult;

public class JavelinThrowApp {

	private static String[] testCompetitors = {
		"Jan Zelezny",
		"Andreas Thorkildsen",
		"Steve Backley"
	};
	
	/**
	 * This test routine simulates a JavelinThrow competition 
	 */
	public static void main(String[] args){

		TrackAndFieldCompetition theCompetition = null;

		System.out.println("**************************************************** ");
		System.out.println("=== A new JavelinThrow competition is starting here! === ");
		theCompetition = new JavelinThrowCompetition(testCompetitors, "Throw that spear!");			
		theCompetition.compete();

		System.out.println("=============== Final results ====================== ");
		theCompetition.printResults();
		System.out.println("==================================================== ");

		TrackAndFieldResult winner = (TrackAndFieldResult)theCompetition.getWinningResult();
		if(FieldUtils.compareToWorldRecord((FieldResult)winner, FieldCompetitionType.JAVELIN_THROW)){
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