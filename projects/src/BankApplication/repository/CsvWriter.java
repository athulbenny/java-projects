package BankApplication.repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import BankApplication.model.*;
import BankApplication.constants.*;

public class CsvWriter {
	
	static String path = Constants.filepath;
	
	public void updateUserEntryToCsv(Customer customer) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path,true))) {
			String s = customer.getCustomerId()+","+customer.getPassword().hashCode()+","+customer.getBalance();
			bw.write(s);
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		
		}
	}
	
	
	public void rewriteDataToCsvAfterDelete(String customerId) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
			for(Customer customer: Constants.users) {
				String s = customer.getCustomerId()+ "," +customer.getPassword()+","+customer.getBalance();
				if(!customer.getCustomerId().equals(customerId)) {
					bw.write(s);
					bw.newLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void removeDuplicatesFromCsv() {
		
		List<Customer> customers = Constants.users;
		List<String> custList = new ArrayList<>();
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
			String s = customers.get(0).getCustomerId()+ "," +customers.get(0).getPassword()+","+customers.get(0).getBalance();
			bw.write(s); 
			bw.newLine();
			
			for(int i = customers.size()-1; i>0; i--) {
				s = customers.get(i).getCustomerId()+ "," +customers.get(i).getPassword()+","+customers.get(i).getBalance();
				
				if(!custList.contains(customers.get(i).getCustomerId())) {
					custList.add(customers.get(i).getCustomerId());
					bw.write(s);
					bw.newLine();
				}
			}
			
			custList.clear();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
