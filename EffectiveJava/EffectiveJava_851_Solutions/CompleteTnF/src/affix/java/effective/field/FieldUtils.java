package affix.java.effective.field;

import java.util.EnumMap;
import java.util.Map;
import affix.java.effective.uslength.USLength;

/**
 * This class is used as a utility class for various FieldCompetition classes. 
 */
public class FieldUtils {

	/**
	 * This type defines all valid single field competitions 
	 */
	public enum FieldCompetitionType {LONG_JUMP, TRIPLE_JUMP, HIGH_JUMP, POLE_VAULT, DISCUS_THROW, HAMMER_THROW, JAVELIN_THROW, SHOT_PUT}

	private static Map<FieldCompetitionType, FieldResult> worldRecords 
			= new EnumMap<FieldCompetitionType, FieldResult>(FieldCompetitionType.class);

	// This info should be read from file or db at start up
	static {
		worldRecords.put(FieldCompetitionType.LONG_JUMP, new FieldResult(new USLength(29, 2), "Mike Powell"));
		worldRecords.put(FieldCompetitionType.JAVELIN_THROW, new FieldResult(new USLength(323, 1), "Jan Zelzny"));		
	}
	
	private FieldUtils() {;}

	/**
	 * This methods supplies entrance to collection of WorldRecords for specific field competitions.
	 * @param competition a FieldCompetitionType acting as key for lookup
	 * @return FieldResult holding the current WorldRecord
	 */
	public static FieldResult getWorldRecord(FieldCompetitionType competition){
		return worldRecords.get(competition);
	}

	/**
	 * This method compares a competitors performance to current world record
	 * @param winningResult holds the FieldResult to examine
	 * @param competition holds current FieldCompetitionType
	 * @return boolean true if current world record is beaten
	 */
	public static boolean compareToWorldRecord(FieldResult winningResult, FieldCompetitionType competition){

		boolean status = false;

		FieldResult worldRecord = getWorldRecord(competition);	

		if(winningResult.compareTo(worldRecord) > 0){
			status = true;
		}
		return status;
	}

}
