package service;

import java.util.List;
import domain.Category;
public interface CategoryService {
	List<Category> findAll() throws ServiceException;
	
	void save(Category category) throws ServiceException;
	
	void delete(Long id) throws ServiceException;
}
