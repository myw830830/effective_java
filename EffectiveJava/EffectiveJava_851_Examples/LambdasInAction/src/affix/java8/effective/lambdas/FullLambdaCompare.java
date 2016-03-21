
package affix.java8.effective.lambdas;

import java.util.*;
import static affix.java8.effective.lambdas.MyLambdaLibrary.comp;

public class FullLambdaCompare {    
	
    public static void main(String[] args){  
    	
        List<String> strings = new ArrayList<String>();     
        strings.add("OldJavaStyle");
        strings.add("LambdaStyle");           
        Collections.sort(strings, comp);

        System.out.println("Shortest String is: " + strings.get(0));
    }
}
