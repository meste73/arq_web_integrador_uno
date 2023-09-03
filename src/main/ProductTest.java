package main;

import java.util.List;

import common.Constants;
import entity.Product;
import factory.DaoFactory;

public class ProductTest {
	
	private DaoFactory daoFactory;
	
	public ProductTest() {
		this.daoFactory = DaoFactory.getDaoFactory("postgres");
	}
	
	public void printAllProducts() {
		List<Product> products;
		products = this.daoFactory.getProductDao().getAll();
		for(Product p: products) {
			System.out.println(p);
		}
	}
	
	public void printProduct(int id) {
		System.out.println(this.daoFactory.getProductDao().getById(id));
	}
	
	public void insertProductsFromCsvFile(String path, String[] headers) {
		this.daoFactory.getProductDao().insertFromCsvFile(path, headers);
	}
	
	public void insertProduct(int id, String name, float value) {
		this.daoFactory.getProductDao().insert(id, name, value);
	}
	
	public Product modifyProduct(int productId, int newProductId, String name, float value) {
		return this.daoFactory.getProductDao().modify(productId, newProductId, name, value);
	}
	
	public void deleteProduct(int id) {
		this.daoFactory.getProductDao().deleteById(id);
	}
	
	public void printHigherMoneyCollectedProduct() {
		System.out.println("\nProducto que mas recaudo:\n");
		System.out.println(this.daoFactory.getProductDao().getHigherMoneyCollectedProduct());
	}
	
	public void run() {
		insertProductsFromCsvFile(Constants.PRODUCT_CSV_PATH, Constants.PRODUCT_HEADERS);
		//printAllProducts();                                                              
		//modifyProduct(35, 35, "Engranaje", 135);                                         
		//printProduct(35);                                                                
		//deleteProduct(35);                                                               
		//insertProduct(35, "Quintuple", 220);                                             
		//printProduct(35);
		//printHigherMoneyCollectedProduct();
	}
}
