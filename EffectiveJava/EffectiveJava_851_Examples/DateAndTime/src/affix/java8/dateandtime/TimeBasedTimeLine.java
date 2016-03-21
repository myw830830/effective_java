package affix.java8.dateandtime;
import java.time.Duration;
import java.time.Instant;


public class TimeBasedTimeLine {

	public static void main(String[] args) {

		Instant start = Instant.now();
		System.out.println("Instant value at start: " + start);
		
		try { 
			Thread.sleep(1234);	
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Instant stop = Instant.now();
		System.out.println("Instant value at stop: " + stop);
		
		Duration dur = Duration.between(start, stop);
		
		System.out.println("Starting: " + start.toEpochMilli());
		System.out.println("Stopping: " + stop.toEpochMilli());
		System.out.println("Duration: " + dur.getSeconds() + "s " + dur.getNano() / 1000000 + " ms");
	}

}
