package affix.java.effective.field;

import affix.java.effective.uslength.USLength;

public class JavelinThrowCompetitor extends AbstractFieldCompetitor{

	/**
	 * Static factory used instead of a public constructor
	 * @param name holding an id of this competitor
	 * @param n holding number of attempts to perform
	 * @return on success a JavelinThrowCompetitor instance
	 * @throws IllegalArgumentException if n is less than 1
	 */
	public static JavelinThrowCompetitor createCompetitor(String name, int n) {
		if(n<1){
			throw new IllegalArgumentException("A JavelinThrow Competitor must have at least 1 attempt");
		}
		return new JavelinThrowCompetitor(name, n);
	}
	
	/**
	 * Constructor used internally by static factory method
	 * @param name a String defining competitor id
	 * @param n an int holding number of attempts for this competitor
	 */
	private JavelinThrowCompetitor(String name, int n){	
		super(name, n);
	}


	/** 
	 * This method produces a random value in the range 200 - 350 feet (60m - 105m).
	 * In order to handle illegal throws an Exception should be thrown if value is out of range
	 * @return FieldResult simulating an attempt in a JavelinThrow competition
	 */
	FieldResult doCompete(){
		int f = (int)(Math.random()*150)+200;
		int i = (int)(Math.random()*12);
		USLength value = new USLength(f, i);
		
		FieldResult fr = new FieldResult(value, getName());
		setAttempt(fr, getNextIndex());
		return fr;
	}
	

}
