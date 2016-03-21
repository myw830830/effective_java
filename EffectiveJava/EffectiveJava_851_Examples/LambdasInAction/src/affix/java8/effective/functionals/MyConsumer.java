package affix.java8.effective.functionals;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public class MyConsumer {

    public static void main(String[] args) {

        String[] names = {"Kalle", "Ole", "Emma", "Dragan", "Grace"};

        Consumer<String> upperCaseConsumer
                = (String name) -> {
                    System.out.print(name.toUpperCase()+" ");
                };

        for (String s : names) {
            upperCaseConsumer.accept(s);
        }
        
        System.out.println();
        
        Consumer<String> lowerCaseConsumer
                = (String name) -> {
                    System.out.println(name.toLowerCase());
                };
                
        final List<Character> initials = new ArrayList<Character>();
        Consumer<String> initialConsumer
                = (String x) -> {
                    initials.add(x.charAt(0));
                };
        
        for(String s : names){
           lowerCaseConsumer.andThen(initialConsumer).accept(s);
        }
        
        System.out.println("----- The initials -----");
        for(int c=0; c<initials.size(); c++){
            System.out.print(initials.get(c));
        }
        System.out.println();
    }
}
