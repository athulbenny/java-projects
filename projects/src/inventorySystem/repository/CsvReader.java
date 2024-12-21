package inventorySystem.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import inventorySystem.constants.*;
import inventorySystem.model.Product;

public class CsvReader {

	static String path = Constants.filepath;
	
	public  List<Product> readFromCsv() {
		Constants.productList.clear();
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line;
			while((line=br.readLine())!=null) {
				String[] values = line.split(",");
				Product product = new Product( values[0], Double.parseDouble(values[1]), values[2]);
				Constants.productList.add(product);
			}
		}catch(Exception e ) {
			e.printStackTrace();
		}
		return Constants.productList;
	}
	
}

