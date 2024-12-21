package scrum17;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Requirements -
 1) There should be two modes, an admin mode and customer mode. Only admin should be able to create and delete bank accounts.
 2) Customers should be able to make deposit, withdrawal and check their current balance.
 3) There should be login functionality for customers where they enter their customerId and password
 4) Customer and admin information[balance, password, customer id] should be stored in a csv file.
*/


class InvalidAmountException extends Exception{
	InvalidAmountException(String msg){
		super(msg);
	}
}

class AccountAlreadyExistException extends Exception{
	AccountAlreadyExistException(String msg){
		super(msg);
	}
}

class NoSuchAccountExistException extends Exception{
	NoSuchAccountExistException(String msg){
		super(msg);
	}
}

//class Customer for performing deposit,withdraw and check balance functions
class Customer{
	String customerId; String password; double balance;
	
Customer(String customerId,String password,double balance){
		this.customerId = customerId;
		this.password = password;
		this.balance = balance;
	}
	
	public void bankProcess() {
		try (Scanner sc = new Scanner(System.in)) {
			boolean tryAgain = true;
			boolean logFlag = true;
			
			while(tryAgain && logFlag) {
				System.out.println("Enter 	1: Deposit,	 2: Withdraw,	3: Check balance,	4: Update password,	5: Logout");
				int n = sc.nextInt();
				switch(n) {
					
				case 1:{
						System.out.println("Enter the amount to be deposited");
						double amount = sc.nextDouble();
						try {
							if(amount < 0) {
								throw new InvalidAmountException("Invalid amount!");
							}
							this.balance += amount;
							updateEntry();
						}catch(InvalidAmountException e) {
							System.out.println(e);
						}
					
						System.out.println("amount successfully added and updated");
						break;
					}
					
				case 2:{
						System.out.println("Enter the amount to be withdrawn");
						double amount = sc.nextDouble();
						try {
							if(amount> this.balance) {
								throw new InvalidAmountException("Insufficient balance!");
							}
							this.balance -= amount;
							updateEntry();
						}catch(InvalidAmountException e) {
							System.out.println(e);
						}
						
						System.out.println("amount successfully debited and updated");
						break;
					}
					
				case 3:
						System.out.println("balance is: "+this.balance);
						break;
					
				case 4:{
						System.out.println("Enter the new password");
						String password = sc.next();
						this.password = password;
						updateEntry();
						System.out.println("password is successfully updated");
						break;
					}
				
				case 5:{
					logFlag = false;
					System.out.println("Successfully Logged out");
					break;
				}
					
				default:
						System.out.println("Invalid option!");
					}
				if(logFlag) {
					System.out.println("Do you want to try again? Y/N");
					tryAgain = sc.next().equalsIgnoreCase("Y");
				}
			}
		}
	}
	
	//function for adding updated customer data to csv file
	public void updateEntry() {
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Cred_Ai_WS\\core_java\\scrum17\\records.csv",true))) {
			String s = this.customerId+","+this.password.hashCode()+","+this.balance;
			bw.write(s);
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		
		}
	}
}

//class Admin for performing create and delete customer
class Admin{
	List<Customer> customers;
	
	Admin(List<Customer> customers){
		this.customers = customers;
		doAutoUpdate();
	}
	
	public void adminDashboardOperation() {
		Scanner sc = new Scanner(System.in);
		boolean tryAgain = true;
		
		while(tryAgain) {
			System.out.println("Enter	1: Create customer	2: Delete customer	3:Customer details	4: Logout");
			int n =  sc.nextInt();
			
			if(n==1) createUser(sc);
			else if(n==2) deleteUser(sc);
			else if(n==3) {
				System.out.println("Currently available Customers: ");
				System.out.println("Customers\tBalance");
				for(int i=1;i<this.customers.size();i++) {
					System.out.println(this.customers.get(i).customerId + "\t\t" + this.customers.get(i).balance);
				}
			}
			else if(n==4) {
				System.out.println("Successfully Logged out");
				break;
			}
			
			System.out.println("Do you want to try again? Y?N");
			tryAgain = sc.next().equalsIgnoreCase("Y");
		}
	}
	
