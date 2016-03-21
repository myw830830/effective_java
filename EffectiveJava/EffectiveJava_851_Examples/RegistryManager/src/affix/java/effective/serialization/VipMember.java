package affix.java.effective.serialization;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;


/**
 * This class takes care of serialization process on its own
 * Requires that there is an available default constructor in super class
 *
 */
public class VipMember extends Member implements Externalizable{

	/**
	 * Version number 
	 */
	private static final long serialVersionUID = 1L;

	private int points;
	private static int vipMemberCounter = 0;
	
	/**
	 * NB! There must be a public default constructor for classes implementing Externalizable
	 */
	public VipMember() {}
	
	VipMember(String f, String l, String e, int cId) {
		super(f, l, e, cId);
		// VipMembers have a number starting with 9
		int vipNo = Integer.parseInt("9"+String.valueOf(super.getMemberNo()));
		super.setMemberNo(vipNo);
		
		points = 1000;
		vipMemberCounter++;
	}
	
	/**
	 * @param points the points to set
	 */
	void setPoints(int points) {
		this.points = points;
	}
	
	/**
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}

	static void decreaseVipMemberCounter(){
		vipMemberCounter--;
	}
	public static int getVipMemberCounter(){
		return vipMemberCounter;
	}
	
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		
		// first write super class attributes	
		out.writeObject(super.getFirstName());
		out.writeObject(super.getLastName());
		out.writeObject(super.getEmail());
		out.writeInt(super.getMemberNo());
		
		// second write local attributes
		out.writeInt(points);
		
		// static attribute must be recreated when reading
		out.writeInt(vipMemberCounter);
		
		System.out.println("Debug: passed writeExternal(VipMember)");			
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
				
		// first read super class attributes	
		super.setFirstName((String)in.readObject());
		super.setLastName((String)in.readObject());
		super.setEmail((String)in.readObject());
		super.setMemberNo(in.readInt());
		
		// second read local attributes
		points = in.readInt();
		
		// static attribute must be recreated when reading
		vipMemberCounter = in.readInt();
		
		System.out.println("Debug: passed readExternal(VipMember)");	
	}

	public String toString(){
		
		StringBuilder sb=new StringBuilder(super.toString());		
		sb.append(", ");
		sb.append(points);
		sb.append(" p");
		
		return sb.toString();
	}
	
}
