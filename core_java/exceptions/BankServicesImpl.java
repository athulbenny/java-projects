package exceptions;

public class BankServicesImpl implements BankServices {

	private double balance;
	
	@Override
	public void deposit(double amount) throws InvalidAmountException {
		if( amount <= 0 ) {
			throw new InvalidAmountException(amount +" is invalid amount");
		}
		this.balance = this.balance + amount;		
	}

	@Override
	public double withDraw(double amount) throws InvalidAmountException, InsufficientFundsException {
		
		if( amount <= 0 ) {
			throw new InvalidAmountException(amount +" is invalid amount, check again");
		}
		
		if( this.balance <= amount  ) {
			throw new InsufficientFundsException("Sorry!, Insufficient Funds");
		}
		
		this.balance = this.balance - amount;
		return amount;
	}

	@Override
	public void balance() {
		System.out.println("Current balance is: " + this.balance);
	}

}
