package affix.java.effective.login;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.*;

class LogInGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel wrapperPanel = new JPanel();
	private JPanel userPanel = new JPanel();
	private JLabel userJL = new JLabel("User", SwingConstants.CENTER);	
	private JTextField userJTF = new JTextField(10);
	private JLabel pwdJL = new JLabel("Password", SwingConstants.CENTER);	
	private JTextField pwdJTF = new JTextField(10);
		
	private JPanel logPanel = new JPanel();
	private JTextArea logPane = new JTextArea(10, 20);
	
	private JPanel controlPanel = new JPanel();
	private JPanel sysAdminPanel = new JPanel();
	private JButton addUserJB = new JButton("Add User");
	private JButton removeUserJB = new JButton("Remove User");	
	private JPanel logInPanel = new JPanel();
	private JButton logInJB = new JButton("LogIn");
	private JButton newPwdJB = new JButton("New Pwd");
	private JButton logOutJB = new JButton("LogOut");
	
	private LogInSystem theSystem;
	String lf = System.getProperty("line.separator");
	
	LogInGUI(LogInSystem system){
		
		theSystem = system;
		
		this.setLayout(new BorderLayout());

		// set up user panel
		userPanel.setLayout(new GridLayout(2,2));
		userPanel.add(userJL);
		userPanel.add(userJTF);
		userPanel.add(pwdJL);
		userPanel.add(pwdJTF);
		wrapperPanel.setLayout(new FlowLayout());
		wrapperPanel.add(userPanel);
		this.add(wrapperPanel, BorderLayout.WEST);
		
		
		// set up system panel
		logPanel.setLayout(new FlowLayout());
		logPane.setEditable(false);
		logPane.setFocusable(false);
		logPane.setBackground(Color.WHITE);
		JScrollPane jsp = new JScrollPane(logPane);
		jsp.setPreferredSize(logPane.getPreferredScrollableViewportSize());
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		logPanel.add(jsp);
		this.add(logPanel, BorderLayout.CENTER);
			
		// set up control panel
		controlPanel.setLayout(new FlowLayout());
		
		// set up logInPanel
		logInPanel.setLayout(new GridLayout(1, 3));
		logInPanel.setBorder(new TitledBorder("LogIn Panel"));
		// inner nested class referred to for user actions
		logInJB.addActionListener(new LogInActionListener());
		logInPanel.add(logInJB);
		
		newPwdJB.addActionListener(new UserActionListener());
		logInPanel.add(newPwdJB);
		
		logOutJB.addActionListener(new LogInActionListener());
		logInPanel.add(logOutJB);
		controlPanel.add(logInPanel);
		
		// set up sysAdminPanel
		sysAdminPanel.setLayout(new GridLayout(1, 2));
		sysAdminPanel.setBorder(new TitledBorder("SysAdmin Panel"));
		// anonymous nested class defined for SysAdmin actions
		addUserJB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				if(ae.getSource() == addUserJB){
					boolean ok = theSystem.addUser(userJTF.getText(), new User(userJTF.getText(), pwdJTF.getText()));
					if(ok){
						logPane.append(" +++ User " +userJTF.getText() + " added to system +++" + lf);
					}
				}
			}
		});		
		addUserJB.setEnabled(false);
		sysAdminPanel.add(addUserJB);
		// anonymous nested class defined for SysAdmin actions
		removeUserJB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				if(ae.getSource() == removeUserJB){
					boolean ok = theSystem.removeUser(userJTF.getText());
					if(ok){
						logPane.append(" --- User " +userJTF.getText() + " removed from system ---" + lf);
					}
				}	
			}
		});
		removeUserJB.setEnabled(false);
		sysAdminPanel.add(removeUserJB);		
		controlPanel.add(sysAdminPanel);

		this.add(controlPanel, BorderLayout.SOUTH);

		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	/**
	 * Inner class handling user GUI interactions
	 */
	class LogInActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent ae) {
			String lf = System.getProperty("line.separator");
			String userName = userJTF.getText();
			String password = pwdJTF.getText();
			
			JButton temp =(JButton)ae.getSource();
			if(temp == logInJB){
				if(userName.equals(theSystem.getAdminName())){
					if(validateLogIn(userName, password)){
						logPane.append(" *** SysAdmin logged in *** " + lf);
						addUserJB.setEnabled(true);
						removeUserJB.setEnabled(true);
					}
				}
				else{
					if(validateLogIn(userName, password)){
						logPane.append(userName + " logged in" + lf);
					}
				}
			}
			
			if(temp == logOutJB){
				
				if(userName.equals("SysAdmin")){
					if(validateLogOut(userName)){
						logPane.append(" *** SysAdmin logged out *** " + lf);
						addUserJB.setEnabled(false);
						removeUserJB.setEnabled(false);
					}
				}
				else{
					if(validateLogOut(userName)){
						logPane.append(userName + " logged out" + lf);
					}
				}
			}

		}
		
	}
	
	/**
	 * Inner class handling user activities while logged in
	 */	
	class UserActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {

			if(!userJTF.getText().equals("SysAdmin") && !pwdJTF.getText().isEmpty()){
				User currentUser = theSystem.findUser(userJTF.getText());
				// check up that user is logged in
				if(currentUser != null && currentUser.isLoggedIn()){
					boolean ok = true;
					
					// wait for new entry
					String testPwd = (String)JOptionPane.showInputDialog(LogInGUI.this, "Enter new password", "User update", 
							JOptionPane.PLAIN_MESSAGE, null, null, null);
					if(testPwd != null){
						ok = currentUser.changePassword(testPwd);
						if(!ok){
							JOptionPane.showMessageDialog(LogInGUI.this, "Invalid password", "User message", JOptionPane.ERROR_MESSAGE, null);
						}
						else{
							// empty text field holding previous password
							pwdJTF.setText("");
						}
					}
					
				}
			}
		}
		
	}
	
	/**
	 * This method checks userName and password before login is accepted
	 * @param userName String holding user id
	 * @param password String holding pwd to check i.e. current pwd
	 * @return true if login accepted false if it fails 
	 */
	private boolean validateLogIn(String userName, String password){
		boolean ok = false;

		ok = theSystem.logInUser(userName, password);
		if(ok){
			JOptionPane.showMessageDialog(
					LogInGUI.this, 
					"Logged In", 
					"Login message", 
					JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			JOptionPane.showMessageDialog(
					LogInGUI.this, 
					"Log In Failed", 
					"Login message", 
					JOptionPane.ERROR_MESSAGE);
		}
		return ok;
	}
		
	/**
	 * This method checks userName before logout is accepted
	 * @param userName String holding user id
	 * @return true if logout accepted false if it fails 
	 */
	private boolean validateLogOut(String userName){
		boolean ok = false;
		
		ok = theSystem.logOutUser(userName);
		if(ok){
			JOptionPane.showMessageDialog(
					LogInGUI.this, 
					"Logged Out", 
					"Logout message", 
					JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			JOptionPane.showMessageDialog(
					LogInGUI.this, 
					"Log Out Failed", 
					"Logout message", 
					JOptionPane.ERROR_MESSAGE);
		}
		return ok;
	}
	
}
