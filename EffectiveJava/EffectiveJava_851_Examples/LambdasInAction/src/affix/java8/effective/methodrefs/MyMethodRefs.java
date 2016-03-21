package affix.java8.effective.methodrefs;

import java.util.Arrays;

public class MyMethodRefs {

	public static void main(String[] args) {

		String[] strings = "Mary had a little lamb".split(" ");
		Arrays.sort(strings, String::compareToIgnoreCase);
		System.out.println(Arrays.toString(strings));

		System.out.println("-------------------------------");

	}

}
