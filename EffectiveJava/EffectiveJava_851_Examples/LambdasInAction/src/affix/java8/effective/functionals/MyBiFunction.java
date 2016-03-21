
package affix.java8.effective.functionals;

import java.util.function.BiFunction;

public class MyBiFunction {
	
    
    public static void main(String[] args){
        
    	BiFunction<Long, Integer, Float> meanValueBiFunction
        = (Long sum, Integer observations) -> { return (float)sum/observations;};
        
        Long sum = 1234567L;
        Integer observations = 17;
        
        Float meanValue = meanValueBiFunction.apply(sum, observations);
        
        System.out.println("Mean value calculated to " + meanValue);
             
    }
}
