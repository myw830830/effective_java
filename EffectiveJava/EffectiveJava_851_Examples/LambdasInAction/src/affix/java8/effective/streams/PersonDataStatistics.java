package affix.java8.effective.streams;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class PersonDataStatistics {

	public List<PersonData> getTestData(){
		
		List<PersonData> persons = new ArrayList<>();
		
		persons.add(new PersonData("Kalle", 34, 7));
		persons.add(new PersonData("Ole", 43, 12));
		persons.add(new PersonData("Emma", 24, 2));
		persons.add(new PersonData("Dragan", 13, 5));
		persons.add(new PersonData("Grace", 69, 3));
		persons.add(new PersonData("Bill", 56, 8));		
		
		return persons;
	}
	
	public void printPersons(List<PersonData> persons){
		
		for(PersonData pd: persons){
			System.out.println(pd);
		}
		System.out.println();
	}
	
	public static void main(String[] args) {

		PersonDataStatistics pdstats = new PersonDataStatistics();
		List<PersonData> persons = pdstats.getTestData();
		pdstats.printPersons(persons);
		collectingStatistics(persons);
	}


	private static void collectingStatistics(List<PersonData> testData) {
		
		System.out.println("Calculating statistics using Collectors");
		
		IntSummaryStatistics stats = testData.stream().collect(Collectors.summarizingInt(PersonData::getAge));
		
		System.out.println("Oldest person: " + stats.getMax());
		System.out.println("Youngest person: " + stats.getMin());
		
		System.out.println("Total age of "+ stats.getCount() + " persons " + stats.getSum());
		System.out.println(String.format("Average age: %4.1f", stats.getAverage()));		
		System.out.println();
	}
	
}