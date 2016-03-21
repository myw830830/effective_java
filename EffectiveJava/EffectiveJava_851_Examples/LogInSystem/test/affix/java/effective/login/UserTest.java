package affix.java.effective.login;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class UserTest {
	
	private User testUser;
	
	@Before
	public void setUp() throws Exception {
		testUser = new User("Kalle", "LetMeIn");
	}

	@Test
	public void testUserCreation() {
		User myUser = new User("Dummy", "Argh");
		assertNotNull(myUser);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testUserNoName(){
		@SuppressWarnings("unused")
		User myUser = new User("", "dummy");
	}

	@Test (expected = IllegalArgumentException.class)
	public void testUserNoPassword(){
		@SuppressWarnings("unused")
		User myUser = new User("dummy", "");
	}
	
	@Test
	public void testGetUserId() {
		assertEquals("Kalle", testUser.getUserId());
	}

	@Test
	public void testLogIn() {
		String userId = "Kalle";
		String pwd = "LetMeIn";
		assertTrue(testUser.logIn(userId, pwd));
	}

	@Test
	public void testNewPassword() {
		testUser.logIn("Kalle", "LetMeIn");
		testUser.changePassword("qwerty");
		assertEquals("qwerty", testUser.getPasswords()[0]);
	}

	@Test
	public void testGetPasswords() {
		String[] pwdArray = {"abc", "def", "ghi", "jkl", "mno", "pqr"};
		testUser.logIn("Kalle", "LetMeIn");
		
		for(int i = 0; i<pwdArray.length; i++){
			testUser.changePassword(pwdArray[i]);
		}
		
		String[] pwds = testUser.getPasswords();
		for(int i=0, x = 5; i<pwds.length; i++, x--){
			assertEquals(pwdArray[x], pwds[i]);
		}
	}
	
	@Test
	public void testAddNewPassword(){		
		testUser.logIn("Kalle", "LetMeIn");
		testUser.changePassword("qwerty2");
		testUser.changePassword("qwerty3");
		testUser.changePassword("qwerty2"); // copy
		testUser.changePassword("qwerty4");
		testUser.changePassword("qwerty5");	
		
		String lastPwd = testUser.getPasswords()[5];
			
		assertNull(lastPwd);		
	}
	
	@Ignore
	@Test
	public void testLogIn4Times() {
		String userId = "Kalle";
		String pwd = "LetMeIn";
		for(int i=0; i<3; i++){
			testUser.logIn(userId, "qwerty");
		}
		assertFalse(testUser.logIn(userId, pwd));
	}
	
	@Test // confirm bug, incorrect handling of double login
	public void testDoubleLogIn() {
		String userId = "Kalle";
		String pwd = "LetMeIn";
		
		testUser.logIn(userId, pwd);
		testUser.logIn(userId, pwd);
		testUser.setLoggedIn(false);

		testUser.logIn(userId, "qwerty");
		testUser.logIn(userId, "qwerty");
		
		assertTrue(testUser.logIn(userId, pwd));
	}
	
	@Test
	public void testGetInitialPriority(){
		int prioLevel = testUser.getPrio();
		assertEquals(2, prioLevel);
	}
	
	@Test
	public void testChangePriority(){
		testUser.setPrio(3);
		int prioLevel = testUser.getPrio();
		assertEquals(3, prioLevel);
	}
	
	@Test
	public void testComparePriority(){
		User xtraUser = new User("Danny", "ewtu422¤");
		int prioLevel1 = xtraUser.getPrio();
		testUser.setPrio(3);
		int prioLevel2 = testUser.getPrio();
		assertTrue(prioLevel2 > prioLevel1);
	}
	

}
