package service;

import java.util.List;

import model.Receipt;

public interface IReceiptService {
	
	public List<Receipt> getAll();
	
	public Receipt getById(int id);
	
	public void insert(int receiptId, int clientId);
	
	public void insertFromCsvFile(String filePath, String[] csvHeader);
	
	public Receipt modify(int receiptId, int newReceiptId, int clientId);
	
	public void delete(int receiptId);
}
