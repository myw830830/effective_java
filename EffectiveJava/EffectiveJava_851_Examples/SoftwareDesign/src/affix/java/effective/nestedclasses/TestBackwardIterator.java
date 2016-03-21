package affix.java.effective.nestedclasses;

import java.util.Iterator;

public class TestBackwardIterator {

	public static void main(String[] args) {
				
		String test = "ABCdefGHIjkl";
		BackwardIterator bi = new BackwardIterator(test);
		System.out.print("Test backwardSpeller(): ");
		Iterator<Character> iter = bi.backwardSpeller();
		while(iter.hasNext()){
			System.out.print(iter.next());
		}
		System.out.println();		
	}

}
