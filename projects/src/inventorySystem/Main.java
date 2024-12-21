package inventorySystem;

import java.util.Scanner;

import inventorySystem.service.ProductService;

public class Main {

	public static void main(String[] args) {
	
		ProductService ps = new ProductService();
		Scanner sc = new Scanner(System.in);
		boolean itr =true;
		
		while(itr) {
			System.out.println("Enter 1: add, 2: update 3:display");
			int n = sc.nextInt();
			switch(n) {
			
			case 1:
				ps.addProducts();
				break;
				
			case 2:
				System.out.println("Enter the product name");
				String name = sc.next();
				System.out.println("Enter the product category(price/quantity)");
				String field = sc.next();
				ps.updateProducts(name, field);
				break;
				
			case 3:
				ps.displayAllProducts();
				break;
				
			default:
				System.out.println("invalid choice");
			
			}
			System.out.println("do you want to continue(y/n)");
			itr = sc.next().equalsIgnoreCase("y");
		}
	}

}
