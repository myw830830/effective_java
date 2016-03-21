
package affix.java8.effective.lambdas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OldStringCompare {
    
    public static void main(String[] args){
        
        List<String> strings = new ArrayList<String>();
        
        strings.add("OldJavaStyle");
        strings.add("LambdaStyle");

        Collections.sort(strings, new StringComparator());

        System.out.println("Shortest String is: " + strings.get(0));
    }
    
}

class StringComparator implements Comparator<String>{
	
    @Override
    public int compare(String s1, String s2) {
        return s1.length() - s2.length();
    }
}