package affix.java.effective.time;

/**
 * Immutable class Time is required to follow some restrictions:
 * <br/>
 * The characteristics of this type is to handle a 24 hour time stamp, by using four 
 * attributes, covering the common time concept of hour, minute, second and hundreds of seconds.
 * <br/>
 * Creation of Time instances is done by calling one of two constructors, 
 * passing 0 or 4 numerical parameters.
 * Default constructor should create objects where all attributes are set to 0.
 * Parameters should be of int type, range of valid parameter values are:
 * 	hour 	 0 - 23
 *  minute	 0 - 59
 *  second	 0 - 59
 *  hundred  0 - 99
 * Any invalid parameter values should trigger an IllegalArgumentException
 * <br/>
 * In order to comply with normal requirements that apply for value classes implementation
 * of the Comparable interfaces should be present as well as overriding some methods 
 * derived from Object. Especially the toString( ) method should be defined presenting 
 * object state using format HH:MM:SS.hh.
 * <br/>
 */
public final class Time implements Comparable<Time>, java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// attributes
	private final int hour;
	private final int minute;
	private final int second;
	private final int hundred;
	
	// some constants defining limits for time attributes
	public final static int HOUR_MIN = 0;
	public final static int HOUR_MAX = 23;
	public final static int MINUTE_MIN = 0;
	public final static int MINUTE_MAX = 59;
	public final static int SECOND_MIN = 0;
	public final static int SECOND_MAX = 59;	
	public final static int HUNDRED_MIN = 0;
	public final static int HUNDRED_MAX = 99;
	
	/**
	 * Default constructor
	 */
	public Time(){
		this(HOUR_MIN, MINUTE_MIN, SECOND_MIN, HUNDRED_MIN);
	}
	


	/**
	 * Overloaded constructor
	 * @param h holding inparam for hour
	 * @param m holding inparam for minute
	 * @param s holding inparam for second
	 * @param d holding inparam for hundred
	 * @throws IllegalArgumentException when any argument is out of limits
	 */
	public Time(int h, int m, int s, int d){
		
		if(checkParams(h, m, s, d)){
			this.hour = h;
			this.minute = m;
			this.second = s;
			this.hundred = d;
		}
		else{
			throw new IllegalArgumentException("Parameter value(s) out of limits");
		}
	}
	
	public int getHour(){
		return hour;
	}
	public int getMinute(){
		return minute;
	}
	public int getSecond(){
		return second;
	}
	public int getHundred(){
		return hundred;
	}	
	

	/**
	 * Overriding inherited method, defining equality for Time objects
	 * @param obj holding object to compare to
	 * @return boolean true if Time values are equal
	 */
	@Override
	public boolean equals(Object obj) {

		if(obj instanceof Time){
			Time that = (Time)obj;
			if(this.hour==that.hour && this.minute==that.minute && this.second==that.second && this.hundred==that.hundred){
				return true;
			}
		}
		return false;	
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		int result = 13;
		result = result*31 + hour;
		result = result*31 + minute;
		result = result*31 + second;
		result = result*31 + hundred;
		
		return result;
	}



	/**
	 * Overriding inherited method, defining natural order for Time objects
	 * @param that holding Time object to compare this to
	 * @return int value defining how to order this and that objects 
	 *  positive if this should be ordered after that
	 * 	negative if this should be ordered before that 
	 *  zero if Time values are equal
	 */
	@Override
	public int compareTo(Time that) {

		if(this.hour==that.hour && this.minute==that.minute && this.second==that.second && this.hundred==that.hundred){
			return 0;
		}
		else{
			return (this.hour > that.hour
					|| this.hour==that.hour && this.minute > that.minute
					|| this.hour==that.hour && this.minute==that.minute && this.second > that.second
					|| this.hour==that.hour && this.minute==that.minute && this.second==that.second && this.hundred > that.hundred
					)? 1: -1;
		}
	}
	
	
	/**
	 * Converting object to human readable format
	 * @return String holding a full Time object
	 * Output format: HH:MM:SS.hh
	 */
	@Override	
	public String toString(){
	
		StringBuilder sb = new StringBuilder();
		sb.append(twoDigitString(hour));
		sb.append(":");
		sb.append(twoDigitString(minute));
		sb.append(":");
		sb.append(twoDigitString(second));
		sb.append(".");
		sb.append(twoDigitString(hundred));
		return sb.toString();
	}

	private String twoDigitString(int value) {
		if(value < 10){
			return "0"+value;
		}
		else{
			return String.valueOf(value);
		}
	}

	/**
	 * This method checks numeric parameters for validity
	 * @param h holding inparam for hour
	 * @param m holding inparam for minute
	 * @param s holding inparam for second
	 * @param d holding inparam for hundred
	 * @return boolean true if params ok
	 */
	public static boolean checkParams(int h, int m, int s,  int d){
		if(!(h >= HOUR_MIN && h <= HOUR_MAX)){
			return false;
		}
		if(!(m >= MINUTE_MIN && m <= MINUTE_MAX)){
			return false;
		}
		if(!(s >= SECOND_MIN && s <= SECOND_MAX)){
			return false;
		}
		if(!(d >= HUNDRED_MIN && d <= HUNDRED_MAX)){
			return false;
		}

		return true;
	}
	
	
	
//	public String toString(){
//		return String.format("%02d:%02d:%02d.%02d", hour, minute, second, hundred);	
//	}
}
