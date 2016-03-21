package affix.java.effective.trackandfield;

public class ResultBoard {

	private static ResultBoard SINGLETON = new ResultBoard();
	
	private ResultBoard() {;}
	
	public static ResultBoard getInstance(){
		return SINGLETON;
	}
	
	public void printResults(String competitionName, AbstractResult[] results){
		System.out.println("---------------------------------------------------- ");
		System.out.println("ResultBoard presents: ");
		System.out.println("Results from " + competitionName);
		
		for(int i=0; i<results.length; i++){
			System.out.println(results[i]);
		}	
		System.out.println("---------------------------------------------------- ");
	}

}
