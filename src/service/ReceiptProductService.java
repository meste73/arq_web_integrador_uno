package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import common.Queries;
import dao.ReceiptProductDao;
import handler.CustomSQLException;
import model.ReceiptProduct;
import utils.CSVReader;

public class ReceiptProductService implements IReceiptProductService {
	
	private String CLASS_NAME = getClass().getName();
	
	private ReceiptProductDao receiptProductDao;
	
	public ReceiptProductService(ReceiptProductDao receiptProductDao) {
		this.receiptProductDao = receiptProductDao;
	}
	
	@Override
	public List<ReceiptProduct> getAll() {
		List<ReceiptProduct> receiptProducts = new ArrayList<>();
		try {
			ResultSet rs = this.receiptProductDao.getAll(Queries.GET_ALL_RECEIPT_PRODUCTS);
			if(rs != null) {
				while(rs.next()) {
					int receiptId = rs.getInt(1);
					int productId = rs.getInt(2);
					int quantity = rs.getInt(3);
					receiptProducts.add(new ReceiptProduct(receiptId, productId, quantity));
				}
			}
		} catch (SQLException e) {
			CustomSQLException.handle(e, "Error: getAll() in Service: " + CLASS_NAME);
		}
		return receiptProducts;
	}

	@Override
	public ReceiptProduct getById(int receiptId, int productId) {
		ReceiptProduct receiptProduct = new ReceiptProduct();
		try {
			ResultSet rs = this.receiptProductDao.getById(receiptId, productId);
			if(rs != null) {
				while(rs.next()) {
					receiptProduct.setReceiptId(rs.getInt(1));
					receiptProduct.setProductId(rs.getInt(2));
					receiptProduct.setQuantity(rs.getInt(3));
				}
			}
		} catch (SQLException e) {
			CustomSQLException.handle(e, "Error: getById() in Service: " + CLASS_NAME);
		}
		return receiptProduct;
	}

	@Override
	public void insert(int receiptId, int productId, int quantity) {
		try {
			this.receiptProductDao.insert(receiptId, productId, quantity);
		} catch (SQLException e) {
			CustomSQLException.handle(e, "Error: insert() in Service: " + CLASS_NAME);
		}
	}
	
	@Override
	public void insertFromCsvFile(String filePath, String[] csvHeader) {
		
		CSVReader reader = new CSVReader();
		Iterable<CSVRecord> records = reader.read(filePath, csvHeader);
		
		if(records != null) {
			try {
				for (CSVRecord record : records) {
					int receiptId = Integer.valueOf(record.get(csvHeader[0]));
					int productId = Integer.valueOf(record.get(csvHeader[1]));
					int quantity = Integer.valueOf(record.get(csvHeader[2]));
					this.receiptProductDao.insert(receiptId, productId, quantity);
				}				
			} catch(SQLException e) {
				CustomSQLException.handle(e, "Error: insertFromCsvFile() in Service: " + CLASS_NAME);
			}
		}
	}

	@Override
	public ReceiptProduct modify(int receiptId, int newReceiptId, int productId, int newProductId, int quantity) {
		ReceiptProduct receiptProduct = new ReceiptProduct();
		try {
			ResultSet rs = this.receiptProductDao.modify(receiptId, newReceiptId, productId, newProductId, quantity);
			if(rs != null) {
				while(rs.next()) {
					receiptProduct.setReceiptId(rs.getInt(1));
					receiptProduct.setProductId(rs.getInt(2));
					receiptProduct.setQuantity(rs.getInt(3));
				}
			}
		} catch (SQLException e) {
			CustomSQLException.handle(e, "Error: 'modify() in Service: " + CLASS_NAME);
		}
		return receiptProduct;
	}

	@Override
	public void delete(int receiptId, int productId) {
		try {
			this.receiptProductDao.deleteById(receiptId, productId);
		} catch (SQLException e) {
			CustomSQLException.handle(e, "Error: delete() in Service: " + CLASS_NAME);
		}
	}
}
