package main;

import java.util.List;

import common.Constants;
import entity.ReceiptProduct;
import factory.ServiceFactory;

public class ReceiptProductTest {
	
	private ServiceFactory serviceFactory;	
	
	public ReceiptProductTest() {
		this.serviceFactory = ServiceFactory.getServiceFactory("postgres");
	}
	
	public void printAllReceiptProducts() {
		List<ReceiptProduct> receiptProducts;
		receiptProducts = this.serviceFactory.getReceiptProductDao().getAll();
		for(ReceiptProduct r: receiptProducts) {
			System.out.println(r);
		}
	}
	
	public void printReceiptProduct(int receiptId, int productId) {
		System.out.println(this.serviceFactory.getReceiptProductDao().getById(receiptId, productId));
	}
	
	public void insertReceiptProductsFromCsvFile(String path, String[] headers) {
		this.serviceFactory.getReceiptProductDao().insertFromCsvFile(path, headers);
	}
	
	public void insertReceiptProduct(int receiptId, int productId, int quantity) {
		this.serviceFactory.getReceiptProductDao().insert(receiptId, productId, quantity);
	}
	
	public ReceiptProduct modifyReceiptProduct(int receiptId, int newReceiptId, int productId, int newProductId, int quantity) {
		return this.serviceFactory.getReceiptProductDao().modify(receiptId, newReceiptId, productId, newProductId, quantity);
	}
	
	public void deleteReceiptProduct(int receiptId, int productId) {
		this.serviceFactory.getReceiptProductDao().deleteById(receiptId, productId);
	}
	
	public void run() {
		insertReceiptProductsFromCsvFile(Constants.RECEIPT_PRODUCT_CSV_PATH, Constants.RECEIPT_PRODUCT_HEADERS);
		//printAllReceiptProducts();                                                                              
		//modifyReceiptProduct(495, 53, 80, 42, 150);                                                             
		//printReceiptProduct(53, 42);                                                                            
		//deleteReceiptProduct(53, 42);                                                                           
		//insertReceiptProduct(53, 42, 220);                                                                      
		//printReceiptProduct(53, 42);
	}
}
