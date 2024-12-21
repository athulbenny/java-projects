package BankApplication.model;


//class Customer for performing deposit,withdraw and check balance functions
public class Customer{
	 private String customerId;
	 private String password; 
	 private double balance;
	
public Customer(String customerId,String password,double balance){
		this.setCustomerId(customerId);
		this.setPassword(password);
		this.setBalance(balance);
	}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public double getBalance() {
	return balance;
}

public void setBalance(double balance) {
	this.balance = balance;
}

public String getCustomerId() {
	return customerId;
}

public void setCustomerId(String customerId) {
	this.customerId = customerId;
}

}
