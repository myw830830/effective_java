package affix.java8.effective.methodrefs;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Programmer {
	
	private final String name;
	private int skillsLevel;
	
	public Programmer(String name, int skills){
		this.name = name;
		this.skillsLevel = skills;
	}

	public String getName() {
		return name;
	}

	public int getSkillsLevel() {
		return skillsLevel;
	}

	public void setSkillsLevel(int skillsLevel) {
		this.skillsLevel = skillsLevel;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Programmer [name=");
		builder.append(name);
		builder.append(", skillsLevel=");
		builder.append(skillsLevel);
		builder.append("]");
		return builder.toString();
	}

	public static void main(String[] args) {

		Programmer[] hackers = {
				new Programmer("John", 33),
				new Programmer("Fred", 89),
				new Programmer("Emmy", 47)
		};

		List<Programmer> programmers = Arrays.asList(hackers); 
		Collections.sort(programmers, Comparator.comparing(Programmer::getSkillsLevel));
		Collections.reverse(programmers);
		programmers.forEach(System.out::println);

	}
}
