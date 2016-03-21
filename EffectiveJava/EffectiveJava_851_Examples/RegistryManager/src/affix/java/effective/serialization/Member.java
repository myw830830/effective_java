package affix.java.effective.serialization;

import java.io.*;

public class Member implements Serializable{

	private static final long serialVersionUID = 1L;

	private int memberNo;
	private String firstName;
	private String lastName;
	private String email;


	// supporting any future subclass requiring a default constructor
	protected Member() {}
	
	Member(String f, String l, String e, int cId){
		firstName=f;
		lastName=l;
		email=e;
		memberNo=cId;
	}

	// setters and getters
	void setMemberNo(int no){
		memberNo = no;
	}
	public int getMemberNo() {
		return memberNo;
	}

	void setFirstName(String f){
		firstName = f;
	}
	public String getFirstName(){
		return firstName;
	}

	void setLastName(String l){
		lastName = l;
	}
	public String getLastName(){
		return lastName;
	}

	void setEmail(String e){
		email = e;
	}
	public String getEmail(){
		return email;
	}

	public String toString(){
		
		StringBuilder sb=new StringBuilder();
		sb.append(memberNo);
		sb.append(", ");
		sb.append(firstName);
		sb.append(" ");
		sb.append(lastName);
		sb.append(", ");
		sb.append(email);
		
		return sb.toString();
	}
}