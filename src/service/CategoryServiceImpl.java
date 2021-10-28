package service;

import java.util.List;

import dao.CategoryDao;
import dao.DaoException;
import domain.Category;

public class CategoryServiceImpl implements CategoryService{
	private CategoryDao categoryDao;
	
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao=categoryDao;
	}
	
	@Override
	public List<Category> findAll() throws ServiceException{
		try {
			return categoryDao.readAll();
		}
		catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public void save(Category category) throws ServiceException{
		try{
			if(category.getId()!=null) {
				categoryDao.update(category);
			}
			else {
				Long id=categoryDao.create(category);
				category.setId(id);
			}
		}
		catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public void delete(Long id) throws ServiceException{
		try {
			categoryDao.delete(id);
		}
		catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}

