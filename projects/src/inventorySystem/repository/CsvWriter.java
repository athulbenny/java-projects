package inventorySystem.repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import inventorySystem.constants.*;
import inventorySystem.model.Product;

public class CsvWriter {
	
	static String path = Constants.filepath;
	
	public void updateUserEntryToCsv(Product product) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path,true))) {
			String s = product.getName() +","+product.getPrice()+","+product.getQuantity();
			bw.write(s);
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		
		}
	}
	
	
	public void rewriteDataToCsvAfterUpdate() {
		List<Product> prodList = Constants.productList;
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
			for(Product item: prodList){
				String s = item.getName()+ "," +item.getPrice()+","+item.getQuantity();
					bw.write(s);
					bw.newLine();
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
