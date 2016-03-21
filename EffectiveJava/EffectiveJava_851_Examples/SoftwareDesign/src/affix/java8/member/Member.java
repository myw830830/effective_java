package affix.java8.member;

public interface Member {
	
	String getClubName();
	
	default String getMemberName(){
		return "Guest";
	}

	static boolean withinLimits(int min, int max, int value){
		return (value >= min && value <= max)? true: false;
	}

	int getPoints();
}
