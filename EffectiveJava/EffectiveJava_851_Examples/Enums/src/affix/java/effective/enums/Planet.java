package affix.java.effective.enums;

import java.util.Comparator;

/**
 * Extended enum defining planets in the solar system
 * Apart from being a named constant there are also some attributes defined
 * for each enum value holding planet mass and radius.
 */
public enum Planet {

	// define enum values
	MERCURY(3.302e+23, 2.439e6),
	VENUS(4.869e+24, 6.052e6),
	EARTH(5.975e+24, 6.378e6),
	MARS(6.419e+23, 3.393e6),
	JUPITER(1.899e+27, 7.149e7),
	SATURN(5.685e+26, 6.027e7),
	URANUS(8.683e+25, 2.556e7),
	NEPTUNE(1.024e+26, 2.477e7);
		
	private final double mass;
	private final double radius;
	private final double surfaceGravity;
	
	private static final double G = 6.67300E-11;
	
	// constructor
	Planet(double mass, double radius){
		this.mass = mass;
		this.radius = radius;
		this.surfaceGravity = G*mass / (radius*radius);
	}

	/**
	 * @return the mass
	 */
	public double getMass() {
		return mass;
	}

	/**
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * @return the surfaceGravity
	 */
	public double getSurfaceGravity() {
		return surfaceGravity;
	}
	
	/**
	 *  This method combines attribute values and calculates surface weight
	 *  for a passed parameter mass and a chosen planet.
	 *  @param mass holding value for mass at earth
	 *  @return double holding surface weight
	 */
	public double surfaceWeight(double mass){
		return mass * surfaceGravity;
	}
}

/**
 * A specific comparison class for enum Planet
 * NB! The natural order defined by Enum class is the declared order
 * Method compareTo() is final and cannot be overridden
 */
class PlanetComparator implements Comparator<Planet>{
	@Override
	public int compare(Planet obj1, Planet obj2) {
		return (int) (obj1.getMass() - obj2.getMass());
	}
}