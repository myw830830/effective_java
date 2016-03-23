package affix.java8.effective.functionals;

import java.util.function.Function;

public class TempConverter {
	
	private static Function<Float, Float> tempConvertFactorFtoC(){
		
		 Function<Float, Float> func
         = (Float input) -> {return input * 5 / 9;};
         
		return func;
	}
	
	private static Function<Float, Float> tempConvertOffsetFtoC(){
		
		final int OFFSET = -32;
		
		Function<Float, Float> func
		 = (Float input) -> {return input + OFFSET;};
		 
		return func;
	}
	
	private static Function<Float, Float> tempConvertFactorCtoF(){
		
		 Function<Float, Float> func
        = (Float input) -> {return input * 9 / 5;};
        
		return func;
	}
	
	private static Function<Float, Float> tempConvertOffsetCtoF(){
		
		final int OFFSET = 32;
		
		Function<Float, Float> func
		 = (Float input) -> {return input + OFFSET;};
		 
		return func;
	}

	public static void main(String[] args) {

		Function<Float, Float> temperatureConvertFactorFtoC = tempConvertFactorFtoC();
		Function<Float, Float> temperatureConvertOffsetFtoC = tempConvertOffsetFtoC();
		
		Function<Float, Float> temperatureConvertFactorCtoF = tempConvertFactorCtoF();
		Function<Float, Float> temperatureConvertOffsetCtoF = tempConvertOffsetCtoF();

		Float tempInF = 100F;   
		Float tempInC = temperatureConvertOffsetFtoC.andThen(temperatureConvertFactorFtoC).apply(tempInF);
		System.out.println("Temperature in Fahrenheit " + tempInF + " converted into Celcius " + tempInC);

		Float tempInC2 = temperatureConvertFactorFtoC.compose(temperatureConvertOffsetFtoC).apply(tempInF);
		System.out.println("Temperature in Fahrenheit " + tempInF + " converted into Celcius " + tempInC2);

		System.out.println("-----------------------------------------------------------------------");
		
		Float tempInC_new = 37.77778F;   
		Float tempInF_new = temperatureConvertOffsetCtoF.compose(temperatureConvertFactorCtoF).apply(tempInC_new);
		System.out.println("Temperature in Fahrenheit " + tempInC_new + " converted into Celcius " + tempInF_new);

		Float tempInF2_new = temperatureConvertFactorCtoF.andThen(temperatureConvertOffsetCtoF).apply(tempInC_new);
		System.out.println("Temperature in Fahrenheit " + tempInC_new + " converted into Celcius " + tempInF2_new);

		System.out.println("-----------------------------------------------------------------------");
    
	}

}
