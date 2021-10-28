package service;

import java.util.List;

import dao.ProductDao;
import dao.DaoException;
import domain.Product;

public class ProductServiceImpl implements ProductService{
private ProductDao productDao;
	
	public void setProductDao(ProductDao productDao) {
		this.productDao=productDao;
	}
	
	@Override
	public List<Product> findByCategoryId(Long category_id) throws ServiceException{
		try {
			return productDao.readByCategoryId(category_id);
		}
		catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public void save(Product product) throws ServiceException{
		try{
			if(product.getId()!=null) {
				productDao.update(product);
			}
			else {
				Long id=productDao.create(product);
				product.setId(id);
			}
		}
		catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public void delete(Long id) throws ServiceException{
		try {
			productDao.delete(id);
		}
		catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}
