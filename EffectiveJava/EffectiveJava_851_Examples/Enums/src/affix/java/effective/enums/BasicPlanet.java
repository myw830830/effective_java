package affix.java.effective.enums;

//define enum values	
public class BasicPlanet{
	
	public enum Planet {MERCURY, VENUS, EARTH, MARS, JUPITER, SATURN, URANUS, NEPTUNE}
	
	public static void main(String[] args){
		
		Planet[] planetList = Planet.values();
		for(Planet p : planetList){
			System.out.print(p + " ");
		}
		
	}
}
