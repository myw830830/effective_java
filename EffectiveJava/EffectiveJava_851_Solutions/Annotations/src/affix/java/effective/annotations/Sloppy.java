package affix.java.effective.annotations;

public class Sloppy {

	private int x;
	private String msg;
	
	@BugFix(bugNumbers={})
	public Sloppy(){
		msg = "Dummy";
	}
	
	@BugFix(bugNumbers = 12345, project = "Prototype")
	public int calcX(){
		x = (int)msg.charAt(x);
		return x;
	}
	
	@BugFix(bugNumbers = {12345, 12346, 12348})
	public void printOut(){
		for(int i=0; i<msg.length(); i++){
			System.out.println(calcX());
		}
	}
	
	public static void main(String[] args){
		Sloppy s = new Sloppy();
		s.printOut();
	}
}
