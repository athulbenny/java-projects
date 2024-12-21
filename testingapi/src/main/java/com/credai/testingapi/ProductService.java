package com.credai.testingapi;

public class ProductService {

	private ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public double calculateTotalPrice(int productId, int quantity) {
		Product product = productRepository.getProductById(productId);
		return product.getPrice()*quantity;
	}
}
