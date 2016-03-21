package affix.java.effective.misc.general;

import java.math.BigDecimal;

public class CountDown {
	
	public static void countDownDouble(double total, double fraction){
		
		while(total > fraction){
			total -= fraction;
			System.out.println("Count down total " + total);
		}
		System.out.println("Final value " + total);
	}
	
	public static void countDownBigDecimal(BigDecimal total, BigDecimal fraction){
		
		while(total.compareTo(fraction) >= 0){
			total = total.subtract(fraction);
			System.out.println("Count down total " + total);
		}
		System.out.println("Final value " + total);
	}
	
	public static void main(String[] args){
		
		double sum = 1.00;
		double fraction = 0.20;		
		countDownDouble(sum, fraction);
			
		BigDecimal total = new BigDecimal("1.00");
		BigDecimal part = new BigDecimal("0.20");		
		countDownBigDecimal(total, part);
		
	}
}
