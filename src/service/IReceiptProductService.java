package service;

import java.util.List;

import model.ReceiptProduct;

public interface IReceiptProductService {

	public List<ReceiptProduct> getAll();

	public ReceiptProduct getById(int receiptId, int productId);

	public void insert(int receiptId, int productId, int quantity);
	
	public void insertFromCsvFile(String filePath, String[] csvHeader);
	
	public ReceiptProduct modify(int receiptId, int newReceiptId, int productId, int newProductId, int quantity);
	
	public void delete(int receiptId, int productId);
}
