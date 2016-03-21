package affix.java.effective.login;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class LogInSystemTest {

	private LogInSystem theSystem;
	
	@Before
	public void setUp() throws Exception {
		theSystem = LogInSystem.getInstance();
		theSystem.logInUser("SysAdmin", "ImTheBoss");
		theSystem.addUser("Dummy", new User("Dummy", "qwerty"));
	}

	@After
	public void tearDown() throws Exception{
		theSystem.removeAllUsers();
		theSystem.logOutUser("SysAdmin");
		theSystem = null;
	}
	
	@Test
	public void testRemoveUser(){
		String userName = "Dummy";
		assertTrue(theSystem.removeUser(userName));
	}

	@Test
	public void testAddExistingUser(){
		String userName = "Dummy";
		User u = new User("Dummy", "LetMeIn");
		
		assertFalse(theSystem.addUser(userName, u));
	}
	
	@Test
	public void testAddNewUser(){
		String userName = "Kalle";
		User u = new User("Kalle", "LetMeIn");
		
		assertTrue(theSystem.addUser(userName, u));
	}

	@Test
	public void testAddManyUsers(){
		
		String userName = "Kalle";
		User u = new User("Kalle", "LetMeIn");
		theSystem.addUser(userName, u);
		
		userName = "Hobbe";
		u = new User("Hobbe", "StayPut");
		theSystem.addUser(userName, u);
		
		userName = "Nisse";
		u = new User("Nisse", "asd78s2SD");
		theSystem.addUser(userName, u);
		
		userName = "Emma";
		u = new User("Emma", "Unbreakable");
		theSystem.addUser(userName, u);
		
		userName = "Olle";
		u = new User("Olle", "ellO");
		theSystem.addUser(userName, u);
		
		assertEquals(6, theSystem.getNoOfUsers());
	}

	@Test
	public void testLoginUser(){
		String userName = "Dummy";
		boolean ok = theSystem.logInUser(userName, "qwerty");
		
		assertTrue(ok);
	}
	
	@Test
	public void testNaturalOrder(){
		
		String userName = "Kalle";
		User u = new User("Kalle", "LetMeIn");
		theSystem.addUser(userName, u);
		
		userName = "Hobbe";
		u = new User("Hobbe", "StayPut");
		theSystem.addUser(userName, u);
		
		userName = "Nisse";
		u = new User("Nisse", "asd78s2SD");
		theSystem.addUser(userName, u);
		
		// defensive copy returned, must be handled by separate instance
		List<User> tempList = new ArrayList<>(theSystem.getUsers());
		Collections.sort(tempList);
		
		assertTrue(tempList.get(1).getUserId().equals("Hobbe"));
		assertTrue(tempList.get(2).getUserId().equals("Kalle"));
		assertTrue(tempList.get(3).getUserId().equals("Nisse"));
	}
	
}
