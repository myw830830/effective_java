
package affix.java8.effective.functionals;

import java.util.function.BinaryOperator;

public class MyBinaryOperator {
    
        public static void main(String[] args){
        
        BinaryOperator<Integer> meanValueBinaryOperator
                = (Integer sum, Integer observations) -> { return Math.round(sum/observations);};
        
        Integer sum = 1234567;
        Integer observations = 17;
        
        Integer meanValue = meanValueBinaryOperator.apply(sum, observations);
        
        System.out.println("Mean value calculated and rounded to " + meanValue);
             
    }
}
