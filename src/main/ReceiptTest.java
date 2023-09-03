package main;

import java.util.List;

import common.Constants;
import entity.Receipt;
import factory.ServiceFactory;

public class ReceiptTest {
	
	private ServiceFactory serviceFactory;
	
	public ReceiptTest() {
		this.serviceFactory = ServiceFactory.getServiceFactory("postgres");
	}
	
	public void printAllReceipts() {
		List<Receipt> receipts;
		receipts = this.serviceFactory.getReceiptDao().getAll();
		for(Receipt r: receipts) {
			System.out.println(r);
		}
	}
	
	public void printReceipt(int id) {
		System.out.println(this.serviceFactory.getReceiptDao().getById(id));
	}
	
	public void insertReceiptsFromCsvFile(String path, String[] headers) {
		this.serviceFactory.getReceiptDao().insertFromCsvFile(path, headers);
	}
	
	public void insertReceipt(int receiptId, int clientId) {
		this.serviceFactory.getReceiptDao().insert(receiptId, clientId);
	}
	
	public Receipt modifyReceipt(int receiptId, int newReceiptId, int clientId) {
		return this.serviceFactory.getReceiptDao().modify(receiptId, newReceiptId, clientId);
	}
	
	public void deleteReceipt(int id) {
		this.serviceFactory.getReceiptDao().deleteById(id);
	}
	
	public void run() {
		insertReceiptsFromCsvFile(Constants.RECEIPT_CSV_PATH, Constants.RECEIPT_HEADERS);
		//printAllReceipts();                                                              
		//modifyReceipt(42, 42, 12);                                                       
		//printReceipt(42);                                                                
		//deleteReceipt(42);                                                               
		//insertReceipt(42, 16);                                                           
		//printReceipt(42);
	}
}
