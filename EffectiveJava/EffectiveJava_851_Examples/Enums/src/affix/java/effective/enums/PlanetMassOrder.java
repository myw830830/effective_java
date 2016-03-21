package affix.java.effective.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlanetMassOrder {

	public static void main(String[] args) {
		
		List<Planet> planets = Arrays.asList(Planet.values());
		Collections.sort(planets, new PlanetComparator());
		
		Collections.reverse(planets);
		
		System.out.println("Planets ordered by mass");
		for(Planet p : planets){
			System.out.format("%s\t %5.3e %n", p, p.getMass());
		}

	}

}
