package affix.java.effective.collections;

import java.util.Queue;
import java.util.PriorityQueue;
import java.util.ArrayDeque;

public class CountryQueue {
	
	// type safe Queue reference
	private Queue<Country> que;
	
	public CountryQueue(String type){
		// choose parameterized type
		if(type.equals("Queue")){
			que = new PriorityQueue<>();
		}
		else{
			que = new ArrayDeque<>();
		}
	}
	
	/**
	 * This method adds an element to the queue using offer(E) 
	 * @param c the object that should be added to the queue
	 * @return false if object is not suitable for the queue
	 */
	public boolean addCountry(Country c){
		return que.offer(c);
	}
	
	/**
	 * This method removes object if it is present
	 * @param c object to remove
	 * @return boolean indicating if operation was performed
	 */
	public boolean removeCountry(Country c){
		return que.remove(c);
	}
	
	
	/**
	 * This method will check if there is any elements at all in the queue
	 * by using general method peek() which returns null if empty
	 * @return int holding number of objects in queue
	 */
	public int getNoOfCountries(){
		
		Country temp = que.peek();
		return (temp == null)? 0: que.size();
	}
	
	/**
	 * This method will empty the queue by removing elements one by one
	 * using method poll() which removes objects from head of queue until
	 * there are no more elements to remove 
	 */
	public void emptyCollection(){
		Country temp;		
		while( (temp = que.poll()) != null){
			System.out.println("Object removed from que "+temp);
		}
	}
	
	public void printAllCountries(){
		// for:each loop used
		for(Country temp: que){
			System.out.println(temp);
		}
	}
	
	public boolean containsCountry(Country test){
		return que.contains(test);
	}

	public String getType() {
		return this.getClass().toString();
	}
}
