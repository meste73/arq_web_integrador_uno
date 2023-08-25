package main;

import java.util.List;

import common.Constants;
import factory.ServiceFactory;
import model.Product;

public class ProductTest {
	
	private ServiceFactory serviceFactory;
	
	public ProductTest() {
		this.serviceFactory = ServiceFactory.getServiceFactory("postgres");
	}
	
	public void printAllProducts() {
		List<Product> products;
		products = this.serviceFactory.getProductService().getAll();
		for(Product p: products) {
			System.out.println(p);
		}
	}
	
	public void printProduct(int id) {
		System.out.println(this.serviceFactory.getProductService().getById(id));
	}
	
	public void insertProductsFromCsvFile(String path, String[] headers) {
		this.serviceFactory.getProductService().insertFromCsvFile(path, headers);
	}
	
	public void insertProduct(int id, String name, float value) {
		this.serviceFactory.getProductService().insert(id, name, value);
	}
	
	public Product modifyProduct(int productId, int newProductId, String name, float value) {
		return this.serviceFactory.getProductService().modify(productId, newProductId, name, value);
	}
	
	public void deleteProduct(int id) {
		this.serviceFactory.getProductService().delete(id);
	}
	
	public void printHigherMoneyCollectedProduct() {
		System.out.println("\nProducto que mas recaudo:\n");
		System.out.println(this.serviceFactory.getProductService().getHigherMoneyCollectedProduct());
	}
	
	public void run() {
		//insertProductsFromCsvFile(Constants.PRODUCT_CSV_PATH, Constants.PRODUCT_HEADERS);
		//printAllProducts();                                                              
		//modifyProduct(35, 35, "Engranaje", 135);                                         
		//printProduct(35);                                                                
		//deleteProduct(35);                                                               
		//insertProduct(35, "Quintuple", 220);                                             
		//printProduct(35);
		printHigherMoneyCollectedProduct();
	}
}
