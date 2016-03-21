package affix.java.effective.login;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class LogInGUIApp {

	public static void main(String[] args){
	
		
		final LogInSystem theSystem = LogInSystem.getInstance();
		theSystem.logInUser("SysAdmin", "ImTheBoss");
		theSystem.addUser("Kalle", new User("Kalle", "LetMeIn"));
		theSystem.addUser("Olle", new User("Olle", "ellO"));
		theSystem.addUser("Emma", new User("Emma", "sly34Xa#"));
		theSystem.logOutUser("SysAdmin");
		
		Runnable guiRunner = new Runnable(){
			
			public void run(){
				JFrame jf = new LogInGUI(theSystem);
				jf.setTitle("LogInSystem");
				jf.pack();
				jf.setResizable(false);
				jf.setVisible(true);
			}
		};
		SwingUtilities.invokeLater(guiRunner);
	}
}
