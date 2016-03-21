package affix.java.effective.track;

import java.util.EnumMap;
import java.util.Map;

import affix.java.effective.time.Time;

public class TrackUtils {

	/**
	 * This type defines all valid single track competitions 
	 */
	public enum TrackCompetitionType {M100, M200, M400, M800, M1500, M5000, M10000, M110_HURDLES, M400_HURDLES, STEEPLE_CHASE};


	private static Map<TrackCompetitionType, TrackResult> worldRecords 
					= new EnumMap<TrackCompetitionType, TrackResult>(TrackCompetitionType.class);

	// This info should be read from file or db at start up
	static {
		worldRecords.put(TrackCompetitionType.STEEPLE_CHASE, new TrackResult(new Time(0, 7, 53, 10), "Moses Kiptanui"));
	}										

	private TrackUtils() {;}
	
	/**
	 * This methods supplies entrance to collection of WorldRecords for specific track competitions.
	 * @param competition a TrackCompetitionType acting as key for lookup
	 * @return TrackResult holding the current WorldRecord
	 */
	public static TrackResult getWorldRecord(TrackCompetitionType competition){
		return worldRecords.get(competition);
	}

	/**
	 * This method compares a competitors performance to current world record
	 * @param winningResult the TrackResult to examine
	 * @param competition specific type of track competition
	 * @return boolean true if current world record is beaten
	 */
	public static boolean compareToWorldRecord(TrackResult winningResult, TrackCompetitionType competition){

		boolean status = false;
		TrackResult worldRecord = getWorldRecord(competition);	

		if(winningResult.compareTo(worldRecord) < 0){
			status = true;
		}
		return status;
	}
}
