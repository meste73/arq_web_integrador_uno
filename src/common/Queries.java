package common;

public class Queries {
	
	//Create table Client
	public static final String CREATE_TABLE_CLIENT = new StringBuilder("CREATE TABLE IF NOT EXISTS client (")
			.append("client_id int,")
			.append(" name varchar(500),")
			.append(" email varchar(150),")
			.append(" CONSTRAINT pk_client_id PRIMARY KEY (client_id)") 
			.append(")")
			.toString();
	
	//Create table Product
	public static final String CREATE_TABLE_PRODUCT = new StringBuilder("CREATE TABLE IF NOT EXISTS product (")
			.append(" product_id int,")
			.append(" name varchar(45),")
			.append(" value float,")
			.append(" CONSTRAINT pk_product_id PRIMARY KEY (product_id)")
			.append(")")
			.toString();
	
	//Create table Receipt
	public static final String CREATE_TABLE_RECEIPT = "CREATE TABLE IF NOT EXISTS receipt ("
			+ "	receipt_id int,"
			+ "	client_id int,"
			+ "	CONSTRAINT pk_receipt_id PRIMARY KEY (receipt_id),"
			+ "	CONSTRAINT fk_client_id FOREIGN KEY (client_id) REFERENCES client"
			+ ")";
	
	//Create table ReceiptProduct
	public static final String CREATE_TABLE_RECEIPT_PRODUCT = "CREATE TABLE IF NOT EXISTS receipt_product ("
			+ "	receipt_id int,"
			+ "	product_id int,"
			+ "	quantity int,"
			+ "	CONSTRAINT pk_receipt_product_id PRIMARY KEY (receipt_id, product_id),"
			+ "	CONSTRAINT fk_receipt_id FOREIGN KEY (receipt_id) REFERENCES receipt,"
			+ "	CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES client"
			+ ")";
	
	//Client Queries
	public static final String GET_ALL_CLIENTS = "SELECT * FROM client ORDER BY client_id;";
	public static final String GET_CLIENT = "SELECT * FROM client WHERE client_id = ?";
	public static final String INSERT_CLIENT = "INSERT INTO client VALUES (?, ?, ?)";
	public static final String MODIFY_CLIENT = "UPDATE client SET client_id = ?, name = ?, email = ? WHERE client_id = ?";
	public static final String DELETE_CLIENT = "DELETE FROM client WHERE client_id = ?";
	
	//Product Queries
	public static final String GET_ALL_PRODUCTS = "SELECT * FROM product;";
	public static final String GET_PRODUCT = "SELECT * FROM product WHERE product_id = ?";
	public static final String INSERT_PRODUCT = "INSERT INTO product VALUES (?, ?, ?)";
	public static final String MODIFY_PRODUCT = "UPDATE product SET product_id = ?, name = ?, value = ? WHERE product_id = ?";
	public static final String DELETE_PRODUCT = "DELETE FROM product WHERE product_id = ?";
	
	//Receipt Queries
	public static final String GET_ALL_RECEIPTS = "SELECT * FROM receipt;";
	public static final String GET_RECEIPT = "SELECT * FROM receipt WHERE receipt_id = ?";
	public static final String INSERT_RECEIPT = "INSERT INTO receipt VALUES (?, ?)";
	public static final String MODIFY_RECEIPT = "UPDATE receipt SET receipt_id = ?, client_id = ? WHERE receipt_id = ?";
	public static final String DELETE_RECEIPT = "DELETE FROM receipt WHERE receipt_id = ?";
	
	//ReceiptProduct Queries
	public static final String GET_ALL_RECEIPT_PRODUCTS = "SELECT * FROM receipt_product;";
	public static final String GET_RECEIPT_PRODUCT = "SELECT * FROM receipt_product WHERE receipt_id = ? AND product_id = ?";
	public static final String INSERT_RECEIPT_PRODUCT = "INSERT INTO receipt_product VALUES (?, ?, ?)";
	public static final String MODIFY_RECEIPT_PRODUCT = "UPDATE receipt_product SET receipt_id = ?, product_id = ?, quantity = ? WHERE receipt_id = ? AND product_id = ?";
	public static final String DELETE_RECEIPT_PRODUCT = "DELETE FROM receipt_product WHERE receipt_id = ? AND product_id = ?";
	
	//Custom Queries
	public static final String GET_HIGHER_MONEY_COLLECTED_PRODUCT = "SELECT rec.product_id, "
			+ "pro.name, "
			+ "SUM(rec.quantity) AS \"Cantidad\", "
			+ "pro.value \"Valor\", "
			+ "(SUM(rec.quantity) * pro.value) AS \"Valor total\" "
			+ "FROM receipt_product rec "
			+ "JOIN product pro ON rec.product_id = pro.product_id "
			+ "GROUP BY rec.product_id, pro.value, pro.name "
			+ "ORDER BY \"Valor total\" DESC "
			+ "LIMIT 1;";
	
	public static final String GET_CLIENTS_ORDER_BY_RECEIPT_QUANTITY_DESC = "SELECT cli.*, "
			+ "COUNT(rec.receipt_id) AS \"Cantidad facturas\" "
			+ "FROM client cli "
			+ "JOIN receipt rec ON cli.client_id = rec.client_id "
			+ "GROUP BY cli.client_id, cli.name, cli.email "
			+ "ORDER BY \"Cantidad facturas\" DESC;";
}
