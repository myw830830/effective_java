
package affix.java8.effective.streams;

import java.util.stream.Stream;

public class InfiniteStream {
    
     public static void main(String[] args) {

        System.out.println("InfiniteStream");
        
        // using an infinite stream, here Math.random(), stopping after 10 items
        Stream<Double> randomDoubles = Stream.generate(Math::random).limit(10);
        
        // let's apply sorting of random numbers
        Stream<Double> sortedRandoms = randomDoubles.sorted();

        sortedRandoms.forEach(System.out::println);
    }
}
