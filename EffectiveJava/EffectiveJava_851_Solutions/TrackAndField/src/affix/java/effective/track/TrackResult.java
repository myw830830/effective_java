package affix.java.effective.track;

import affix.java.effective.time.*;

public final class TrackResult implements Comparable<TrackResult> {

	private final Time result;
	private final String id;
	
	public TrackResult(Time time, String id){
		this.result = time;
		this.id = id;
	}
	
	public Time getResult() {
		return result;
	}
	
	public String getId() {
		return id;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();		
		sb.append("Competitor: ");
		sb.append(id);
		sb.append(" ");
		sb.append(result);
		
		return sb.toString();
	}
	
	@Override
	public int compareTo(TrackResult obj) {
		return this.result.compareTo(obj.result);
	}
}
