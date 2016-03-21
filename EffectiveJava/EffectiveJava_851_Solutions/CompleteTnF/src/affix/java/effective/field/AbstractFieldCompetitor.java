package affix.java.effective.field;

import affix.java.effective.trackandfield.AbstractCompetitor;
import affix.java.effective.uslength.USLength;

public abstract class AbstractFieldCompetitor extends AbstractCompetitor{
	
	/**
	 * Attribute attempts holds an array of results
	 */
	private final FieldResult[] attempts;
	
	
	/**
	 * Static factory used instead of a public constructor
	 * @param competition the specific type of Field Competition
	 * @param name holding an id of this competitor
	 * @param n holding number of attempts to perform
	 * @return on success a AbstractFieldCompetitor instance
	 */
	static AbstractFieldCompetitor createCompetitor(FieldUtils.FieldCompetitionType competition, String name, int n) {
		
		switch(competition){
		case LONG_JUMP:
			return LongJumpCompetitor.createCompetitor(name, n);
			
		case JAVELIN_THROW:
			return  JavelinThrowCompetitor.createCompetitor(name, n);
			default:
				throw new IllegalArgumentException(competition.toString() + "not supported!");
		}

	}
	
	/**
	 * Constructor used internally by static factory method
	 */
	protected AbstractFieldCompetitor(String name, int n){	
		super(name);
		this.attempts = new FieldResult[n];
	}
	
	/**
	 * Getter for array holding all attempts performed at a specific moment in a competition 
	 * NB! package private method, in order to prohibit external exposure
	 * @return an array holding all non-null objects
	 */
	FieldResult[] getPerformedAttempts(){
		FieldResult[] tempArray = new FieldResult[getNextIndex()];
		for(int i=0; i<getNextIndex(); i++){
			tempArray[i] = attempts[i];
		}
		return tempArray;
	}
	
	/**
	 * Helper method calculating next index for a new attempt
	 * @return an int holding next available index
	 */
	protected int getNextIndex(){
		int attemptIndex = 0;
		for(int i=0; (i<attempts.length && attempts[i]!=null); i++){
			attemptIndex++;
		}
		return attemptIndex;
	}
	
	/**
	 * Getter for specific array index
	 * @return a FieldResult or null if not present
	 */
	public FieldResult getAttempt(int index){
		return new FieldResult((USLength) attempts[index].getResult(), attempts[index].getId());
	}
	
	/**
	 * Setter for specific array index
	 * @param result a FieldResult
	 * @param index where to store value
	 */
	protected void setAttempt(FieldResult result, int index){
		attempts[index] = result;
	}
	
	/**
	 * Getter complete array holding all attempts
	 * This should typically be called when competition is finished 
	 * Defensive copying is used in order not to manipulate original objects 
	 * @return a deeply cloned array holding FieldResult objects
	 */
	public FieldResult[] getAttempts(){
		FieldResult[] tempArray = new FieldResult[attempts.length];
		for(int i=0; i<attempts.length; i++){
			tempArray[i] = new FieldResult((USLength) attempts[i].getResult(), attempts[i].getId());
		}
		return tempArray;
	}
	
	/**
	 * Converting object state to human readable format
	 * @return String holding textual description of JavelinThrowCompetitor object
	 * Output format: <Competitor>: <name> [FieldResult[]]
	 */
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		String lf = System.getProperty("line.separator");	
		String competitorType = this.getClass().getSimpleName();
		
		sb.append(competitorType + ": " + getName() + lf);
		for(FieldResult temp : attempts){
			sb.append(temp + lf);
		}
		
		return sb.toString();
	}

	abstract FieldResult doCompete();
	
}
