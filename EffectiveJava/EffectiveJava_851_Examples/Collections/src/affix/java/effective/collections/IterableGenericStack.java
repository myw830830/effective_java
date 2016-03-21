package affix.java.effective.collections;

import java.util.Collection;
import java.util.Iterator;
import affix.java.effective.annotations.EffectiveJava;

/**
 * This class will handle any type in a stack
 * By exchanging T with a specific class you will get a type safe collection
 * @param <T> maps to Object until an instance of the class is created
 */
@EffectiveJava(item=23 , description="Don't use raw types in new code")
public class IterableGenericStack<T> implements Iterable<T>{

	private int head = -1;
	private final int CAPACITY;
	private T[] stackData;

	/**
	 * @param n
	 */
	@SuppressWarnings("unchecked")
	@EffectiveJava(item=24, description="Eliminate unchecked warnings")
	public IterableGenericStack(int n){
		CAPACITY = n;
		stackData = (T[]) new Object[CAPACITY];
	}
	
	@EffectiveJava(item=26, description="Favor generic types")
	public void push(T obj){
		if(hasRoom()){
			stackData[++head] = obj;
		}
	}
	
	/**
	 * @param array
	 */
	@EffectiveJava(item=28 , description="Use bounded wildcards to increase API flexibility")
	public void pushAll(Iterable<? extends T> array){
		for(T temp : array){
			push(temp);
		}
	}	
	
	@EffectiveJava(item=26, description="Favor generic types")
	public T pop(){
		T obj=null;
		if(hasNext()){
			obj = stackData[head--];
			stackData[head+1] = null;
		}			
		return obj;
	}
	
	@EffectiveJava(item=28 , description="Use bounded wildcards to increase API flexibility")	
	public void popAll(Collection<? super T> sink){
		while(head != -1){
			sink.add(pop());
		}
	}
	
	public boolean hasRoom(){
		return (head+1 < CAPACITY)? true: false;
	}
	
	public boolean hasNext(){
		return (head >= 0)? true: false;
	}
	
	public void printStack(){
		for(T obj : stackData){
			if(obj != null){
				System.out.println(obj);
			}
		}
	}

	public int size(){
		return head + 1;
	}

	@Override
	public Iterator<T> iterator() {
		return new GenericStackIterator();
	}

	// nested class supporting normal iteration
	private class GenericStackIterator implements Iterator<T>{
		
		private int pos = 0;
		
		@Override
		public boolean hasNext() {
			return (head >= pos)? true: false;
		}

		@Override
		public T next() {
			return stackData[pos++];
		}

//		default method in Java 8		
//		@Override
//		public void remove() {
//			throw new UnsupportedOperationException("remove is not supported for IterableGenericStack!");
//		}		
	}
	
	public static void main(String[] args){
		
		IterableGenericStack<String> testIGS = new IterableGenericStack<String>(5);
		for(int i=1; i<=5; i++){
			testIGS.push("ABC"+i);
		}
		System.out.println("IterableGenericStack holds "+testIGS.size()+ " elements");
			
		// iterating using for:each loop
		for(String temp : testIGS){
			System.out.println(temp);
		}
		System.out.println("=======================================================");
		
		// iterating using explicit Iterator
		Iterator<String> igsIter = testIGS.iterator();
		while(igsIter.hasNext()){
			String temp = igsIter.next();
			System.out.println(temp);
		}
	}
}
