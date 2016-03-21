/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package affix.java8.effective.lambdas;

import java.util.Comparator;

public class MyLambdaLibrary {
    
        public static Comparator<String> comp = 
            (String s1, String s2) -> 
         		{ return Integer.compare(s1.length(), s2.length()); };
}
