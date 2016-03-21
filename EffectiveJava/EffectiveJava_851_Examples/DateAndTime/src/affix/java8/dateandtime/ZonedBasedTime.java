package affix.java8.dateandtime;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;

public class ZonedBasedTime {
	
	public static void printZoneIds(){
		System.out.println("Available ZoneIds: ");
		Set<String> zoneIds = ZoneId.getAvailableZoneIds();
		for(String zid: zoneIds){
			System.out.println(zid);
		}
		System.out.println("-------------------------------------------------");
	}

	public static void main(String[] args) {
		
		printZoneIds();
		
		ZonedDateTime start = ZonedDateTime.of(2014, 8, 30, 8, 30, 0, 0, ZoneId.of("Europe/Berlin"));
		
		LocalDate date = LocalDate.of(2014, 9, 1);
		LocalTime time = LocalTime.of(12, 0, 0);
		ZoneId zone = ZoneId.of("Europe/London");
		ZonedDateTime stop = ZonedDateTime.of(date, time, zone);
		
		long zonedTimeSpan = start.until(stop, ChronoUnit.MINUTES);
		Duration dur = Duration.of(zonedTimeSpan, ChronoUnit.MINUTES);
		
		System.out.println("Start: " + start);
		System.out.println("Stop:  " + stop);
		System.out.println("Duration: " + dur.toDays() + " days " 
				+ dur.minusDays(dur.toDays()).toHours() + " hours " 
				+ dur.minusHours(dur.toHours()).toMinutes() + " minutes" );
		
	}

}
