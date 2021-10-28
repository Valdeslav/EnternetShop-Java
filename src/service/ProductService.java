package service;

import java.util.List;
import domain.Product;
public interface ProductService {
List<Product> findByCategoryId(Long category_id) throws ServiceException;
	
	void save(Product product) throws ServiceException;
	
	void delete(Long id) throws ServiceException;
}
