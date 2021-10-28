package controller;

import java.util.List;

import javax.swing.JOptionPane;

import domain.Category;
import ioc.ContainerException;
import ioc.IocContainer;
import service.CategoryService;
import service.ServiceException;
import view.CategoryListFrame;

public class ApplicationStarter {
	private IocContainer container;
	
	public ApplicationStarter(IocContainer container) {
		this.container=container;
	}
	
	public void start() {
		try {
			CategoryService service=container.getCategoryService();
			List<Category> categories=service.findAll();
			CategoryListFrame categoriesListFrame=new CategoryListFrame(container);
			categoriesListFrame.setCategories(categories);
		}
		catch (ContainerException | ServiceException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ошибка запуска приложения", "Ошибка", JOptionPane.ERROR_MESSAGE);
		}
	}
}
