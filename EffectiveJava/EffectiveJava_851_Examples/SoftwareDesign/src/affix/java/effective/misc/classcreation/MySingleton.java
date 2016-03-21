package affix.java.effective.misc.classcreation;

public class MySingleton {

	private static final MySingleton INSTANCE  = new MySingleton();
	private final String id;
	
	private MySingleton(){
		id = "I'm the only one!";
	}
	
	public static MySingleton getInstance(){
		return INSTANCE;
	}
	
	public String toString(){
		return id;
	}
	
	public static void main(String[] args) {

		MySingleton s1 = MySingleton.getInstance();
		System.out.println(s1);
		
		MySingleton s2 = MySingleton.getInstance();
		System.out.println(s2);
		
		System.out.println("Are s1 and s2 a Singleton? "+(s1==s2));

	}

}
