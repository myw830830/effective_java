package affix.java8.member;

public class HackerMember implements Member {
	
	private final String clubName;
	private String alias;
	private int points;
	
	public HackerMember(String clubName, String alias, int points){
		this.clubName = clubName;
		if(alias.isEmpty()){
			this.alias = Member.super.getMemberName();
		}		
		else{
			this.alias = alias;			
		}
		this.points = (Member.withinLimits(100, 1000, points))? points: 0;
	}
	
	@Override
	public String getClubName() {
		return clubName;
	}
	
	@Override
	public String getMemberName(){
		return alias;
	}
	
	public String getAlias(){
		return alias;
	}
	public void setAlias(String alias){
		this.alias = alias;
	}
	
	@Override
	public int getPoints(){
		return points;
	}
	public void setPoints(int points){
		this.points = points;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClubMember ");
		builder.append(clubName);
		builder.append(", ");
		builder.append(alias);
		builder.append(", ");
		builder.append(points);
		builder.append("p");
		return builder.toString();
	}

}
