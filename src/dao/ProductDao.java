package dao;

import java.util.List;
import domain.Product;

public interface ProductDao extends Dao<Product>{
	List<Product> readByCategoryId(Long category_id) throws DaoException;
}
