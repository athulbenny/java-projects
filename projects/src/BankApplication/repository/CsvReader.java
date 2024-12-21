package BankApplication.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import BankApplication.constants.*;
import BankApplication.model.Customer;
import BankApplication.services.Login;

public class CsvReader {

	static String path = Constants.filepath;
	static Login login = new Login();
	
	public  boolean readFromCsvForLogin(String user, String pass) {
		boolean isLogged = false;
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line;
			while((line=br.readLine())!=null) {
				String[] values = line.split(",");
				isLogged = login.route(values,user,pass);
			}
		}catch(Exception e ) {
			e.printStackTrace();
		}
		return isLogged;
	}
	
	public void readDataFromCsvByAdmin() {
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			String line;
			List<Customer> records = new ArrayList<>();
			
			while((line=br.readLine())!=null) {
				String[] values = line.split(",");
					Customer customer = new Customer(values[0],values[1], Double.parseDouble(values[2]));
					records.add(customer);
				}
			
			Constants.users = records;
		
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
