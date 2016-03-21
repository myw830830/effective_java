package affix.java8.dateandtime;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BirthdayWaitTest {
	
	private static int calculate(LocalDate birthday){	
		
		System.out.println("Checking up birthday " + birthday);
		
		int waitDays = 0;
		LocalDate today = LocalDate.now();
		
		// we might have to move on to next year
		LocalDate thisYearBirthDay = LocalDate.of(today.getYear(), birthday.getMonth(), birthday.getDayOfMonth());
		LocalDate nextYearBirthDay = thisYearBirthDay.plusYears(1);
		
		int todayNo = today.getDayOfYear();
		int birthdayNoNextYear = nextYearBirthDay.getDayOfYear();

		if(thisYearBirthDay.isAfter(today)){
			waitDays = (int)today.until(thisYearBirthDay, ChronoUnit.DAYS);
//			System.out.println("Debug: Next birthday is within this year, days to wait " + waitDays);
		}
		else{
			int lastDayOfYear = LocalDate.of(today.getYear(), 12, 31).getDayOfYear();
			waitDays = lastDayOfYear - todayNo + birthdayNoNextYear;
//			System.out.println("Debug: Next birthday happens next year, days to wait " + waitDays);
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
