package affix.java.effective.trackandfield;

public abstract class AbstractResult implements TrackAndFieldResult {

	/**
	 * Result holds Competitor effort 
	 */
	private final Object result;	
	
	/**
	 * Owner of this FieldResult
	 */
	private final String id;	
	

	protected AbstractResult(Object result, String id) {
		this.result = result;
		this.id = id;
	}

	@Override
	/**
	 *  Getter
	 *  @return an Object holding a value 
	 */
	public Object getResult() {
		return result;
	}

	/**
	 *  Getter
	 *  @return a String identifying the owner of this result 
	 */
	@Override
	public String getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((this.result == null) ? 0 : this.result.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof AbstractResult)) {
			return false;
		}
		AbstractResult other = (AbstractResult) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (result == null) {
			if (other.result != null) {
				return false;
			}
		} else if (!result.equals(other.result)) {
			return false;
		}
		return true;
	}

}
