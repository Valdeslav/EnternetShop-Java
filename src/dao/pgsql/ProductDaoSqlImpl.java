package dao.pgsql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.ProductDao;
import dao.DaoException;
import domain.Product;

public class ProductDaoSqlImpl implements ProductDao{
private Connection connection;
	
	public void setConnection(Connection connection) {
		this.connection=connection;
	}

	@Override
	public Long create(Product product) throws DaoException{
		String sql = "INSERT INTO \"product\"(\"name\", \"category_id\", \"cost\", \"description\") VALUES (?, ?, ?, ?)";
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		try {
			statement=connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, product.getName());
			statement.setLong(2, product.getCategory_id());
			statement.setDouble(3, product.getCost());
			statement.setString(4, product.getDescription());
			statement.executeUpdate();
			resultSet=statement.getGeneratedKeys();
			resultSet.next();
			return resultSet.getLong(1);
		}
		catch(SQLException e) {
			throw new DaoException(e);
		}
		finally {
			try {resultSet.close();} catch(Exception e) {}
			try {statement.close();} catch(Exception e) {}
		}
	}
	
	@Override
	public Product read(Long id) throws DaoException{
		String sql="SELECT \"name\", \"category_id\", \"cost\", \"description\" FROM \"product\" WHERE \"id\"=?";
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		try {
			statement=connection.prepareStatement(sql);
			statement.setLong(1, id);
			resultSet=statement.executeQuery();
			Product product=null;
			if(resultSet.next()) {
				product=new Product();
				product.setId(id);
				product.setName(resultSet.getString("name"));
				product.setCategory_id(resultSet.getLong("category_id"));
				product.setCost(resultSet.getDouble("cost"));
				product.setDescription(resultSet.getString("descriptaion"));
			}
			return product;
		}
		catch(SQLException e) {
			throw new DaoException(e);
		}
		finally {
			try {resultSet.close(); } catch(Exception e) {}
			try {statement.close(); } catch(Exception e) {}
		}
	}
	
	@Override
	public void update(Product product) throws DaoException{
		String sql = "UPDATE \"product\" SET \"name\"=?, \"category_id\"=?, \"cost\"=?, \"description\"=? WHERE \"id\"=?";
		PreparedStatement statement =null;
		try {
			statement=connection.prepareStatement(sql);
			statement.setString(1, product.getName());
			statement.setLong(2, product.getCategory_id());
			statement.setDouble(3, product.getCost());
			statement.setString(4, product.getDescription());
			statement.setLong(5, product.getId());
			statement.executeUpdate();
		}
		catch(SQLException e) {
			throw new DaoException(e);
		}
		finally {
			try {statement.close(); } catch(Exception e) {}
		}
	}
	
	@Override
	public void delete(Long id) throws DaoException{
		String sql="DELETE FROM \"product\" WHERE \"id\"=?";
		PreparedStatement statement=null;
		try {
			statement=connection.prepareStatement(sql);
			statement.setLong(1, id);
			statement.executeUpdate();
		}
		catch(SQLException e) {
			throw new DaoException(e);
		}
		finally {
			try { statement.close(); } catch(Exception e) {}
		}
	}
	
	@Override
	public List<Product> readByCategoryId(Long category_id) throws DaoException{
		String sql ="SELECT \"id\", \"name\", \"category_id\", \"cost\", \"description\" FROM \"product\" WHERE \"category_id\"=?";
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		try {
			List<Product> products=new ArrayList<>();
			statement=connection.prepareStatement(sql);
			statement.setLong(1, category_id);
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				Product product=new Product();
				product.setId(resultSet.getLong("id"));
				product.setName(resultSet.getString("name"));
				product.setCategory_id(resultSet.getLong("category_id"));
				product.setCost(resultSet.getDouble("cost"));
				product.setDescription(resultSet.getString("description"));
				products.add(product);
			} 
			return products;
		}
		catch(SQLException e) {
			throw new DaoException(e);
		}
		finally {
			try {resultSet.close();} catch (Exception e) {}
			try {statement.close();} catch (Exception e) {}
		}
	}
}
