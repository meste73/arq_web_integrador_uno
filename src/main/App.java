package main;

import common.Queries;
import factory.ServiceFactory;

public class App {
	
	public static void main(String[] args) {
		run();
	}

	private static void run() {
		
		String[] createTableQuery = { Queries.CREATE_TABLE_CLIENT, 
										Queries.CREATE_TABLE_PRODUCT, 
										Queries.CREATE_TABLE_RECEIPT, 
										Queries.CREATE_TABLE_RECEIPT_PRODUCT};
		
		createTables(createTableQuery);
		
		//Tests for Client entity here.
		ClientTest clTest = new ClientTest();
		clTest.run();
		
		//Tests for Product entity here.
		ProductTest prTest = new ProductTest();
		prTest.run();
		
		//Tests for Receipt entity here
		ReceiptTest reTest = new ReceiptTest();
		reTest.run();
		
		//Tests for ReceiptProduct entity here.
		ReceiptProductTest rePrTest = new ReceiptProductTest();
		rePrTest.run();
		
		clTest.printClientsOrderByReceiptQuantity();
		prTest.printHigherMoneyCollectedProduct();
		
		ServiceFactory.closeConnection();

	}
	
	private static void createTables(String[] queries) {
		ServiceFactory serviceFactory = ServiceFactory.getServiceFactory("postgres");
		for(int i = 0; i < queries.length; i++) {
			serviceFactory.createTable(queries[i]);			
		}
	}
}
