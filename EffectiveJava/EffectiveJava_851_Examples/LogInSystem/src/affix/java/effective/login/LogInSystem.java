package affix.java.effective.login;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;



/**
 * This class handles the administration of a user login system
 * There should only be one instance of this class so this is a singleton.
 * The SysAdmin User should be able to login/logout in order to handle all User accounts that are part of the system.
 * All User objects are stored in some storage that is maintained by various methods under SysAdmin control.
 * Any changes to this storage can only be done by a logged in SysAdmin.
 */
class LogInSystem {
	
	private final User admin;
	private List<User> userList;

	private static final LogInSystem SYSTEM = new LogInSystem();

	/**
	 * Private constructor will prohibit uncontrolled access to this singleton class
	 */
	private LogInSystem(){
		userList = new ArrayList<>();
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
			if(admin.isLoggedIn()){
				// check if User is not already stored
				if(findUser(userName) == null){
					// find first free index for new User
					userList.add(newUser);
					stored = true;
					System.out.println(" +++ SysAdmin Log: User " + userName + " added to system +++ ");
				}
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
			for(int i=0; i<userList.size(); i++){
				User temp = userList.get(i);
				if(temp.getUserId().equals(userName)){
					if(temp.isLoggedIn()){
						logOutUser(temp.getUserId());
					}
					userList.remove(temp);				
					removed = true;
					System.out.println(" --- SysAdmin Log: User " + userName + " removed from system --- ");	
					break;
				}
			}
		}

		return removed;	
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
			User u = findUser(userName);
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
			User u = findUser(userName);
			if(u != null && u.isLoggedIn()){
				u.setLoggedIn(false);
				ok = true;
				System.out.println(" *** System Log: User " + userName + " logged out *** ");			
			}
		}
		return ok;
	}	

	/**
	 * This is a convenience method that will look through the array of users in order
	 * to find a specific user
	 * @param userName the name of a user to find
	 * @return User object mapped to userName or null if not found
	 */
	User findUser(String userName){
		User u = null;
		boolean found = false;
		
		Iterator<User> iter = userList.iterator();
		while(iter.hasNext() && !found){
			User temp = iter.next();
			String tempName = temp.getUserId();
			if(tempName.equals(userName)){
				u = temp;
				found = true;
			}
		}
		return u;
	}

	/**
	 * @return int holding number of current User objects
	 */
	int getNoOfUsers() {
		return userList.size();
	}


	/**
	 * Cleaning up the internal array
	 */
	void removeAllUsers(){
		userList.clear();
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
		return Collections.unmodifiableList(userList);
	}
	
}