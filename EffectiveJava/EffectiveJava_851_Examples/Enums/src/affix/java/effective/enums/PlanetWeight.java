package affix.java.effective.enums;

public class PlanetWeight {

	public static void main(String[] args){
		
		double earthWeight = 80; 
		double mass = earthWeight / Planet.EARTH.getSurfaceGravity();
		
		for(Planet p : Planet.values()){
			System.out.printf("Weight on planet %s is %f %n", p, p.surfaceWeight(mass));
		}
	}
}
