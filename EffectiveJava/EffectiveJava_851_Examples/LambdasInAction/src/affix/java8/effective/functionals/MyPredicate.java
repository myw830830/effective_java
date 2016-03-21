
package affix.java8.effective.functionals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class MyPredicate {
    
    public static void main(String[] args){
        
        Predicate<Integer> positiveIntPredicate 
                = (Integer value) -> { return value >= 0;};
        
        for(int i=-2; i<3; i++){
            if(positiveIntPredicate.test(i)){
                System.out.println("Loop variable value " + i + " met Predicate condition");
            }
        }
        for(int i=-2; i<3; i++){
            if(positiveIntPredicate.negate().test(i)){
                System.out.println("Loop variable value " + i + " did not meet Predicate condition");
            }
        }
        
        Predicate<String> shortStringPredicate
                = (String input) -> { return (input.length() < 5);};
        
        String[] names = {"Kalle", "Ole", "Emma", "Dragan", "Grace", ""};
        for(String name : names){
            if(shortStringPredicate.test(name) && !name.isEmpty()){
                System.out.println("A short name found: " + name);
            }
        }
           
        Predicate<Integer> nonZeroPredicate
        = (Integer value) -> { return  (value != 0); };
        
        Predicate<Integer> evenLengthPredicate
                = (Integer value) -> { return  (value%2 == 0); };
            
        for (String name : names) {
            if (nonZeroPredicate.and(evenLengthPredicate).test(name.length())) {
                System.out.println("A name of even number of characters found: " + name);
            }
        }
        
        List<String> nameList = new ArrayList<String>(Arrays.asList(names));
        System.out.println("---- Original contents of nameList ----");
        for(String name : nameList){
            System.out.println(name);
        }
        
        nameList.removeIf(shortStringPredicate);
        System.out.println("---- Remaining contents of filtered nameList ----");
        for(String name : nameList){
            System.out.println(name);
        }      
    }
}
