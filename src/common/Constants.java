package common;

public class Constants {
	
	//Postgres connection properties.
	public static final String POSTGRES_URL = "jdbc:postgresql://127.0.0.1:5432/postgres";
	public static final String POSTGRES_USERNAME = "postgres";
	public static final String POSTGRES_PASSWORD = "Mestelan2610";
	
	//Csv headers
	public static final String[] CLIENT_HEADERS = { "client_id", "name", "email"};
	public static final String[] PRODUCT_HEADERS = { "product_id", "name", "value"};
	public static final String[] RECEIPT_HEADERS = { "receipt_id", "client_id"};
	public static final String[] RECEIPT_PRODUCT_HEADERS = { "receipt_id", "product_id", "quantity"};
	
	//File paths
	public static final String CLIENT_CSV_PATH = "src/csv/files/clients.csv";
	public static final String PRODUCT_CSV_PATH = "src/csv/files/products.csv";
	public static final String RECEIPT_CSV_PATH = "src/csv/files/receipts.csv";
	public static final String RECEIPT_PRODUCT_CSV_PATH = "src/csv/files/receipts_products.csv";
}
