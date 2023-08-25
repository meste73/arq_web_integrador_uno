package service;

import java.util.List;

import model.Product;
import model.ProductDTO;

public interface IProductService {
	
	public List<Product> getAll();
	
	public Product getById(int productId);
	
	public void insert(int productId, String name, float value);
	
	public void insertFromCsvFile(String filePath, String[] csvHeader);
	
	public Product modify(int productId, int newProductId, String name, float value);
	
	public void delete(int productId);
	
	public ProductDTO getHigherMoneyCollectedProduct();
}
