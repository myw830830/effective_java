package affix.java.effective.field;

import affix.java.effective.uslength.USLength;

/**
 * An immutable value class holding data representing a Field competition performance.
 */
public final class FieldResult implements Comparable<FieldResult> {
	
	/**
	 * A FieldResult will be measured using USLength
	 */
	private final USLength length;
	
	/**
	 * Owner of this FieldResult
	 */
	private final String id;
	
	/**
	 * Constructor
	 * @param length a USLength object holding measured value for a field competition 
	 * @param id a String defining id of athlete
	 */
	public FieldResult(USLength length, String id){
		this.length = length;
		this.id = id;
	}

	/**
	 *  Getter
	 *  @return a USLength object holding length value 
	 */
	public USLength getResult() {
		return length;
	}
	
	/**
	 *  Getter
	 *  @return a String holding id
	 */	
	public String getId() {
		return id;
	}

	/**
	 * Converting object state to human readable format
	 * @return String holding textual description of FieldResult object
	 * Output format:[ Competitor: {@code id} USLength: {@code foot' inch"}]
	 */
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();	
		sb.append(" Competitor: ");
		sb.append(id);
		sb.append(" ");
		sb.append(length);		
		return sb.toString();
	}

	/**
	 * Implementation of interface Comparable
	 * Delegate to USLength comparison method
	 * {@link affix.java.effective.uslength.USLength#compareTo compareTo}
	 */	
	@Override
	public int compareTo(FieldResult obj) {
		return this.length.compareTo(obj.length);
	}

}