	public void createUser(Scanner sc) {
		
		System.out.println("Enter the customer id");
		String customerId = sc.next();
		
		try {
			for(Customer customer: customers) {
				if(customer.customerId.equals(customerId)) {
					throw new AccountAlreadyExistException("Account Already Exist");
				}
				if(customerId.equalsIgnoreCase("admin")) {
					throw new AccountAlreadyExistException("Process failed");
				}
			}
			
			System.out.println("Enter the customer password");
			String password = sc.next();
			double balance = 0.0;
			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Cred_Ai_WS\\core_java\\scrum17\\records.csv",true))) {
				String s = customerId+","+password.hashCode()+","+balance;
				bw.write(s);
				bw.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				updateCustomersList();
			}  catch (IOException e) {
				e.printStackTrace();
			
			}
		}catch(AccountAlreadyExistException e) {
			System.out.println(e);
		}
		
	}
	
	public void deleteUser(Scanner sc) {
		
		System.out.println("Enter the customer id");
		String customerId = sc.next();
		
		try {
			boolean flag = false;
		for(Customer customer: customers) {
			if(customer.customerId.equals(customerId)) {
				flag = true;
			}
		}
		if(!flag) {
			throw new NoSuchAccountExistException("No such Account Exist");
		}
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Cred_Ai_WS\\core_java\\scrum17\\records.csv"))) {
			for(Customer customer: customers) {
				String s = customer.customerId+ "," +customer.password+","+customer.balance;
				if(!customer.customerId.equals(customerId)) {
					bw.write(s);
					bw.newLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			updateCustomersList();
		}  catch (IOException e) {
			e.printStackTrace();
		
		}
		
		}catch(NoSuchAccountExistException e) {
			System.out.println(e);
		}
		
	}

	//for removing duplicates in the csv file(duplicates that are created by customer(s))
	public void doAutoUpdate() {
		
		List<String> custList = new ArrayList<>();
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Cred_Ai_WS\\core_java\\scrum17\\records.csv"))) {
			String s = customers.get(0).customerId+ "," +customers.get(0).password+","+customers.get(0).balance;
			bw.write(s); 
			bw.newLine();
			
			for(int i = customers.size()-1; i>0; i--) {
				s = customers.get(i).customerId+ "," +customers.get(i).password+","+customers.get(i).balance;
				
				if(!custList.contains(customers.get(i).customerId)) {
					custList.add(customers.get(i).customerId);
					bw.write(s);
					bw.newLine();
				}
			}
			
			custList.clear();
			System.out.println("Currently available Customers: ");
			System.out.println("Customers\tBalance");
			
			for(int i=1;i<customers.size();i++) {
				System.out.println(customers.get(i).customerId + "\t\t" + customers.get(i).balance);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		try {
			updateCustomersList();
		} catch ( IOException e) {
			e.printStackTrace();
		} 
	}
	
	//function for updating the customerList instance variable
	public void updateCustomersList() throws FileNotFoundException, IOException {
		
		try(BufferedReader br = new BufferedReader(new FileReader("C:\\Cred_Ai_WS\\core_java\\scrum17\\records.csv"))){
			String line;
			List<Customer> records = new ArrayList<>();
			
			while((line=br.readLine())!=null) {
				String[] values = line.split(",");
					Customer customer = new Customer(values[0],values[1], Double.parseDouble(values[2]));
					records.add(customer);
				}
			this.customers = records;
		
		}catch(Exception e) {
			System.out.println(e);
		}
		}
}

//Main class
public class Question5 {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Login with your customerId and Password");
			
			System.out.println("Enter CustomerId");
			String username = sc.next();
			System.out.println("Enter password");
			String password = sc.next();
			
			boolean loginFlag=false;
			String mode = "customer";
			String usernameTemp="",passwordTemp="", balanceTemp = "";
			
			List<Customer> records = new ArrayList<>();
			
			try(BufferedReader br = new BufferedReader(new FileReader("C:\\Cred_Ai_WS\\core_java\\scrum17\\records.csv"))){
				
				String line;
				while((line=br.readLine())!=null) {
					String[] values = line.split(",");
					
					if(username.equals(values[0]) && password.hashCode()  == Integer.parseInt(values[1])) {
						loginFlag=true;
						
						if(username.equalsIgnoreCase("AdmIn")) {
							mode = "admin";
						}
						
						if(loginFlag && mode!="admin") {
							usernameTemp = values[0];
							passwordTemp = values[1];
							balanceTemp= values[2];
						}
					}
					
					if(loginFlag && mode == "admin") {
						Customer customer = new Customer(values[0],values[1], Double.parseDouble(values[2]));
						records.add(customer);
					}
				}
			}catch(Exception e) {
				System.out.println(e);
			}
			
			if(loginFlag) {
				System.out.println("login successfull");
				
				if(mode == "admin") {
					Admin admin = new Admin(records);
					admin.adminDashboardOperation();
				}else {
					Customer customer = new Customer(usernameTemp,passwordTemp,Double.parseDouble(balanceTemp));
					customer.bankProcess();
				}
			}else {
				System.out.println("login failed");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}	
	}
}