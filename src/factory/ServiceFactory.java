package factory;

import service.IClientService;
import service.IProductService;
import service.IReceiptProductService;
import service.IReceiptService;

public abstract class ServiceFactory {
	
	private static PostgresServiceFactory postgresServiceFactory;
	
	public static ServiceFactory getServiceFactory(String dataBase) {
		if(dataBase == "postgres") {
			if(postgresServiceFactory == null) {
				postgresServiceFactory = new PostgresServiceFactory();
			}
			return postgresServiceFactory;			
		}
		return null;
	}
	
	public abstract IClientService getClientService();
	
	public abstract IProductService getProductService();
	
	public abstract IReceiptService getReceiptService();
	
	public abstract IReceiptProductService getReceiptProductService();
	
	public abstract void createTable(String query);

}
