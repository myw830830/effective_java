package affix.java.effective.field;


import affix.java.effective.uslength.USLength;

class LongJumpCompetitor extends AbstractFieldCompetitor{
	
	/**
	 * Static factory used instead of a public constructor
	 * @param name holding an id of this competitor
	 * @param n holding number of attempts to perform
	 * @return on success a LongJumpCompetitor instance
	 * @throws IllegalArgumentException if n is less than 1
	 */
	static LongJumpCompetitor createCompetitor(String name, int n) {	
		if(n<1){
			throw new IllegalArgumentException("A Long Jump Competitor must have at least 1 attempt");
		}
		return new LongJumpCompetitor(name, n);
	}
	
	/**
	 * Constructor used internally by static factory method
	 * @param name a String defining competitor id
	 * @param n an int holding number of attempts for this competitor
	 */
	private LongJumpCompetitor(String name, int n){
		super(name, n);
	}
	
	/** 
	 * This method produces a random value in the range 10 - 35 feet.
	 * In order to handle illegal jumps an Exception should be thrown if value is
	 * less than 15 or more than 30
	 * @return FieldResult simulating an attempt in a LongJump competition
	 */
	FieldResult doCompete(){
		int f = (int)(Math.random()*25)+10;
		int i = (int)(Math.random()*12);
		USLength value = new USLength(f, i);
		
		FieldResult fr = new FieldResult(value, getName());
		setAttempt(fr, getNextIndex());
		return fr;
	}
	
}
