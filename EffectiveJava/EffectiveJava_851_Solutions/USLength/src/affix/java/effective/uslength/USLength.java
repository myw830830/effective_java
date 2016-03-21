package affix.java.effective.uslength;

import affix.java.effective.annotations.EffectiveJava;

/**
 * USLength is an immutable class (defined by modifier final)
 * This class should be an example of a correctly defined class
 * including overridden Object class methods where appropriate.
 * Instances of this class is available for storing in sorted
 * collections via the implementation of the Comparable interface
 */
@EffectiveJava(item=0 , description="Annotated Class")
public final class USLength implements Comparable<USLength>{
		
	/**
	 * Constants defining limits for attributes
	 */
	public static final int FOOT_MIN = 0;
	public static final int INCH_MIN = 0; 
	public static final int INCH_MAX = 11;
	
	/**
	 * Attribute holding most important part of a USLength object
	 */
	@EffectiveJava(item=15, description="Minimize mutability")
	private final int foot;
	
	/**
	 * Attribute holding least important part of a USLength object
	 */
	@EffectiveJava(item=15, description="Minimize mutability")
	private final int inch;
	
	/**
	 * Default constructor delegating to overloaded version
	 */
	public USLength(){
		this(FOOT_MIN, INCH_MIN);
	}
	
	/**
	 * Overloaded constructor
	 * @param f holding foot value
	 * @param i holding inch value
	 * @throws IllegalArgumentException if any of the passed parameters are negative
	 * or if inch part exceeds limit 11
	 */
	@EffectiveJava(item=38, description="Check parameters for validity")
	public USLength(int f, int i){
		checkParams(f, i);
		foot = f;
		inch = i;
	}
	
	/**
	 * Overloaded constructor
	 * Parsing an input String in order to supply values for a USLength object
	 * @param s holds USLength value in String format <foot>' <inch>"
	 * @throws IllegalArgumentException if parameter is not parsable
	 */
	@EffectiveJava(item=50 , description="Avoid strings where other types are more appropriate")
	public USLength(String s){
		int footPos = s.indexOf("\'");
		int inchPos = s.indexOf("\"");
		if(footPos == -1 || inchPos == -1){
			throw new IllegalArgumentException("String is not parsable: " + s);
		}
		int f = Integer.parseInt(s.substring(0, footPos));
		int i = Integer.parseInt(s.substring(footPos + 2, inchPos));
		
		checkParams(f, i);
		
		this.foot = f;
		this.inch = i;		
	}
	
	/**
	 * This method checks numeric parameters for validity
	 * @param f holding foot value
	 * @param i holding inch value
	 * @return boolean true if params ok
	 * @throws IllegalArgumentException if any of the passed parameters are negative
	 * or if inch part exceeds limit 11
	 */
	public static boolean checkParams(int f, int i){
		if(f<FOOT_MIN){
			throw new IllegalArgumentException("Illegal parameter value: "+f);
		}
		if(i<INCH_MIN || i>INCH_MAX){
			throw new IllegalArgumentException("Illegal parameter value: "+i);
		}
		return true;
	}
	
	/**
	 * Getter
	 * @return int holding foot value
	 */
	@EffectiveJava(item= 14, description="Use accessor methods, not public fields")
	public int getFoot(){
		return foot;
	}
	
	/**
	 * Getter
	 * @return int holding inch value
	 */
	@EffectiveJava(item= 14, description="Use accessor methods, not public fields")
	public int getInch(){
		return inch;
	}
	

	/**
	 * Contents comparison method
	 * @param obj holding USLength instance to compare to
	 * @return boolean true if this and obj are equal, otherwise false
	 */	
	@EffectiveJava(item=8, description="Obey the general contract when overriding equals")
	@Override
	public boolean equals(Object obj){

		boolean status = false;
		// this test will also cover test of null objects
		if(obj instanceof USLength){
			USLength that = (USLength)obj;

			if( (this.foot == that.foot) && (this.inch == that.inch) ){
				status = true;
			}
		}

		return status;
	}

	
	/**
	 * Calculation of object characteristic code based on attribute values 
	 * @return int holding hashcode
	 */
	@EffectiveJava(item=9 , description="Always override hashCode when overriding equals")
	@Override
	public int hashCode(){
		int result = 17;
		result = result*31 + foot;
		result = result*31 + inch;	
		
		return result;
	}
	
	/**
	 * Converting object state to human readable format
	 * @return String holding textual description of USLength object
	 * Output format: USLength: {@code foot' inch"}
	 */
	@EffectiveJava(item=10 , description="Always override toString")
	@Override
	public String toString(){

		StringBuilder sb = new StringBuilder();
		sb.append("USLength: ");
		sb.append(foot + "' ");
		sb.append(inch+"\"");	
		return sb.toString();
	}
	
	/**
	 * @see java.lang.Comparable#compareTo
	 */
	@EffectiveJava(item=12 , description="Consider implementing Comparable")
	@Override
	public int compareTo(USLength that){

		int result;

		if(this.foot == that.foot && this.inch == that.inch){
			result = 0;
		}
		else{
			result = (this.foot > that.foot 
					|| this.foot == that.foot && this.inch > that.inch)? 1: -1;
		}

		return result;
	}	
	
	@EffectiveJava(item=7, description="Avoid finalizers")
	@Override
	protected void finalize() throws Throwable{;}
}
