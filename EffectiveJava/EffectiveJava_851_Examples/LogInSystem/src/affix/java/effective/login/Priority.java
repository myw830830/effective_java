package affix.java.effective.login;

public enum Priority {
	
	LOW_PRIORITY(1), 
	MEDIUM_PRIORITY(2), 
	HIGH_PRIORITY(3);
	
	int prioLevel;
	
	Priority(int p) {
		this.prioLevel = p;
	}
	
	int getPrioLevel() {
		return prioLevel;
	}
}
