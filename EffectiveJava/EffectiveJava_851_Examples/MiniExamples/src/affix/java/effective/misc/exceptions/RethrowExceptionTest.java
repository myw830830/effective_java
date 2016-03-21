package affix.java.effective.misc.exceptions;

public class RethrowExceptionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			handleInputs(args);
		}
		catch(ArrayIndexOutOfBoundsException | NumberFormatException e){
			System.out.println("Arguments missing or incorrect use <number, number>");
		}

	}

	private static void handleInputs(String[] args) 
			throws ArrayIndexOutOfBoundsException, NumberFormatException{
		
		try{
			int firstYear = Integer.parseInt(args[0]);
			int secondYear = Integer.parseInt(args[1]);
			int age = secondYear - firstYear;
			System.out.println("Calculated age is " + age + " years");
		}
		catch(Exception e){
			System.out.println("Logging exception " + e.getMessage());
			throw e;
		}
	}

}