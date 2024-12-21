package inventorySystem.model;

public class Product {
	private String name;
	private double price;
	private String quantity;
	
	public Product(String name, double price, String quantity){
		this.setName(name);
		this.setPrice(price);
		this.setQuantity(quantity);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	};
}
