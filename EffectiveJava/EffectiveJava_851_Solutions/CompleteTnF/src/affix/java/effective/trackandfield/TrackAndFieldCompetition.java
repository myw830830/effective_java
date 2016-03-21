package affix.java.effective.trackandfield;

/**
 * This interface defines methods that are applicable for any type of Track & Field competition.
 * The two main branches will implement methods somewhat differently
 * Track competitions use time measuring while Field competitions use length measuring 
 * Some competitions offer more than one attempt, some delivers result after a single performance
 * 
 */
public interface TrackAndFieldCompetition {
	
	/**
	 * This method shows the possibility to define a default implementation in Java 8
	 */
	default public String getCompetitionName(){
		return "TrackAndField Competition";
	}	
	
	/**
	 * Getter for competitors
	 * @return AbstractCompetitor[] holding list of current competitors
	 */
	public AbstractCompetitor[] getCompetitors();	
	
	/**
	 * Accessor method for results array after performing sort
	 * In order to make this immutable perform defensive copying
	 * @return a cloned array
	 */
	public AbstractResult[] getResults();	
	
	/**
	 * This method presents current result after sorting
	 */
	public void printResults();
			
	/**
	 * This method takes care of the competition process
	 */
	public void compete();

	/**
	 * This method returns the winning result of the competition
	 */
	default public Object getWinningResult(){
		return getResults()[0];
	}
}
