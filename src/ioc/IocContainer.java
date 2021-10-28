package ioc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.CategoryDao;
import dao.ProductDao;
import dao.pgsql.CategoryDaoSqlImpl;
import dao.pgsql.ProductDaoSqlImpl;
import service.CategoryService;
import service.CategoryServiceImpl;
import service.ProductService;
import service.ProductServiceImpl;

public class IocContainer implements AutoCloseable{
	public CategoryService getCategoryService() throws ContainerException{
		CategoryServiceImpl service = new CategoryServiceImpl();
		service.setCategoryDao(getCategoryDao());
		return service;
	}
	
	public CategoryDao getCategoryDao() throws ContainerException{
		CategoryDaoSqlImpl dao=new CategoryDaoSqlImpl();
		dao.setConnection(getConnection());
		return dao;
	}

	public ProductService getProductService() throws ContainerException{
		ProductServiceImpl service =new ProductServiceImpl();
		service.setProductDao(getProductDao());
		return service;
	}
	
	public ProductDao getProductDao() throws ContainerException{
		ProductDaoSqlImpl dao=new ProductDaoSqlImpl();
		dao.setConnection(getConnection());
		return dao;
	}
	
	private Connection connection;
	public Connection getConnection() throws ContainerException{
		if(connection==null) {
			try {
				connection=DriverManager.getConnection("jdbc:postgresql://localhost/enternet_shop", "postgres", "rfnz");
			}
			catch(SQLException e) {
				throw new ContainerException(e);
			}
		}
		return connection;
	}
	
	@Override 
	public void close() {
		try {connection.close();} catch (Exception e) {}
	}
}
