package affix.java.effective.login;

enum Priority{
	
	LOW_PRIORITY(1), 
	MEDIUM_PRIORITY(2), 
	HIGH_PRIORITY(3);
	
	int prioLevel;
	
	Priority(int p){
		prioLevel = p;
	}
	
	int getPrioLevel(){
		return prioLevel;
	}

}
