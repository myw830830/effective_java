package affix.java.effective.track;

import affix.java.effective.time.Time;
import affix.java.effective.trackandfield.AbstractCompetitor;

public class SteepleChaseCompetitor extends AbstractCompetitor{

	private TrackResult result;

	private SteepleChaseCompetitor(String name) {
		super(name);
	}

	static SteepleChaseCompetitor createSteepleChaseCompetitor(String name){
		// checking of competition restrictions for any names goes here
		// might throw an exception or return null
		return new SteepleChaseCompetitor(name);
	}

	/**
	 * @return the result
	 */
	TrackResult getResult() {
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SteepleChaseCompetitor [name=");
		builder.append(getName());
		builder.append(", result=");
		builder.append(result);
		builder.append("]");
		return builder.toString();
	}
	
	TrackResult doRun(){
		int m = 7 + (int)(Math.random()*2);
		int s = 0 + (int)(Math.random()*30);
		int d = 0 + (int)(Math.random()*100);
		Time temp = new Time(0, m, s, d);
		
		result = new TrackResult(temp, getName());
		
		return result;
	}
	
}
