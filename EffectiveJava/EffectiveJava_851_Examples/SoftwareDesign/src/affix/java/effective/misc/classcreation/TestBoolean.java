package affix.java.effective.misc.classcreation;

public class TestBoolean {

	public static void main(String[] args){
		
		int a=12, b=15, c= 7;
		Boolean aObj,bObj, cObj;
	
		aObj = Boolean.valueOf(a > b);
		bObj = Boolean.valueOf(a == c);
		cObj = Boolean.valueOf(b > c);
		
		System.out.println(aObj.booleanValue() + " " + aObj.hashCode());
		System.out.println(bObj.booleanValue() + " " + bObj.hashCode());
		System.out.println(cObj.booleanValue() + " " + cObj.hashCode());
	}
}
