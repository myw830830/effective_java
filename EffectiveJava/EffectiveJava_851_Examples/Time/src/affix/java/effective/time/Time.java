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
 * derived from Object. Especially the toString() method should be defined presenting 
 * object state using format: HH:MM:SS.hh
 * <br/>
 */
public final class Time implements Comparable<Time> {

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
		this.hour = h;
		this.minute = m;
		this.second = s;
		this.hundred = d;
	}
	
	public int getHour(){
		return this.hour;
	}
	public int getMinute(){
		return this.minute;
	}
	public int getSecond(){
		return this.second;
	}
	public int getHundred(){
		return this.hundred;
	}	
	

	/**
	 * Overriding inherited method, defining equality for Time objects
	 * @param obj holding object to compare to
	 * @return boolean true if Time values are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if ( obj instanceof Time ) {
			Time time = (Time)obj;
			return (this.hour == time.getHour() && this.minute == time.getMinute()
					&& this.second == time.getSecond() && this.hundred == time.getHundred())? true: false;
		}

		return false;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int result = 17;
		result = result*31 + this.hour;
		result = result*31 + this.minute;	
		result = result*31 + this.second;	
		result = result*31 + this.hundred;	
		
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
		if (this.hour == that.getHour() && this.minute == that.getMinute()
				&& this.second == that.getSecond() && this.hundred == that.getHundred() ) {
			return 0;
		}
		else if (this.hour > that.getHour()) {
			return 1;
		}
		else if (this.hour == that.getHour() && this.minute > that.getMinute()) {
			return 1;
		}
		else if (this.hour == that.getHour() && this.minute == that.getMinute()
				&& this.second > that.getSecond()) {
			return 1;
		}
		else if (this.hour == that.getHour() && this.minute == that.getMinute()
				&& this.second == that.getSecond() && this.hundred > that.getHundred()) {
			return 1;
		}
		else {
			return -1;
		}
	}
	
	
	/**
	 * Converting object to human readable format
	 * @return String holding a full Time object
	 * Output format: HH:MM:SS.hh
	 */
	@Override
	public String toString(){		
		return String.format("%02d:%02d:%02d.%02d", this.hour, this.minute, this.second, this.hundred);
	}

	
}
