package affix.java.effective.nestedclasses;

import javax.swing.SwingUtilities;

public class PasswordApp {

	public static void main(String[] args) {
		
		Runnable pwdRunner = new Runnable(){
			public void run(){
				PasswordGUI pg = new PasswordGUI("ABC123");
				pg.setTitle("PasswordGUI");
				pg.pack();
				pg.setVisible(true);
			}
		};
		SwingUtilities.invokeLater(pwdRunner);

	}

}
