package affix.java8.effective.streams;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.*;

public class GroupingStreams {
	
	/**
	 * Conversion method from object Array into a Set 
	 * @return a Set<City> produced from countryArray
	 */
	public static Set<City> createCitySet(Country[] countryArray){
		
		Set<City> citySet = new HashSet<>();
		for(Country c : countryArray){
			for(String cityName: c.getCities()){
				citySet.add(new City(cityName, c.getName()));
			}
		}	
		return citySet;
	}
	
	public static void main(String[] args) {
		
		// starting out with an array holding Country objects which hold an array of City names each
		Country[] countryArray = {
				new Country("Sweden", new String[]{"Stockholm", "Göteborg", "Malmö"}),
				new Country("Denmark", new String[]{"Copenhagen", "Odense", "Aarhus"}),
				new Country("Norway", new String[]{"Oslo", "Bergen"}),
				new Country("Iceland", new String[]{"Reykavik"}),
				new Country("Germany", new String[]{"Berlin", "Hamburg", "Frankfurt", "Munich"}),
				new Country("England", new String[]{"London", "Birmingham", "Liverpool", "Manchester"})
		};
		
		System.out.println("--------------- Original array of Country objects ---------------------");
		for(Country c: countryArray){
			System.out.println(c);
		}
		System.out.println("-----------------------------------------------------------------------");
		
		// convert the initial Array to a Set of City objects, where each City know its Country
		Set<City> citySet = createCitySet(countryArray);
		System.out.println("--------------- Set of City objects from array ------------------------");
		citySet.stream().forEach(System.out::println);
		System.out.println("-----------------------------------------------------------------------");
		
		// create a Map using Country name as key holding List<City> as values
		Map<String, List<City>> cityMap = citySet.stream()
				.collect(Collectors.groupingBy(City::getCountry));
		System.out.println("--------------- Map of City objects ordered by Country ----------------");
		cityMap.keySet().stream().forEach(key -> System.out.println(key+" "+cityMap.get(key)));
		System.out.println("-----------------------------------------------------------------------");
		
		// convert the values of cityMap into a List<City> using flatmap
		List<City> cityList = cityMap.values().stream()
				.flatMap(cList -> cList.stream())
				.collect(Collectors.toList());	
		System.out.println("--------------- List of all City objects from cityMap ---------------------");
		cityList.stream().forEach(System.out::println);
		System.out.println("-----------------------------------------------------------------------");
	}

}

final class Country{
	
	private final String name;
	private final List<String> cities;
	
	public Country(String name, String[] cityArray){
		this.name = name;
		this.cities = Arrays.asList(cityArray);
	}
	
	
	public String getName(){
		return name;
	}
	
	public List<String> getCities(){
		return Collections.unmodifiableList(cities);
	}
	
	@Override
	public String toString() {
		return String.format("Country [name=%s, cities=%s]", name, cities);
	}
	
}

final class City{
	
	private final String name;
	private final String country;
	
	public City(String name, String country){
		this.name = name;
		this.country = country;
	}
	
	public String getName(){
		return name;
	}
	
	public String getCountry(){
		return country;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("City: " + name);
		sb.append(" in " + country);
		return sb.toString();	
	}
}