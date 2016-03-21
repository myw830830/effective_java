package affix.java.effective.collections;

public class Country implements Comparable<Country>{
	
	private final String name;
	private final int number;
	private boolean euMember;
	
	public Country(){
		this("Sweden", 46, true);
	}
	
	public Country(String name, int number, boolean euMember) {
		this.name = name;
		this.number = number;
		this.euMember = euMember;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}
	
	/**
	 * @return euMember status
	 */
	public boolean isEuMember() {
		return euMember;
	}

	/**
	 * @param euMember the new euMember status
	 */
	public void setEuMember(boolean euMember) {
		this.euMember = euMember;
	}

	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		System.out.println("### Debug: Country hashCode() called");		
		final int prime = 31;
		int result = 1;
		result = prime * result + (euMember ? 1231 : 1237); // Boolean.TRUE: Boolean.FALSE
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + number;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		System.out.println("### Debug: Country equals() called");
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (euMember != other.euMember)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (number != other.number)
			return false;
		return true;
	}
	
	@Override
	public int compareTo(Country that) {
		System.out.println("### Debug: Country compareTo() called");
		return this.number-that.number;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Country [name=");
		builder.append(name);
		builder.append(", number=");
		builder.append(number);
		builder.append(", euMember=");
		builder.append(euMember);
		builder.append("]");
		return builder.toString();
	}
	
}
