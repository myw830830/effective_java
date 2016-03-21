
package affix.java8.effective.functionals;

import java.util.function.UnaryOperator;

public class MyUnaryOperator {
    
    public static void main(String[] args){
        
        UnaryOperator<Float> tempConvertFactorFtoC
                = (Float input) -> {return input * 5 / 9;};
        
        UnaryOperator<Float> tempConvertOffsetFtoC
                = (Float input) -> {return input - 32;};
        
        Float tempInF = 100F;   
        Float tempInC = tempConvertOffsetFtoC.andThen(tempConvertFactorFtoC).apply(tempInF);
        
        System.out.println("Temperature in Fahrenheit " + tempInF + " converted into Celcius " + tempInC);
        
        Float tempInC2 = tempConvertFactorFtoC.compose(tempConvertOffsetFtoC).apply(tempInF);
        System.out.println("Temperature in Fahrenheit " + tempInF + " converted into Celcius " + tempInC2);
    }
}
