package affix.java.effective.trackandfield;

/**
 * This class acts as a general type to be specified by field and track branches
 */
public abstract class AbstractCompetitor {
	
	/**
	 * Attribute name hold competitor id
	 */
	private final String name;

	public AbstractCompetitor(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
}
