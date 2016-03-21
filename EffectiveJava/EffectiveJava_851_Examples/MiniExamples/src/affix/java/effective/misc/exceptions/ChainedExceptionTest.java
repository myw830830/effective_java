package affix.java.effective.misc.exceptions;

public class ChainedExceptionTest {

	 public void test(String s) throws SpecialException{

	   try{
	     int number = Integer.parseInt(s);
	     System.out.format("%s converted into int %d", s, number);
	   }
	   catch(NumberFormatException e){
	     throw new SpecialException("Chained exception occured", e);
	   }
	 }
	 
	 
	 public static void main(String[] args){
		  ChainedExceptionTest cet = new ChainedExceptionTest();
		  try{
		    cet.test("123abc");
		  }
		  catch(SpecialException se){
		    System.out.println("----- Top level toString  -------");
		    System.out.println(se.toString());
		    System.out.println("----- Root cause ------");
		    System.out.println(se.getCause());
		  }
		}

}

class SpecialException extends Exception{

	private static final long serialVersionUID = 1L;

	public SpecialException(){
	    super();
	  }

	  public SpecialException(String s, Throwable t){
	    super(s, t);
	  }

	  public String toString(){
	    return "SpecialException wrapping" + getCause();
	  }
	}
