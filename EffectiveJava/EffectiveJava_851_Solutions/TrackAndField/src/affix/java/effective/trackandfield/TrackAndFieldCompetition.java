package affix.java.effective.trackandfield;

/**
 * This interface defines methods that are applicable for any type of Track & Field competition.
 * The two main branches will implement methods somewhat differently
 * Track competitions use time measuring while Field competitions use length measuring 
 * Some competitions offer more than one attempt, some delivers result after a single performance
 */
public interface TrackAndFieldCompetition {
	
	/**
	 * This method shows the possibility to define a default implementation in Java 8
	 */
	default public String getCompetitionName(){
		return "TrackAndField Competition";
	}	
	
	/**
	 * Accessor method for results array after performing sort
	 * In order to make this immutable perform defensive copying
	 * @return a cloned array
	 */

	public Object[] getResults();	
	/**
	 * Getter of participants of a competition
	 * @return an array holding all competitors
	 */
	public Object[] getCompetitors();	
	
	/**
	 * This method presents current results after sorting
	 */
	public void printResults();
			
	/**
	 * This method takes care of the competition process
	 */
	public void compete();
	
	/**
	 * This is a default method added to the interface
	 * @return Object holding winning result of the competition
	 */
	default public Object getWinningResult(){
		return getResults()[0];
	}
}
