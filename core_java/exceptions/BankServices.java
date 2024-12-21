package exceptions;

public interface BankServices {
	
	public void deposit(double amount) throws InvalidAmountException;
	
	public double withDraw(double amount) throws InvalidAmountException, InsufficientFundsException;
	
	public void balance();
	
}
