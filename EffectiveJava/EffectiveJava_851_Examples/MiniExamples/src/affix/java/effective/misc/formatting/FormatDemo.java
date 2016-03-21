package affix.java.effective.misc.formatting;

import java.util.Formatter;

public class FormatDemo {

	public static void main(String[] args){
		
		int x = 47;
		long y = 12312553;
		float f = 2.718F;
		double pi = 3.1415;
		
		String fFormat = callFormatterFormat(x, y, f, pi);
		System.out.println(fFormat);
		
		callPrintWriterFormat(x, y, f, pi);
	
		String stringFormat = callStringFormat(x, y, f, pi);		
		System.out.println(stringFormat);
	}

	private static String callStringFormat(int x, long y, float f, double pi) {
		String myString = String.format("Testing String.format() int %+d float %5.3f char %c", x, f, 'X');
		return myString;
	}

	private static void callPrintWriterFormat(int x, long y, float f, double pi) {
		System.out.format("Testing PrintWriter.format() double %e String %s long %d %n", pi, "Dummy", y);
	}

	private static String callFormatterFormat(int x, long y, float f, double pi) {
		@SuppressWarnings("resource")
		Formatter fm = new Formatter();
		fm.format("Testing Formatter.format() int %d float %f char %c", x, f, 'X');
		return fm.toString();		
	}
}
