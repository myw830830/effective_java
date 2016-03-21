package affix.java8.dateandtime;

import java.time.LocalDate;
import java.time.Period;

public class DateBasedTimeLine {

	public static void main(String[] args) {

		LocalDate start =  LocalDate.now();	
		LocalDate stop = LocalDate.of(2020, 6, 30);
		
		Period p = Period.between(start, stop);
				
		System.out.println("Starting: " + start);
		System.out.println("Stopping: " + stop);
		System.out.println("Period: " + p.getYears() + " years " + p.getMonths() + " months " + p.getDays() + " days" );
	}

}
