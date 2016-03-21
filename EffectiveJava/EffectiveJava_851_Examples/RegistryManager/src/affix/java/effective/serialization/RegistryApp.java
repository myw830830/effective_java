package affix.java.effective.serialization;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import affix.java.effective.serialization.RegistryManager.MemberType;

/**
 * This class sets up and handles a Registry holding Member objects.
 * User interaction is possible via a menu that will be presented for console handling.
 */
public class RegistryApp {

	/**
	 * This enum provides a type safe support for a simple menu.
	 */
	private enum MenuChoice { 
		
		QUIT(0), HELP(1), 
		ADD_NEW_MEMBER(2), REMOVE_MEMBER(3),
		PRINT_MEMBERS(4),
		STORE_REGISTRY(5), READ_REGISTRY(6);
		//		ADD_NEW_VIP_MEMBER(9);
		
		/**
		 * Attribute holding specific menu number
		 */
		private int menuNo;
		
		/**
		 * @param x the value to connect to a MenuChoice instance
		 */
		MenuChoice(int x){
			menuNo = x;
		}
		
		/**
		 * Getter
		 * @return an int holding attribute menuNo
		 */
		public int getMenuNo(){
			return menuNo;
		}
		
		/**
		 * This is a lookup routine for MenuChoice values based on attribute menuNo.
		 * It is declared as static since it is doing a somewhat backward logic task 
		 * @param input holds the value to look for
		 * @return MenuChoice holding matching enum value
		 */
		public static MenuChoice getMenuChoice(int input){
			MenuChoice val = HELP;
			for(MenuChoice mc : MenuChoice.values()){
				if(mc.getMenuNo() == input){
					val = mc;
				}
			}
			return val;
		}
	};

	/**
	 * Attribute used by test routine, could be a singleton
	 */
	private static RegistryManager manager;
	

	/**
	 * Constructor setting up the system
	 * If there is a serialized file available this will be read and configure the system
	 */
	public RegistryApp(){	
		manager = RegistrySupport.readMgr();
		if(manager == null){
			manager = new RegistryManager();
		}
	}
	
	/**
	 * Print menu by iterating over an enum
	 */
	public void printMenu(){		
		for(MenuChoice mc : MenuChoice.values()){
			System.out.println(mc.getMenuNo() + " = " + mc);
		}
	}
	

	/**
	 * User interface (console version)
	 */
	public static void main(String[] args) {

		RegistryApp t = new RegistryApp();
		MenuChoice choice = MenuChoice.HELP;

		Scanner in = new Scanner(System.in);
		in.useDelimiter(System.getProperty("line.separator"));
		
		try{
			while(choice != MenuChoice.QUIT){

				try{
					t.printMenu();
					choice = MenuChoice.getMenuChoice(in.nextInt());
					
					switch(choice){
					case QUIT:
						System.out.println("Shutting down ");
						break;
					case HELP:
						t.printMenu();
						break;
					case ADD_NEW_MEMBER:						
						String[] input=getNewMember(in);
						manager.addMember(input[0], input[1], input[2], MemberType.NORMAL);
						break;						
					case REMOVE_MEMBER:
						System.out.print("Enter member no: ");
						manager.removeMember(in.nextInt());
						break;
					case PRINT_MEMBERS:
						manager.printMembers();
						break;
					case STORE_REGISTRY:
						RegistrySupport.storeMgr(manager);
						break;
					case READ_REGISTRY:
						RegistryManager temp = RegistrySupport.readMgr();
						if( temp != null){
							manager = temp;		
						}
						break;	
//					case ADD_NEW_VIP_MEMBER:						
//						String[] vipInput=getNewMember(in);						
//						manager.addMember(vipInput[0], vipInput[1], vipInput[2], MemberType.VIP);
//						break;	
					default:
						System.out.println("Wrong menuchoice !");
					}
				}
				catch(InputMismatchException ime){
					// consume pending input
					in.next();
				}
			}
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}

	}

	/**
	 * Helper method interacting with user
	 * @param in a Scanner instance for keyboard input
	 * @return a String array holding user input
	 * @throws IOException
	 */
	private static String[] getNewMember(Scanner in) throws IOException{
		String[] input = new String[3];
		System.out.print("\n First name: ");
		input[0]=in.next();
		System.out.print("\n Last name: ");
		input[1]=in.next();
		System.out.print("\n Email: ");
		input[2]=in.next();

		return input;
	}

}