package affix.java.effective.track;

import affix.java.effective.time.*;
import affix.java.effective.trackandfield.AbstractResult;

public final class TrackResult extends AbstractResult implements Comparable<TrackResult> {
	
	public TrackResult(Time result, String id) {
		super(result, id);
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TrackResult [id=");
		builder.append(getId());
		builder.append(", result=");
		builder.append(getResult());
		builder.append("]");
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(TrackResult that) {		
		Time thisTime = (Time)getResult();
		return thisTime.compareTo((Time)that.getResult());
	}	
	
}
