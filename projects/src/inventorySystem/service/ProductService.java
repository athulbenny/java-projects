package inventorySystem.service;

import java.util.List;
import java.util.Scanner;

import inventorySystem.constants.*;
import inventorySystem.model.Product;
import inventorySystem.repository.*;

public class ProductService {
	public ProductService(){
		Constants.productList =  csvreader.readFromCsv();
	}
	
	List<Product> listProd = Constants.productList;

	CsvWriter csvwriter = new CsvWriter();
	CsvReader csvreader = new CsvReader();
	Scanner sc = new Scanner(System.in);
	
	public void addProducts() {
		System.out.println("Enter product name");
		String name = sc.next();
		System.out.println("Enter product price");
		double price = sc.nextDouble();
		System.out.println("Enter product quantity");
		sc.nextLine();
		String quantity = sc.nextLine();
//		String track = sc.next();
		if(price>0 && Double.parseDouble(quantity.split(" ")[0])>=0) {
//			csvwriter.updateUserEntryToCsv(new Product(name,price,quantity.concat(track)));
			csvwriter.updateUserEntryToCsv(new Product(name,price,quantity));

			this.listProd =  csvreader.readFromCsv();
		}
		else {
			System.out.println("Invalid field entry!!!");
		}
	}
	
	public void updateProducts(String name,String fieldTobeChanged) {
		listProd.forEach((item)->{
			if(item.getName().equalsIgnoreCase(name)) {
				if(fieldTobeChanged.equalsIgnoreCase("price")) {
					System.out.println("Enter the "+name+"'s new price");
					item.setPrice(sc.nextDouble());
				}
				if(fieldTobeChanged.equalsIgnoreCase("quantity")) {
					System.out.println("Enter the "+name+"'s new quantity");
					item.setQuantity(sc.next());
				}
			}
		});
		Constants.productList = listProd;
		csvwriter.rewriteDataToCsvAfterUpdate();
	}
	
	public void displayAllProducts() {
		System.out.println("name\tprice\tquantity");
		listProd.forEach((item)->{
			System.out.println(item.getName() + "\t"+item.getPrice() + "\t" + item.getQuantity());
		});
	}
}
