package affix.java.effective.field;

import affix.java.effective.field.FieldUtils.FieldCompetitionType;


public class JavelinThrowCompetition extends AbstractFieldCompetition {
	
	/**
	 * Constant holding a fixed number of attempts
	 */
	public static final int THROWS = 6;
	
	public JavelinThrowCompetition(String[] ids, String competitionName) {
		
		super(FieldCompetitionType.JAVELIN_THROW, ids.length, competitionName);
		
		for(int i=0; i<ids.length; i++){
			competitors[i] = AbstractFieldCompetitor.createCompetitor(FieldCompetitionType.JAVELIN_THROW, ids[i], THROWS);
		}
	}
	
	@Override
	public void compete() {
		// produce a number of rounds
		for(int r=0; r<THROWS; r++){
			// produce a round where every Competitor has one attempt
			for(int i=0; i<competitors.length; i++){
				FieldResult result = competitors[i].doCompete();
				System.out.println(" --> "+result);
			}
			collectCurrentResults();
			printResults();
		}
	}

}
