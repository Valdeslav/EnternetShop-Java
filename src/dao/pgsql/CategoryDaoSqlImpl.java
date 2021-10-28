package dao.pgsql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.CategoryDao;
import dao.DaoException;
import domain.Category;

public class CategoryDaoSqlImpl implements CategoryDao{
	private Connection connection;
	
	public void setConnection(Connection connection) {
		this.connection=connection;
	}

	@Override
	public Long create(Category category) throws DaoException{
		String sql = "INSERT INTO \"category\"(\"name\") VALUES (?)";
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		try {
			statement=connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, category.getName());
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
	public Category read(Long id) throws DaoException{
		String sql="SELECT \"name\" FROM \"category\" WHERE \"id\"=?";
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		try {
			statement=connection.prepareStatement(sql);
			statement.setLong(1, id);
			resultSet=statement.executeQuery();
			Category category=null;
			if(resultSet.next()) {
				category=new Category();
				category.setId(id);
				category.setName(resultSet.getString("name"));
			}
			return category;
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
	public void update(Category category) throws DaoException{
		String sql = "UPDATE \"category\" SET \"name\"=? WHERE \"id\"=?";
		PreparedStatement statement =null;
		try {
			statement=connection.prepareStatement(sql);
			statement.setString(1, category.getName());
			statement.setLong(2, category.getId());
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
		String sql="DELETE FROM \"category\" WHERE \"id\"=?";
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
	public List<Category> readAll() throws DaoException{
		String sql ="SELECT \"id\", \"name\" FROM \"category\"";
		Statement statement=null;
		ResultSet resultSet=null;
		try {
			List<Category> categories=new ArrayList<>();
			statement=connection.createStatement();
			resultSet=statement.executeQuery(sql);
			while(resultSet.next()) {
				Category category=new Category();
				category.setId(resultSet.getLong("id"));
				category.setName(resultSet.getString("name"));
				categories.add(category);
			} 
			return categories;
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
