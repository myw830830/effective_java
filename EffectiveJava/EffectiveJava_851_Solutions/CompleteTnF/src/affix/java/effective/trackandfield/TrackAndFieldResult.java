package affix.java.effective.trackandfield;

public interface TrackAndFieldResult {
	
	/**
	 *  Getter
	 *  @return an Object holding a result 
	 */
	public Object getResult();
	
	/**
	 *  Getter
	 *  @return a String identifying the owner of this result 
	 */
	public String getId();
}
