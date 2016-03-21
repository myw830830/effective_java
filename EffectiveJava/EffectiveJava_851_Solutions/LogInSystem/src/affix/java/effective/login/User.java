package affix.java.effective.login;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Comparator;

public class User implements Comparable<User>{

	/**
	 * Attribute holding unique user id
	 */
	private final String userId;
	/**
	 * Attribute holding a collection of previous passwords
	 */
	private Deque<String> pwds;	
	/**
	 * Constant defining number of entries in pwds
	 */
	private static final int MAX = 6;
	
	/**
	 * Enum defining the possible states for a User within the LogInSystem 
	 */
	enum LoggedInStatus {
		LOGGED_IN, LOGGED_OUT, BLOCKED
	}
	/**
	 * Attribute holding the current state of a User
	 */
	private LoggedInStatus loggedIn;
	
	/**
	 * Constant defining maximum attempts for password before he/she is BLOCKED
	 */
	private static final int MAX_TRIES = 3;		
	/**
	 * Attribute holding current number of attempts
	 */
	private int attempts = 0;
	
	/**
	 * Attribute defining the priority of this User
	 */
	private Priority prio;
	
	/**
	 * Constructor setting up a new User instance
	 * @param userName a unique id
	 * @param password a String of any characters
	 * @throws IllegalArgumentException if any of userName or passWord is empty
	 */
	public User(String userName, String password){
		if(userName.isEmpty()){
			throw new IllegalArgumentException("User name cannot be empty!");
		}
		if(password.isEmpty()){
			throw new IllegalArgumentException("Password cannot be empty!");
		}		
		userId=userName;
		pwds = new ArrayDeque<>();
		pwds.addFirst(password);
		prio = Priority.MEDIUM_PRIORITY;
		loggedIn = LoggedInStatus.LOGGED_OUT;
	}
	
	/**
	 * Getter for attribute userId
	 * @return a String holding userId
	 */
	public String getUserId(){
		return userId;
	}
	
	/**
	 * Getter of attribute loggedIn
	 * @return a boolean true if User is logged in
	 */
	public boolean isLoggedIn() {
		return loggedIn == LoggedInStatus.LOGGED_IN;
	}

	/**
	 * Package private method 
	 * @param status holds a boolean flag for loggedIn
	 */
	void setLoggedIn(boolean status){
		if(status == true){
			loggedIn = LoggedInStatus.LOGGED_IN;
		}
		else{
			loggedIn = LoggedInStatus.LOGGED_OUT;
		}
	}
	
	
	/**
	 * Additional method checking up if a user is Blocked
	 * @return a boolean true if user is in Blocked state
	 */
	public boolean isBlocked(){
		return loggedIn == LoggedInStatus.BLOCKED;
	}
	
	/**
	 * Getter of attribute prio
	 * @return an int holding current Priority level
	 */
	public int getPrio() {
		return prio.getPrioLevel();
	}

	/**
	 * Setter of attribute prio
	 * @param newPrio holding an int that can change Priority
	 */
	void setPrio(int newPrio){
		
		Priority[] prios = Priority.values();
		for(Priority p : prios){
			if(p.getPrioLevel() == newPrio){
				prio = p;
				break;
			}
		}
	}
	
	/**
	 * This method validates a login request
	 * @param userName a String holding user id to test
	 * @param password a String holding pwd to test for User
	 * @return boolean flag true if log in successful
	 * NB! If user already is logged in method returns false
	 */
	public boolean logIn(String userName, String password){

		boolean ok = false;
		// if user is BLOCKED skip all tests
		if(loggedIn != LoggedInStatus.BLOCKED){
			// check that user is currently LOGGED_OUT
			if(loggedIn == LoggedInStatus.LOGGED_OUT){
				// check user Id for this instance of User
				if(userName.equals(userId)){
					// check that entered password is current password
					String pwd = pwds.getFirst();
					if(password.equals(pwd)){
						ok = true;
						loggedIn = LoggedInStatus.LOGGED_IN;
						attempts = 0;
					}
					// incorrect password increases counter of attempts
					else{
						attempts++;
						if(attempts == MAX_TRIES){
							loggedIn = LoggedInStatus.BLOCKED;
							System.out.println("--> User is now BLOCKED!");
						}
					}
				}
			}
		}
		else{
			System.out.println("### User is BLOCKED ###");
		}
		return ok;
	}
	
	/**
	 * This method changes password for a logged in user
	 * The new password is tested against the last n passwords in order to be unique
	 * If passing test it will be added to the list of previous passwords
	 * @param password a String holding candidate for new password
	 * @return boolean flag true if new password is accepted
	 */
	boolean changePassword(String password){

		boolean ok = true;
		if(password.isEmpty() || password == null){
			ok = false;
		}
		
		if(isLoggedIn()){
			// check that password does not exist
			if(pwds.contains(password)){
				ok = false;
			}
			// add new password to list, rotate if needed
			if(ok){
				if(pwds.size() == MAX){
					pwds.removeLast();
				}
				pwds.addFirst(password);
			}
		}
		return ok;
	}
		

	/**
	 * Defensive copying!
	 * @return an array of Strings holding all passwords for a User
	 */
	String[] getPasswords(){
		String[] userPwds = new String[MAX];
		userPwds = pwds.toArray(userPwds);
		return userPwds;
	}

	@Override
	public int compareTo(User anotherUser) {		
		return userId.compareTo(anotherUser.getUserId());
	}
}



class UserComparator implements Comparator<User> {

	@Override
	public int compare(User u1, User u2) {
		
		int firstCriterion = u1.getPrio() - u2.getPrio();
		
		if(firstCriterion == 0){
			return u1.getUserId().compareTo(u2.getUserId());
		}
		else{
			return u1.getPrio() - u2.getPrio();
		}

	}

}