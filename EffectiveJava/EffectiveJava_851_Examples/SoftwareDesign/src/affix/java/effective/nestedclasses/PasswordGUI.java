package affix.java.effective.nestedclasses;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Inherited class specializing general class JFrame
public class PasswordGUI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel outputPanel = new JPanel();
	private JLabel userJL = new JLabel("User: ", SwingConstants.CENTER);
	private JTextField userJTF = new JTextField(15);	
	private JLabel pwdJL = new JLabel("Password: ", SwingConstants.CENTER);
	private JTextField pwdJTF = new JTextField(15);	
		
	private Box buttonBar;
	private JButton testJB = new JButton("Test");
	private JButton clearJB = new JButton("Clear");
	private JButton exitJB = new JButton("Exit");
	
	private String thePwd;
	
	public PasswordGUI(String pwd){
		
		thePwd = pwd;
		this.setLayout(new BorderLayout());
		
		outputPanel.setLayout(new GridLayout(2, 2));
		outputPanel.add(userJL);
		outputPanel.add(userJTF);
		outputPanel.add(pwdJL);
		outputPanel.add(pwdJTF);
		
		
		this.add(outputPanel, BorderLayout.CENTER);
		
		buttonBar = Box.createHorizontalBox();
		buttonBar.add(Box.createHorizontalGlue());		
		
		// delegate action event response to inner class
		testJB.addActionListener(new TestActionListener());
		buttonBar.add(testJB);
		
		
		// anonymous class defining action event response
		clearJB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				pwdJTF.setText("");
				pwdJTF.setBackground(Color.WHITE);
			}
		});
		buttonBar.add(clearJB);
		
		// anonymous class defining action event response
		exitJB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				System.exit(0);
			}
		});
		buttonBar.add(exitJB);
	
		this.add(buttonBar, BorderLayout.SOUTH);
	
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	/**
	 * Inner class handling user interaction via button "Test"
	 */
	class TestActionListener implements ActionListener{

		public void actionPerformed(ActionEvent ae){
			String input = pwdJTF.getText();
			if(input.equals(thePwd)){
				pwdJTF.setBackground(Color.GREEN);
			}
			else{
				pwdJTF.setBackground(Color.RED);
			}
		}
	}	
	
}
