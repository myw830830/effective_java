package affix.java.effective.misc.exceptions;

public interface Account {
	
	public void withdraw(double amount) throws IllegalAmountException;
	
	public void deposit(double amount);
	
	public double getBalance();
}
