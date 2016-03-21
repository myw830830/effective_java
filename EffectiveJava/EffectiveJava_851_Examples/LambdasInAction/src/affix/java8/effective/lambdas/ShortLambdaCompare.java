package affix.java8.effective.lambdas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShortLambdaCompare {
    
        public static void main(String[] args){
        
        List<String> strings = new ArrayList<String>();
        
        strings.add("OldJavaStyle");
        strings.add("LambdaStyle");

        // A lot of deductions are taking place here...
        Collections.sort(strings, (s1, s2) 
          -> Integer.compare(s1.length(), s2.length()) ); 
        
        System.out.println("Shortest String is: " + strings.get(0));

    }
}
