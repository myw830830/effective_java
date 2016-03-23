package affix.java.effective.login;

import java.util.ArrayDeque;
import java.util.Deque;

public class User implements Comparable<User>{

//	public static final int LOW_PRIORITY = 1;
//	public static final int MEDIUM_PRIORITY = 2;
//	public static final int HIGH_PRIORITY = 3;	
		
	private static final int MAX = 6;

	private final String userId;
//	private String[] pwds;	
	private Deque<String> pwds;	
	private boolean loggedIn;
	private Priority prio;
	
	/**
	 * Constructor setting up a new User instance
	 * @param userName a unique id
	 * @param password a String of any characters
	 * @throws IllegalArgumentException any of userName or passWord is empty
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
		loggedIn = false;
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
		return loggedIn;
	}

	/**
	 * Package private method 
	 * @param status holds a boolean flag for loggedIn
	 */
	void setLoggedIn(boolean status){
		loggedIn = status;
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
	 * @param p holds a Priority level
	 */
	void setPrio(int p){
		for (Priority temp : Priority.values()) {
			if (temp.getPrioLevel() == p) {
				prio = temp;
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
		if(userName.equals(userId)){
			String pwd = pwds.peekFirst();
			if(!loggedIn && password.equals(pwd)){
				ok = true;
				loggedIn = true;
			}
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
		
		if(loggedIn){
			// check that password does not exist in array
			if(pwds.contains(password)){
				ok = false;
			}
			else {// add new password to list, rotate if needed
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
		pwds.toArray(userPwds);
		return userPwds;
	}

	@Override
	public int compareTo(User anotherUser) {		
		return userId.compareTo(anotherUser.getUserId());
	}
	
}
