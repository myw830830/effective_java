package affix.java8.member;

public class HackersClubApp {

	public static void main(String[] args) {
		
		Member m1 = new HackerMember("Bugvally Hackers", "Stro", 835);
		Member m2 = new HackerMember("Bugvally Hackers", "Gos", 723);
		Member m3 = new HackerMember("Bugvally Hackers", "", 99);

		System.out.println(m1);
		System.out.println(m2);
		System.out.println(m3);
		
	}

}
