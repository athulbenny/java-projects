package com.credai.testingapi;

public class ProductRepository {

	public Product getProductById(int productId) {
		return new Product(productId, "Sample Product", 10.0);
	}
}
