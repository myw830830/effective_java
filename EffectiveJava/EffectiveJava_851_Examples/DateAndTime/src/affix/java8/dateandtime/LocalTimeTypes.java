package affix.java8.dateandtime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LocalTimeTypes {

	public static void main(String[] args) {

		LocalDate date = LocalDate.now();
		System.out.println("LocalDate format: " + date);
		
		LocalTime time = LocalTime.now();
		System.out.println("LocalTime format: " + time);

		LocalDateTime datetime = LocalDateTime.now();
		System.out.println("LocalDateTime format: " + datetime);

	}

}
