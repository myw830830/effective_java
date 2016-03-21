package affix.java.effective.generics;

public class ArrayCharacteristics {

	
	public static void main(String[] args){
		
		Number obj = new Integer(12);
		System.out.println("An integer held by a Number reference " + obj);
		
		Number[] array = new Number[6];
		array[0] = new Long(1234567890);
		array[1] = new Double(23.1234);
		array[2] = new Float(3.14);
		
		Integer[] intValues = {777, 888, 999};
		for(int i=0; i<intValues.length; i++){
			array[3+i] = intValues[i];
		}
		
		int n = array.length;
		for(int i=0; i<n; i++){
			System.out.println("Element from Number[] " + array[i]);
			array[i] = null;
		}		
		
		System.out.println(" ----------------------- ");
		
		array = intValues;
		for(int i=0; i<array.length; i++){
			System.out.println("Element from Number[] " + array[i]);
		}			
		
	}
	
}
