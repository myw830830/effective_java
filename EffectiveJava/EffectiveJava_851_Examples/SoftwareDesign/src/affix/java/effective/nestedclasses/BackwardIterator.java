package affix.java.effective.nestedclasses;

import java.util.Iterator;

public class BackwardIterator {

	private final String text;
	
	public BackwardIterator(String text){
		this.text = text;
	}
	
	public Iterator<Character> backwardSpeller(){
		
		// local class
		class LocalIterator implements Iterator<Character>{
			private int pos = text.length()-1;
			
			public boolean hasNext() {
				return (!text.isEmpty() && pos>=0);
			}

			public Character next() {
				return text.charAt(pos--);
			}

			public void remove() {
				throw new UnsupportedOperationException();				
			}			
		}
		return new LocalIterator();
	}
	
}
