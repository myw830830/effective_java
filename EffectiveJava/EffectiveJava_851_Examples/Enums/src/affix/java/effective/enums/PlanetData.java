package affix.java.effective.enums;

/**
 * This immutable class holds some additional data for planets in a solar system
 */
public final class PlanetData {

	private final Planet planetId;
	private final double solarDistance;
	private final double planetOrbit;
	private final double planetDay;
	
	public PlanetData(Planet planetId, double solarDistance, double planetOrbit, double planetDay) {
		this.planetId = planetId;
		this.solarDistance = solarDistance;
		this.planetOrbit = planetOrbit;
		this.planetDay = planetDay;
	}

	/**
	 * @return the planetId
	 */
	public Planet getPlanetId() {
		return planetId;
	}

	/**
	 * @return the distance (A.U.)
	 */
	public double getSolarDistance() {
		return solarDistance;
	}

	/**
	 * @return the planet orbit (Earth year)
	 */
	public double getPlanetOrbit() {
		return planetOrbit;
	}

	/**
	 * @return the planet day (Earth day)
	 */
	public double getPlanetDay() {
		return planetDay;
	}

	@Override
	public String toString() {
		return "PlanetData [planetId=" + planetId + ", solarDistance=" + solarDistance 
				 + ", planetOrbit=" + planetOrbit + ", planetDay=" + planetDay + "]";
	} 
	
	
}
