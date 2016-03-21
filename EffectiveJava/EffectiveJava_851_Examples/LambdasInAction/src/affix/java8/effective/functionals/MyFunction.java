/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package affix.java8.effective.functionals;

import java.util.function.Function;

public class MyFunction {
    
    public static void main(String[] args){
        
        Function<Float, Float> convertFactorFtoC
                = (Float input) -> { return input * 5 / 9; };
        final int OFFSET = 32;
        Function<Float, Float> convertOffsetFtoC
                = (Float input) -> { return input - OFFSET; };
        
        Float tempInF = 100F;   
        Float tempInC = convertOffsetFtoC.andThen(convertFactorFtoC).apply(tempInF);
        
        System.out.println(tempInF + "F converted to " + tempInC + "C");
        
        Float tempInC2 = convertFactorFtoC.compose(convertOffsetFtoC).apply(tempInF);
        System.out.println(tempInF + "F converted to " + tempInC2 + "C");
    }
}
