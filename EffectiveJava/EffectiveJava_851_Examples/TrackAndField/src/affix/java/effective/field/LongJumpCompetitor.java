package affix.java.effective.field;

import affix.java.effective.uslength.USLength;

/**
 * This class aggregates a number of results along with the name of a competitor.
 * This class is package private in order primarily to limit access to an instance
 * of LongJumpCompetition which is in control of a LongJump application
 */
class LongJumpCompetitor{
	
	/**
	 * Attribute name hold competitor id
	 */
	private final String name;
	
	/**
	 * Attribute jumps hold an array of results
	 */
	private final FieldResult[] jumps;
	
	/**
	 * Constructor 
	 * @param name a String defining competitor id
	 * @param n an int holding number of attempts for this competitor
	 * @throws IllegalArgumentException if number of jumps is less than 1
	 */
	LongJumpCompetitor(String name, int n){
		if(n<1){
			throw new IllegalArgumentException("A LongJump Competitor must have at least 1 attempt");
		}
		this.name = name;
		this.jumps = new FieldResult[n];
	}
	
	/**
	 * Getter of attribute name
	 * @return a String holding current Competitors name
	 */
	public String getName() {
		return name;
	}
	
	/** 
	 * This method produces a random value in the range 10 - 35 feet.
	 */
	void doJump(){
		int f = (int)(Math.random()*25)+10;
		int i = (int)(Math.random()*12);
		USLength value = new USLength(f, i);
		
		FieldResult fr = new FieldResult(value, name);
		jumps[getNextJumpIndex()] = fr;
	}
	
	/**
	 * Helper method calculating next index for a new jump
	 * @return an int holding next available index
	 */
	private int getNextJumpIndex(){
		int jumpIndex = 0;
		for(int i=0; (i<jumps.length && jumps[i]!=null); i++){
			jumpIndex++;
		}
		return jumpIndex;
	}
	
	/**
	 * Getter for array holding all jumps performed at a specific moment in a competition 
	 * NB! package private method, in order to prohibit external exposure
	 * @return an array holding all non-null objects
	 */
	FieldResult[] getPerformedJumps(){
		FieldResult[] tempArray = new FieldResult[getNextJumpIndex()];
		for(int i=0; i<getNextJumpIndex(); i++){
			tempArray[i] = jumps[i];
		}
		return tempArray;
	}
	
	/**
	 * Getter for specific array index
	 * NB! Defensive copying is not needed since object holds immutable data
	 * @return a FieldResult or null if not present
	 */
	public FieldResult getJump(int index){
		return jumps[index];
	}
	
	/**
	 * Getter complete array holding all jumps
	 * This should typically be called when competition is finished 
	 * Defensive copying is used in order not to manipulate original objects
	 * NB! Cloning is ok here since the array holds immutable data 
	 * @return a cloned array holding FieldResult objects
	 */
	public FieldResult[] getJumps(){
		return jumps.clone();
	}
	
	/**
	 * Converting object state to human readable format
	 * @return String holding textual description of LongJumpCompetitor object
	 * Output format: LongJumpCompetitor: <name> [FieldResult[]]
	 */
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		String lf = System.getProperty("line.separator");
		
		sb.append("LongJumpCompetitor: " + name + lf);
		for(FieldResult temp : jumps){
			sb.append(temp);
		}
		
		return sb.toString();
	}
}
