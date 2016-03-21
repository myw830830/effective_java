package affix.java.effective.enums;

import java.util.EnumMap;

public class PlanetInfo {

	private EnumMap<Planet, PlanetData> planetInfoMap = new EnumMap<>(Planet.class);
	
	public PlanetInfo(PlanetData[] data){
		populateMap(data);
	}
	
	private void populateMap(PlanetData[] data){		
		for(PlanetData pd : data){
			planetInfoMap.put(pd.getPlanetId(), pd);
		}
	}
	
	public PlanetData getInfo(Planet p){
		return planetInfoMap.get(p);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		PlanetData[] pd =  
		{
			new PlanetData(Planet.MERCURY, 0.3871, 0.2408, 59),
			new PlanetData(Planet.VENUS, 0.6152, 0.6152, 243),
			new PlanetData(Planet.EARTH, 1.0, 1.0, 1),
			new PlanetData(Planet.MARS, 1.5237, 1.8809, 1),
			new PlanetData(Planet.JUPITER, 5.2028, 11.862, 0.41),
			new PlanetData(Planet.SATURN, 9.54, 29.4577, 0.43),
			new PlanetData(Planet.URANUS, 19.18, 84.013, 0.45),
			new PlanetData(Planet.NEPTUNE, 30.07, 164.79, 0.625)	
		};
		
		PlanetInfo pi  = new PlanetInfo(pd);
		for(Planet p : Planet.values()){
			System.out.println(pi.getInfo(p));
		}
	}

}
