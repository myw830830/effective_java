package affix.java8.effective.streams;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorStream {

	// using an infinite stream, stopping after 10 items
	public Stream<Integer> getRandomStream(){
		
		Random rd = new Random();
        Stream<Integer> randomValues = Stream.generate((rd::nextInt)).limit(10).map(x -> Math.abs(x % 100));		
        return randomValues;
	}
	
	public static void main(String[] args) {
		
        System.out.println("Collectors");
        
        CollectorStream cs = new CollectorStream();

        List<Integer> randomList = cs.streamToList();
        printCollectionContents("List", randomList);
        
        Set<Integer> randomSet = cs.streamToSet();
        printCollectionContents("Set", randomSet);
        
        Map<Integer, String> randomMap = cs.streamToMap(randomSet);
        printCollectionContents("Map", randomMap.values());
        
	}

	private List<Integer> streamToList() {
		
		Stream<Integer> randoms = getRandomStream();
        List<Integer> randomList = randoms.collect(Collectors.toList());

		return randomList;
	}

	private Set<Integer> streamToSet() {
		
		Stream<Integer> randoms = getRandomStream();
        Set<Integer> randomSet = randoms.collect(Collectors.toSet());
        
		return randomSet;
	}
	
	private Map<Integer, String> streamToMap(Set<Integer> randomSet) {
		
		String baseString = "ABC";
		
		Stream<Integer> randoms = randomSet.stream();
		
		Function<Integer, Integer> keyMapper = Function.identity(); // (Integer value) -> { return value; }; 
		Function<Integer, String> valueMapper = (Integer x) -> { return baseString + String.valueOf(x); };
		
		Map<Integer, String> randomMap = randoms.collect(Collectors.toMap(keyMapper, valueMapper));
		
		return randomMap;
	}
	
	private static void printCollectionContents(String type, Collection<?> values){
		
        System.out.println("Stream converted to a " + type + ", values: ");
        
        for(Object tmp: values){
        	System.out.print(tmp + " ");
        }
        System.out.println();
        
	}
}
