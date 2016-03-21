package affix.java.effective.misc.formatting;

import java.util.Formatter;
import java.util.Locale;

public class FormatLocale {

	@SuppressWarnings("resource")
	public static void main(String[] args){
		
		Locale swedishLocale = new Locale("sv");
		boolean found = false;
		
		Locale[] locales = Locale.getAvailableLocales();
		for(Locale l:locales){
			System.out.println("Available Locale " + l);
			if(l.toString().equals("sv")){
				found = true;
			}
		}
		if(found){
			Locale.setDefault(swedishLocale);
		}
		System.out.println("Default Locale is " + Locale.getDefault());
		
		Formatter usFormat = new Formatter(Locale.US);
		Formatter defaultFormat = new Formatter(Locale.getDefault());
		
		long value = 1234567890;
		double pi = 3.14159265358979323846;
		
		Formatter usOutput = usFormat.format("Output using Locale.US pi=%7.5f, int value=%,d %n", pi, value);
		System.out.println(usOutput);
		
		Formatter defaultOutput = defaultFormat.format("Output using default Locale pi=%7.5f, int value=%,d %n", pi, value);
		System.out.println(defaultOutput);
		
		Formatter deFormat = new Formatter(Locale.GERMAN);
		System.out.println("German format " + deFormat.format("pi=%7.5f, int value=%,d %n", pi, value));
		
	}
}
