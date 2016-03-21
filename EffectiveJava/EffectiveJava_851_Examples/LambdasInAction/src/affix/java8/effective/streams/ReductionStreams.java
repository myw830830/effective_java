
package affix.java8.effective.streams;

import java.util.Comparator;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

public class ReductionStreams {
    
     public static void main(String[] args) {

        System.out.println("ReductionStreams");
        
        // using an infinite stream, stopping after 10 items
        Random rd = new Random();
        Stream<Integer> randomValues = Stream.generate((rd::nextInt)).limit(10);      
        Comparator<Integer> highComp = (r1, r2) -> -(r1 - r2);
    
        // an Optional is a replacement for Object/null holding present/not present
        Optional<Integer> optionalValue = randomValues.max(highComp);
        if(optionalValue.isPresent()){
            System.out.println("The optional value is " + optionalValue.get());
        }
        else{
            System.out.println("There is no optional value!");
        }
        
    }
}
