package BankApplication;

import BankApplication.constants.*;
import BankApplication.services.AdminServices;
import BankApplication.services.CustomerServices;
import BankApplication.services.Login;
import BankApplication.model.*;

//Main class
public class Main {

	public static void main(String[] args) {
		
		Login login = new Login();
		boolean isLogged = login.login();
		if(isLogged) {
			System.out.println("Login success");
		}
		else {
			System.out.println("Login failed");
			System.exit(0);
			}
		System.out.println("WELCOME "+ Constants.mode +"!");
		if(Constants.mode == "admin") {
			AdminServices adminServices = new AdminServices();
			adminServices.adminDashboardOperation();
		}else {
			Customer customer = Constants.users.get(0);
			CustomerServices customerServices = new CustomerServices(customer);
			customerServices.bankProcess();
		}
	}
}