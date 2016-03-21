package affix.java.effective.serialization;

import java.io.*;
import java.util.*;

/**
 * This class supervises a customer register.
 * Methods for storing, accessing and removing info are available
 * A Map is used for storage of Members based on a unique id.
 */
public class RegistryManager implements Serializable{

	// manually defined version number
	private static final long serialVersionUID = 1L;

	// collection class to be serialized
	private Map<Integer, Member> memberMap;

	// common attribute exclude from default serialization
	private static int uniqueId = 1001;
	
	enum MemberType {NORMAL, VIP}
	
	/**
	 * Constructor
	 */
	public RegistryManager(){
		memberMap=new TreeMap<Integer, Member> ();
		System.out.println("RegistryManager created");
	}

	/**
	 * Delegate method for Map put()
	 * @param input a String array holding user input
	 */
	public void addMember(String f, String l, String e, MemberType type){
		Member member = null;
		
		switch(type){
		case NORMAL:
			member = new Member(f, l, e, uniqueId++);
			break;
		case VIP:
			member = new VipMember(f, l, e, uniqueId++);
			break;
		}
		memberMap.put(member.getMemberNo(), member);
	}
	
	/**
	 * Method that removes an entry from the register if it is present.
	 * @param x holds the unique id of the Member in the register.
	 */
	public void removeMember(int x){
		Member temp = memberMap.remove(x);
		if(temp instanceof VipMember){
			VipMember.decreaseVipMemberCounter();
		}
	}

	/**
	 * Output method using Member id as sort criterion.
	 */
	public void printMembers(){
//		System.out.format("There are %d VipCustomer(s) in the registry %n", VipMember.getVipMemberCounter());
		Set<Integer> keys = memberMap.keySet();
		for(Integer key : keys){
			System.out.println("Member "+ memberMap.get(key));
		}	  
	}
	
	/**
	 * Overriding default implementation in order to handle
	 * serialization of static attribute uniqueId
	 */
	private void writeObject(ObjectOutputStream oos){

		try {
			oos.defaultWriteObject();
			oos.writeInt(uniqueId);			
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Debug: passed writeObject(RegistryManager)");		
	}
	
	/**
	 * Overriding default implementation in order to handle
	 * serialization of static attribute uniqueId
	 */
	private void readObject(ObjectInputStream ois){

		try {
			ois.defaultReadObject();
			uniqueId = ois.readInt();			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Debug: passed readObject(RegistryManager)");
	}
	
}
