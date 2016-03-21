package affix.java8.effective.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.Random;


public class StreamPipe {

	public static void main(String[] args) {

        Random rd = new Random();
        
        // Optional can also be used for filtering of inputs to a collection
        List<Integer> bigValues = new ArrayList<Integer>();
        
        for(int i=0; i<8; i++){
             Stream<Integer> randomFilteredValues = Stream.generate(rd::nextInt)
                                                        .limit(10)
                                                        .map(d -> d%100)
                                                        .filter(d -> d>80);                          
            
            Optional<Integer> optionalHighValue = randomFilteredValues.findAny();
            optionalHighValue.ifPresent(v -> bigValues.add(v));

        }
        
        System.out.println("---------- big values ------------- ");
        bigValues.forEach((val) -> System.out.print(val + " "));
	}

}
