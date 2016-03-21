package affix.java.effective.track;

import affix.java.effective.time.Time;


class SteepleChaseCompetitor{

	private final String name;
	private TrackResult result;
	

	SteepleChaseCompetitor(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}	

	TrackResult doRun(){
		int h = 0;
		int m = (int)(Math.random()*2)+7;
		int s = (int)(Math.random()*30)+30;
		int d = (int)(Math.random()*100);
		Time value = new Time(h, m, s, d);
		
		result = new TrackResult(value, name);
		return result;
	}
	
	public TrackResult getResult(){
		return result;
	}
	
	public String toString(){
		String lf = System.getProperty("line.separator");
		
		StringBuilder sb = new StringBuilder();
		sb.append("SteepleChaseCompetitor: ");
		sb.append(name);
		sb.append(lf);
		sb.append(result);
		
		return sb.toString();
	}

}
