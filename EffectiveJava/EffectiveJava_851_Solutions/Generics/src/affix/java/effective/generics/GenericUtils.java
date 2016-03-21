package affix.java.effective.generics;

import java.util.HashSet;
//import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import affix.java.effective.annotations.EffectiveJava;

@EffectiveJava(item=23 , description="Don't use raw types in new code")
public class GenericUtils {
	
	private GenericUtils() {;}
	
	/**
	 * This is a generic utility method producing a union of two sets
	 * @param <E> place holder for generic type
	 * @param s1 first set of elements
	 * @param s2 second set of elements
	 * @return a Set of elements representing the union of s1 and s2
	 */
	@EffectiveJava(item=27, description="Favor generic methods")	
	public static <E> Set<E> union(Set<E> s1, Set<E> s2){
		Set<E> result = new HashSet<E>(s1);
		result.addAll(s2);
		return result;
	}
	
	/**
	 * This is a generic utility method producing an intersection between two sets
	 * @param <E> place holder for generic type
	 * @param s1 first set of elements
	 * @param s2 second set of elements
	 * @return a Set of elements representing the intersection of s1 and s2
	 */
	@EffectiveJava(item=27, description="Favor generic methods")	
	public static <E> Set<E> intersection(Set<E> s1, Set<E> s2){
		
		Set<E> result = null;

		result = new HashSet<E>();
//		result.retainAll(s2);
		
		for(E first : s1){
			if(s2.contains(first)){
					result.add(first);
				
			}
		}
		
//		Iterator<E> it1 = s1.iterator();
//		while(it1.hasNext()){
//			E item1 = it1.next();
//			Iterator<E> it2 = s2.iterator();
//			while(it2.hasNext()){
//				E item2 = it2.next();
//				if(item1.equals(item2)){
//					result.add(item1);
//				}
//			}
//		}

		return result;
	}

		
	public static void main(String[] args){
		Set<String> s1 = new TreeSet<String>();
		s1.add("One");
		s1.add("Two");
		s1.add("Three");
		s1.add("Four");
		s1.add("Five");
		
		Set<String> s2 = new HashSet<String>();
		s2.add("One");
		s2.add("Three");
		s2.add("Five");
		s2.add("Seven");
		s2.add("Nine");
		
		Set<Integer> i1 = new TreeSet<Integer>();
		for(int i=0; i<4; i++){
			i1.add(i);
		}

		Set<Integer> i2 = new TreeSet<Integer>();
		for(int i=0; i<4; i++){
			i2.add(i*3);
		}

		System.out.println("All uniquee elements found in two String sets ");
		Set<String> results = GenericUtils.union(s1, s2);
		for(String s : results){
			System.out.print(s+" ");
		}
		System.out.println();
		
		System.out.println("All uniquee elements found in two Integer sets ");
		Set<Integer> resulti = GenericUtils.union(i1, i2);
		for(Integer s : resulti){
			System.out.print(s+" ");
		}
		System.out.println();		
		
		System.out.println("----------------------------------------------");
		
		System.out.println("All elements common for two String sets ");
		Set<String> commons = GenericUtils.intersection(s1, s2);
		for(String s : commons){
			System.out.print(s+" ");
		}
		System.out.println();
		
		System.out.println("All elements common for two Integer sets ");
		Set<Integer> commoni = GenericUtils.intersection(i1, i2);
		for(Integer s : commoni){
			System.out.print(s+" ");
		}
		System.out.println();	
	}
}

