package affix.java.effective.misc.exceptions;

public class BankAccount implements Account {

	private double balance;

	public BankAccount(){
		this(0);
	}
	public BankAccount(double b){
		balance = b;
	}
	
	@Override
	public void deposit(double amount) {
		balance += amount;		
	}

	@Override
	public void withdraw(double x) throws IllegalAmountException{
		if (x < 0) 
			throw new IllegalAmountException ("Negative amount!");
		if (x > balance)
			throw new IllegalAmountException("Amount to high!");
		balance = balance - x;
	}

	@Override
	public double getBalance() {
		return balance;
	}

	
	public static void main (String[] args) {

		  double money = 12345;
		  Account account = new BankAccount();
		  try {
			account.withdraw(money); 
		  }catch (IllegalAmountException e) { 
			  System.out.println(e.getMessage());
		  }
				
		  finally { 
			  System.out.println("End of BankAccount application");
		}

	}
	
}



