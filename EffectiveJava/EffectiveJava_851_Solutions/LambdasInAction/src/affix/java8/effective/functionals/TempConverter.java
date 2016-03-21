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

	public static void main(String[] args) {

		Function<Float, Float> temperatureConvertFactorFtoC = tempConvertFactorFtoC();
		Function<Float, Float> temperatureConvertOffsetFtoC = tempConvertOffsetFtoC();

		Float tempInF = 100F;   
		Float tempInC = temperatureConvertOffsetFtoC.andThen(temperatureConvertFactorFtoC).apply(tempInF);
		System.out.println("Temperature in Fahrenheit " + tempInF + " converted into Celcius " + tempInC);

		Float tempInC2 = temperatureConvertFactorFtoC.compose(temperatureConvertOffsetFtoC).apply(tempInF);
		System.out.println("Temperature in Fahrenheit " + tempInF + " converted into Celcius " + tempInC2);

		System.out.println("-----------------------------------------------------------------------");

		Function<Float, Float> temperatureConvertFactorCtoF
		= (Float input) -> {return input * 9 / 5;};

		Function<Float, Float> temperatureConvertOffsetCtoF
		= (Float input) -> {return input + 32;};

		Float temp2InC = 37.77778F;   
		Float temp2InF = temperatureConvertFactorCtoF.andThen(temperatureConvertOffsetCtoF).apply(temp2InC);

		System.out.println("Temperature in Celsius " + temp2InC + " converted into Fahrenheit " + temp2InF);	        
	}

}
