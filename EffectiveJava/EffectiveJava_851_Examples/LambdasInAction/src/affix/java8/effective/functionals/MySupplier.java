package affix.java8.effective.functionals;

import java.util.function.BooleanSupplier;
import java.util.function.IntSupplier;

public class MySupplier {
    
    public static void main(String[] args) {
        
        BooleanSupplier randomTestSupplier
                = () -> { return ( Math.random()> 0.5);};
        
        for(int b=0; b<5; b++){
            System.out.println( "Random boolean value: " + randomTestSupplier.getAsBoolean() );
        }

        IntSupplier randomDigitSupplier 
                = () -> {return (int)(Math.random()*10);};
        
        for(int i=0; i<10; i++){
            System.out.print("Waiting for the next digit...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println(randomDigitSupplier.getAsInt());
        }    

    }
    
}
