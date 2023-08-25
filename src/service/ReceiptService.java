package service;

import common.Queries;

import dao.ReceiptDao;

import handler.CustomSQLException;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import model.Receipt;

import org.apache.commons.csv.CSVRecord;

import utils.CSVReader;

public class ReceiptService implements IReceiptService {
	
	private final String CLASS_NAME = getClass().getName();
	
	private ReceiptDao receiptDao;
	
	public ReceiptService(ReceiptDao receiptDao) {
		this.receiptDao = receiptDao;
	}

	@Override
	public List<Receipt> getAll() {
		List<Receipt> receipts = new ArrayList<>();
		try {
			ResultSet rs = this.receiptDao.getAll(Queries.GET_ALL_RECEIPTS);
			if(rs != null) {
				while(rs.next()) {
					int receiptId = rs.getInt(1);
					int clientId = rs.getInt(2);
					receipts.add(new Receipt(receiptId, clientId));
				}
			}
		} catch (SQLException e) {
			CustomSQLException.handle(e, "Error: getAll() in Service: " + CLASS_NAME);
		}
		return receipts;
	}

	@Override
	public Receipt getById(int id) {
		Receipt receipt = new Receipt();
		try {
			ResultSet rs = this.receiptDao.getById(id);
			if(rs != null) {
				while(rs.next()) {
					receipt.setReceiptId(rs.getInt(1));
					receipt.setClientId(rs.getInt(2));
				}
			}
		} catch (SQLException e) {
			CustomSQLException.handle(e, "Error: getById() in Service: " + CLASS_NAME);
		}
		return receipt;
	}

	@Override
	public void insert(int receiptId, int clientId) {
		try {
			this.receiptDao.insert(receiptId, clientId);
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
					int clientId = Integer.valueOf(record.get(csvHeader[1]));
					this.receiptDao.insert(receiptId, clientId);
				}				
			} catch(SQLException e) {
				CustomSQLException.handle(e, "Error: insertFromCsvFile() in Service: " + CLASS_NAME);
			}
		}
	}

	@Override
	public Receipt modify(int receiptId, int newReceiptId, int clientId) {
		Receipt receipt = new Receipt();
		try {
			ResultSet rs = this.receiptDao.modify(receiptId, newReceiptId, clientId);
			if(rs != null) {
				while(rs.next()) {
					receipt.setReceiptId(rs.getInt(1));
					receipt.setClientId(rs.getInt(2));
				}
			}
		} catch (SQLException e) {
			CustomSQLException.handle(e, "Error: modify() in Service: " + CLASS_NAME);
		}
		return receipt;
	}

	@Override
	public void delete(int receiptId) {
		try {
			this.receiptDao.deleteById(receiptId);
		} catch (SQLException e) {
			CustomSQLException.handle(e, "Error: delete() in Service: " + CLASS_NAME);
		}
	}
}
