package affix.java.effective.misc.exceptions;

public class MultiCatchExceptionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try{
			int firstYear = Integer.parseInt(args[0]);
			int secondYear = Integer.parseInt(args[1]);
			int age = secondYear - firstYear;
			System.out.println("Calculated age is " + age + " years");
		}
		catch(ArrayIndexOutOfBoundsException | NumberFormatException e){
			System.out.println("Arguments missing or incorrect use <number, number>");
		}

	}

}
