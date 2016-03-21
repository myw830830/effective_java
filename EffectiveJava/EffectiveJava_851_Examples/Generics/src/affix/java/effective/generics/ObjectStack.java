package affix.java.effective.generics;

/**
 * This class will handle any type in a stack using Object
 */
public class ObjectStack {

	private int head = 0;
	private int capacity;
	private Object[] stackData;

	public ObjectStack(int n){
		capacity = n;
		stackData = new Object[capacity];
	}
	
	public void push(Object obj){
		if(hasRoom()){
			stackData[head++]=obj;
		}
	}
	
	public Object pop(){
		Object obj=null;
		if(hasNext()){
			obj = stackData[--head];
		}			
		return obj;
	}
	
	public boolean hasRoom(){
		return (head < capacity)? true: false;
	}
	
	public boolean hasNext(){
		return (head > 0)? true: false;
	}
	
	public void printStack(){
		for(Object obj:stackData){
			if(obj != null){
				System.out.println(obj);
			}
		}
	}


}
