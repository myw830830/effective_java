package affix.java.effective.misc.formatting;

import java.util.Formatter;

public class FormatNumbers {

	public static void main(String[] args){
		
		int[] intArray = {123, 8, -234};
		for(int i=0; i<intArray.length; i++){
			System.out.format("index %d value = %+5d %n", i, intArray[i]);
		}
		System.out.println();
		
		for(int i=0; i<intArray.length; i++){
			System.out.format("index %d value = %- 5d %n", i, intArray[i]);
		}
		System.out.println();

		System.out.format("%d of %d is %4.1f%%", 23, 75, 100*23.0/75);
		System.out.println();

		double[] ds = {1.23, 2.718, 3.1415};
		
		@SuppressWarnings("resource")
		Formatter f = new Formatter();
		f.format("pi = %3$5.3f e = %2$E", ds[0], ds[1], ds[2]);
		System.out.println(f.toString());
		System.out.println();
		
		String text = "ABCdef";
		String s = String.format("Basic string %s first char %c", text, text.charAt(0));
		System.out.println(s);
	}
}
