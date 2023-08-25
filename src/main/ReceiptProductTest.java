package main;

import java.util.List;

import common.Constants;
import factory.ServiceFactory;
import model.ReceiptProduct;

public class ReceiptProductTest {
	
	private ServiceFactory serviceFactory;	
	
	public ReceiptProductTest() {
		this.serviceFactory = ServiceFactory.getServiceFactory("postgres");
	}
	
	public void printAllReceiptProducts() {
		List<ReceiptProduct> receiptProducts;
		receiptProducts = this.serviceFactory.getReceiptProductService().getAll();
		for(ReceiptProduct r: receiptProducts) {
			System.out.println(r);
		}
	}
	
	public void printReceiptProduct(int receiptId, int productId) {
		System.out.println(this.serviceFactory.getReceiptProductService().getById(receiptId, productId));
	}
	
	public void insertReceiptProductsFromCsvFile(String path, String[] headers) {
		this.serviceFactory.getReceiptProductService().insertFromCsvFile(path, headers);
	}
	
	public void insertReceiptProduct(int receiptId, int productId, int quantity) {
		this.serviceFactory.getReceiptProductService().insert(receiptId, productId, quantity);
	}
	
	public ReceiptProduct modifyReceiptProduct(int receiptId, int newReceiptId, int productId, int newProductId, int quantity) {
		return this.serviceFactory.getReceiptProductService().modify(receiptId, newReceiptId, productId, newProductId, quantity);
	}
	
	public void deleteReceiptProduct(int receiptId, int productId) {
		this.serviceFactory.getReceiptProductService().delete(receiptId, productId);
	}
	
	public void run() {
		//insertReceiptProductsFromCsvFile(Constants.RECEIPT_PRODUCT_CSV_PATH, Constants.RECEIPT_PRODUCT_HEADERS);
		//printAllReceiptProducts();                                                                              
		//modifyReceiptProduct(495, 53, 80, 42, 150);                                                             
		//printReceiptProduct(53, 42);                                                                            
		//deleteReceiptProduct(53, 42);                                                                           
		//insertReceiptProduct(53, 42, 220);                                                                      
		//printReceiptProduct(53, 42);
	}
}
