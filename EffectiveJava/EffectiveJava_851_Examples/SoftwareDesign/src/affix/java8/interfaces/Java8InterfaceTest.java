package affix.java8.interfaces;


interface Java8Interface{
	
	static String myStaticMethod() {
		return "I'm static!";
	}
	
	abstract String myNormalMethod();
	
	default String myDefaultMethod(){
		return "I'm default, but can be overridden!";
	}
}


public class Java8InterfaceTest implements Java8Interface{
	
	@Override
	public String myDefaultMethod(){
		return "default method now overridden ";
	}
	
	@Override
	public String myNormalMethod() {
		return "I'm normal!";
	}
	
	public static void main(String[] args){
		
		Java8InterfaceTest test = new Java8InterfaceTest();
		
		System.out.println(test.myDefaultMethod());
	
		System.out.println(test.myNormalMethod());
		
		System.out.println(Java8Interface.myStaticMethod());
	}
	 
}

