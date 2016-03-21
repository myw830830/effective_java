package affix.java.effective.field;

import affix.java.effective.trackandfield.AbstractResult;
import affix.java.effective.uslength.USLength;

/**
 * An immutable value class
 */
public final class FieldResult extends AbstractResult implements Comparable<FieldResult>{
	
	/**
	 * Constructor
	 * @param length a USLength object holding measured value for a field competition 
	 * @param id a String defining id of athlete
	 */
	public FieldResult(USLength length, String id){
		super(length, id);
	}


	/**
	 * Converting object state to human readable format
	 * @return String holding textual description of FieldResult object
	 * Output format: Competitor: &ltid&gt [USLength: ({@code foot' inch"})]
	 */
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();	
		sb.append(" Competitor: " + getId() + " ");
		sb.append((USLength)getResult());		
		return sb.toString();
	}

	/**
	 * Implementation of interface Comparable
	 * Delegate to USLength comparison method
	 * {@link affix.java.effective.uslength.USLength#compareTo compareTo}
	 */
	@Override
	public int compareTo(FieldResult obj) {
		USLength thisLength = (USLength)getResult();
		return thisLength.compareTo((USLength)obj.getResult());
	}

}
