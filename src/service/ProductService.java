package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import common.Queries;
import dao.ProductDao;
import handler.CustomSQLException;
import model.Product;
import model.ProductDTO;
import utils.CSVReader;

public class ProductService implements IProductService {
	
	private final String CLASS_NAME = getClass().getName();
	
	private ProductDao productDao;
	
	public ProductService(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public List<Product> getAll() {
		
		ArrayList<Product> products = new ArrayList<>();
		try {
			ResultSet rs = productDao.getAll(Queries.GET_ALL_PRODUCTS);
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				float value = rs.getFloat(3);
				products.add(new Product(id, name, value));
			}
		} catch(SQLException e) {
			CustomSQLException.handle(e, "Error: getAll() in Service: " + CLASS_NAME);
		}
		return products;
	}

	@Override
	public Product getById(int productId) {
		try {
			ResultSet rs = this.productDao.getById(productId);
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				float value = rs.getFloat(3);
				return new Product(id, name, value);
			}
		} catch (SQLException e) {
			CustomSQLException.handle(e, "Error: getById() in Service: " + CLASS_NAME);
		}
		return null;
	}

	@Override
	public void insert(int productId, String name, float value) {
		try {
			this.productDao.insert(productId, name, value);
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
					int id = Integer.valueOf(record.get(csvHeader[0]));
					String name = record.get(csvHeader[1]);
					float value = Float.valueOf(record.get(csvHeader[2]));
					this.productDao.insert(id, name, value);
				}				
			} catch(SQLException e) {
				CustomSQLException.handle(e, "Error: insertFromCsvFile() in Service: " + CLASS_NAME);
			}
		}
	}

	@Override
	public Product modify(int productId, int oldNewProductId, String name, float value) {
		Product product = new Product();
		try {
			ResultSet rs = this.productDao.modify(oldNewProductId, oldNewProductId, name, value);
			if(rs != null) {				
				while(rs.next()) {
					product.setProductId(rs.getInt(1));
					product.setName(rs.getString(2));
					product.setValue(rs.getFloat(3));
				}
			}
		} catch (SQLException e) {
			CustomSQLException.handle(e, "Error: modify() in Service: " + CLASS_NAME);
		}
		return product;
	}

	@Override
	public void delete(int productId) {
		try {
			this.productDao.deleteById(productId);
		} catch (SQLException e) {
			CustomSQLException.handle(e, "Error: delete() in Service: " + CLASS_NAME);
		}
	}
	
	public ProductDTO getHigherMoneyCollectedProduct() {
		ProductDTO product = new ProductDTO();
		try {
			ResultSet rs = this.productDao.getHigherMoneyCollectedProduct();
			if(rs != null) {
				while(rs.next()) {
					product.setId(rs.getInt(1));
					product.setName(rs.getString(2));
					product.setQuantity(rs.getInt(3));
					product.setValue(rs.getInt(4));
					product.setTotalValue(rs.getInt(5));			
				}
			}
		} catch (SQLException e) {
			CustomSQLException.handle(e, "Error: getHigherMoneyCollectedProduct() in Service: " + CLASS_NAME);
		}
		return product;
	}
}
