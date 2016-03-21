package affix.java.effective.field;

import affix.java.effective.field.FieldUtils.FieldCompetitionType;

/**
 * The class creates LongJumpCompetitor objects by calling a static factory method in a 
 * class LongJumpCompetitor.
 * 
 * A derived class that implements abstract methods defined in base class
 * Configuration details are passed to constructor which sets up attributes
 * in this class and the inherited package private abstract base class
 * The only exposed method, apart from the constructor, is the implemented derived
 * method that is triggered from an application class
 * 
 * The class holds an array of result objects that is only accessible within this package
 * via call to the super class accessor methods setResult() and getResults() 
 * Competition outcome is determined by sorting results held in this array.
 * A general available print method will produce output to console 
 *
 */
public class LongJumpCompetition extends AbstractFieldCompetition {
	
	/**
	 * Constant holding a fixed number of attempts
	 */
	public static final int JUMPS = 6;
	
	
	/**
	 * Constructor setting up a LongJump competition
	 * @param ids holding names of competitors
	 */
	public LongJumpCompetition(String[] ids, String competitionName){
		
		super(FieldCompetitionType.LONG_JUMP, ids.length, competitionName);
		
		for(int i=0; i<ids.length; i++){
			competitors[i] = AbstractFieldCompetitor.createCompetitor(FieldCompetitionType.LONG_JUMP, ids[i], JUMPS);
		}
	}

	/**
	 * @see affix.java.effective.trackandfield.TrackAndFieldCompetition#compete
	 */
	@Override
	public void compete(){
		// produce a number of rounds
		for(int r=0; r<JUMPS; r++){
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
