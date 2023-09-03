package main;

import java.util.List;

import common.Constants;
import entity.ReceiptProduct;
import factory.DaoFactory;

public class ReceiptProductTest {
	
	private DaoFactory daoFactory;	
	
	public ReceiptProductTest() {
		this.daoFactory = DaoFactory.getDaoFactory("postgres");
	}
	
	public void printAllReceiptProducts() {
		List<ReceiptProduct> receiptProducts;
		receiptProducts = this.daoFactory.getReceiptProductDao().getAll();
		for(ReceiptProduct r: receiptProducts) {
			System.out.println(r);
		}
	}
	
	public void printReceiptProduct(int receiptId, int productId) {
		System.out.println(this.daoFactory.getReceiptProductDao().getById(receiptId, productId));
	}
	
	public void insertReceiptProductsFromCsvFile(String path, String[] headers) {
		this.daoFactory.getReceiptProductDao().insertFromCsvFile(path, headers);
	}
	
	public void insertReceiptProduct(int receiptId, int productId, int quantity) {
		this.daoFactory.getReceiptProductDao().insert(receiptId, productId, quantity);
	}
	
	public ReceiptProduct modifyReceiptProduct(int receiptId, int newReceiptId, int productId, int newProductId, int quantity) {
		return this.daoFactory.getReceiptProductDao().modify(receiptId, newReceiptId, productId, newProductId, quantity);
	}
	
	public void deleteReceiptProduct(int receiptId, int productId) {
		this.daoFactory.getReceiptProductDao().deleteById(receiptId, productId);
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
