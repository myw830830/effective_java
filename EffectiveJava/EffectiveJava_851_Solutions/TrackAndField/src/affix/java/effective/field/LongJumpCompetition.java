package affix.java.effective.field;

import java.util.Arrays;

/**
 * This is a derived class that implements abstract methods defined in base class
 * Configuration details are passed to constructor which sets up attributes
 * in this class and the inherited abstract base class.
 */
public class LongJumpCompetition extends AbstractFieldCompetition {

	/**
	 * Internal attribute not directly exposed to outside
	 */
	private final LongJumpCompetitor[] competitors;
	
	/**
	 * Constant holding a fixed number of attempts
	 */
	public static final int JUMPS = 6;
	
	/**
	 * Constructor setting up a LongJump competition
	 * @param ids holding names of competitors
	 */
	public LongJumpCompetition(String[] ids, String competitionName){
		
		super(ids.length, competitionName);
		
		competitors = new LongJumpCompetitor[ids.length];
		
		for(int i=0; i<ids.length; i++){
			competitors[i] = new LongJumpCompetitor(ids[i], JUMPS);
		}
	}
	
	/**
	 * @see affix.java.effective.trackandfield.TrackAndFieldCompetition#getCompetitors
	 */
	@Override
	public LongJumpCompetitor[] getCompetitors(){
		LongJumpCompetitor[] temp = new LongJumpCompetitor[competitors.length];
		for(int i=0; i<competitors.length; i++){
			temp[i] = competitors[i];
		}
		return temp;
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
				competitors[i].doJump();
			}
			collectCurrentResults();
			printResults();
		}
	}
	
	/**
	 * This helper method collects best result from every competitor
	 * sorts these results in falling order by calling standard routines
	 */
	private void collectCurrentResults(){
		// collect best result from each competitor, store in result array
		for(int c=0; c<competitors.length; c++){
			FieldResult[] jumps = competitors[c].getPerformedJumps();
			Arrays.sort(jumps);
			FieldResult best = jumps[jumps.length-1];
			this.setResult(c, best);
		}
		super.calcStandings();
	}	
}
