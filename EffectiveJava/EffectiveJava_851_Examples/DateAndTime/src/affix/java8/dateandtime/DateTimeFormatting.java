package affix.java8.dateandtime;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeFormatting {

	public static void main(String[] args) {

		// Creating and presenting a LocalDate
		DateTimeFormatter defaultLD = DateTimeFormatter.ISO_DATE;
//		LocalDate ld = LocalDate.of(2014, 12, 02);
		LocalDate ld = LocalDate.parse("2014-12-02", defaultLD);
		System.out.println("Default (ISO_DATE) LocalDate " + ld);
		
		DateTimeFormatter isoDate = DateTimeFormatter.ISO_WEEK_DATE;
		System.out.println("LocalDate (ISO_WEEK_DATE) " + isoDate.format(ld));
		System.out.println("-----------------------------------------------");
		
		// Creating and presenting a LocalTime
		LocalTime lt = LocalTime.of(19, 14, 25);
		System.out.println("Default (ISO_TIME) LocalTime " + lt);

		OffsetTime olt = lt.atOffset(ZoneOffset.ofHours(2));		
		DateTimeFormatter isoTime = DateTimeFormatter.ISO_OFFSET_TIME;
		System.out.println("LocalTime (ISO_OFFSET_TIME) " + isoTime.format(olt));
		System.out.println("-----------------------------------------------");
		
		// Creating and presenting a LocalDateTime
		LocalDateTime ldt = LocalDateTime.of(ld, lt);
		System.out.println("Default LocalDateTime " + ldt);
		
		OffsetDateTime oldt = ldt.atOffset(ZoneOffset.ofHours(2));
		DateTimeFormatter isoDateTime = DateTimeFormatter.ISO_ZONED_DATE_TIME;
		System.out.println("LocalDateTime (ISO_ZONED_DATE_TIME) " + isoDateTime.format(oldt));
		System.out.println("-----------------------------------------------");
		
		System.out.println("************************************************************************");		
		
		// Creating a Duration based on two timestamps of type Instant
		Instant start = Instant.now();
		Instant stop = start.plusMillis(47545000);
		Duration dur = Duration.between(start, stop);
		System.out.println("Default Duration " + dur);				
		
		// Formatting a duration using predefined DateTimeFormatter
		DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
		LocalTime durAsLocalTime = LocalTime.ofSecondOfDay(dur.getSeconds());
		System.out.println("Duration using DateTimeFormatter ISO_LOCAL_TIME " + timeFormatter.format(durAsLocalTime));
		
		// Converting a LocalTime to Duration
		Duration dur2 = Duration.of(durAsLocalTime.toSecondOfDay(), ChronoUnit.SECONDS);
		System.out.println("Duration from LocalTime " + dur2);	
		
	}

}
