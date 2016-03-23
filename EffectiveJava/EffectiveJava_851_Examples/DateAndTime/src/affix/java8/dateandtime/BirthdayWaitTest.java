package affix.java8.dateandtime;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BirthdayWaitTest {
	

	private static int calculate(LocalDate birthday) {	
		
		System.out.println("Checking up birthday " + birthday);
		
		int waitDays = 0;
		LocalDate today = LocalDate.now();
		LocalDate thisYearBirthDay = LocalDate.of(today.getYear(), birthday.getMonth(), birthday.getDayOfMonth());
		LocalDate nextYearBirthDay = thisYearBirthDay.plusYears(1);

		if(thisYearBirthDay.isAfter(today)){
			waitDays = (int)today.until(thisYearBirthDay, ChronoUnit.DAYS);
		}
		else{
			waitDays = (int)today.until(nextYearBirthDay, ChronoUnit.DAYS);
		}
		
		return waitDays;
	}
	
	public static void main(String[] args) {

		System.out.println("Today's date " + LocalDate.now());
		
		LocalDate[] birthdays = new LocalDate[5];
		
		LocalDate bd1 = LocalDate.of(1973, 5, 12);
		birthdays[0] = bd1;
		LocalDate bd2 = LocalDate.of(1981, 1, 7);
		birthdays[1] = bd2;
		LocalDate bd3 = LocalDate.of(1997, 3, 1);
		birthdays[2] = bd3;
		LocalDate bd4 = LocalDate.of(1966, 10, 30);
		birthdays[3] = bd4;
		LocalDate bd5 = LocalDate.of(1959, 12, 28);
		birthdays[4] = bd5;
		
		for(LocalDate bd : birthdays){
			int waitingDays = calculate(bd);
			String output = String.format("Days to next birtday for person born %s: %d days", bd, waitingDays);
			System.out.println(output);
		}
	}

}
