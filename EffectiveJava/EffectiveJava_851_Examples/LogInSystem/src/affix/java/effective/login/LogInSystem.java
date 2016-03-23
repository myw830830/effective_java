package affix.java.effective.login;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * This class handles the administration of a user login system
 * There should only be one instance of this class so this is a singleton.
 * The SysAdmin User should be able to login/logout in order to handle all User accounts that are part of the system.
 * All User objects are stored in some storage that is maintained by various methods under SysAdmin control.
 * Any changes to this storage can only be done by a logged in SysAdmin.
 */
class LogInSystem {
	
	private final User admin;
//	private List<User> userList;
	private Map<String, User> userMap;
	
	private static final LogInSystem SYSTEM = new LogInSystem();

	/**
	 * Private constructor will prohibit uncontrolled access to this singleton class
	 */
	private LogInSystem(){
		userMap = new HashMap<>();
		admin = new User("SysAdmin", "ImTheBoss");
		System.out.println(" *** SysAdmin Log: SysAdmin singleton instance created *** ");
	}

	// Singleton
	static LogInSystem getInstance(){
		return SYSTEM;
	}


	/**
	 * This method adds a new user to the collection of registered users
	 * after a check if the user name is not occupied
	 * 
	 * @param userName holds a String of proposed user id
	 * @param newUser holds an instance of a User
	 * @return boolean flag if new user was added or not
	 */
	boolean addUser(String userName, User newUser){

		boolean stored = false;

		if(!userName.equals("SysAdmin")){
			// check if admin is logged in
			if(admin.isLoggedIn() && userMap.putIfAbsent(userName, newUser) == null){
				stored = true;
				System.out.println(" +++ SysAdmin Log: User " + userName + " added to system +++ ");
			}
		}
		return stored;
	}

	/**
	 * This method will remove an existing user from the storage
	 * @param userName holds the entry to remove
	 * @return boolean true if user was successfully removed
	 */
	boolean removeUser(String userName){

		boolean removed = false;

		// check if admin is logged in
		if(admin.isLoggedIn()){
			// look for specific User
			if(userMap.containsKey(userName)) {
				if(userMap.get(userName).isLoggedIn()){
					logOutUser(userName);
				}
				userMap.remove(userName);				
				removed = true;
				System.out.println(" --- SysAdmin Log: User " + userName + " removed from system --- ");	
			}
		}

		return removed;	
	}
	
	User findUser(String userName){
		return userMap.get(userName);		
	}


	/**
	 * This method validates login of a user
	 * @param userName to look for in storage
	 * @param password holds the pwd to validate 
	 * @return boolean true if login of user is successful
	 */
	boolean logInUser(String userName, String password){
		boolean ok = false;

		if(userName.equals("SysAdmin")){
			ok = logInAdmin(password);
		}
		else{
			User u = userMap.get(userName);
			if(u != null && u.logIn(userName, password)){
				ok=true;
				System.out.println(" *** System Log: User " + userName + " logged in *** ");
			}
		}
		return ok;
	}

	/**
	 * This method takes care of logging out a user
	 * @param userName key to look for in storage
	 * @return boolean true if logout of user is successful
	 */
	boolean logOutUser(String userName){
		boolean ok=false;

		if(userName.equals("SysAdmin")){
			ok = logOutAdmin();
		}
		else{
			User u = userMap.get(userName);
			if(u != null && u.isLoggedIn()){
				u.setLoggedIn(false);
				ok = true;
				System.out.println(" *** System Log: User " + userName + " logged out *** ");			
			}
		}
		return ok;
	}	

	
	/**
	 * @return int holding number of current User objects
	 */
	int getNoOfUsers() {
		return userMap.size();
	}


	/**
	 * Cleaning up the internal array
	 */
	void removeAllUsers(){
		userMap.clear();
	}


	String getAdminName(){
		return admin.getUserId();
	}

	/**
	 * This method validates login of SysAdmin
	 * @param pwdAdmin holds the password to test
	 * @return boolean true if login was successful
	 */
	private boolean logInAdmin(String password){
		boolean ok=false;
		if(ok = admin.logIn(getAdminName(), password)){
			admin.setLoggedIn(true);
			System.out.println(" ***************************************** ");			
			System.out.println(" ***  System Log: SysAdmin logged in   *** ");
			System.out.println(" ***************************************** ");			
		}
		return ok;
	}

	/**
	 * This method logs out SysAdmin 
	 */
	private boolean logOutAdmin(){
		boolean ok = false;
		if(ok = admin.isLoggedIn()){
			admin.setLoggedIn(false);
			System.out.println(" ***************************************** ");			
			System.out.println(" ***  System Log: SysAdmin logged out  *** ");			
			System.out.println(" ***************************************** ");			
		}
		return ok;
	}

	/**
	 * Defensive copying of list of user objects
	 * @return List<User> holding all User objects currently registered with the LogInSystem
	 */
	List<User> getUsers() {
		return Collections.unmodifiableList(new ArrayList<>(userMap.values()));
	}
	
}