package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.Category;
import ioc.ContainerException;
import ioc.IocContainer;
import service.CategoryService;
import service.ServiceException;
import view.CategoryEditFrame;
public class SaveCategoryButtonClick implements ActionListener{
	private CategoryEditFrame categoryEditFrame;
	private IocContainer container;

	public SaveCategoryButtonClick(CategoryEditFrame authorEditFrame, IocContainer container) {
		this.categoryEditFrame = authorEditFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Category category = categoryEditFrame.getCategory();
		if(category != null) {
			try {
				CategoryService service = container.getCategoryService();
				service.save(category);
				JOptionPane.showMessageDialog(categoryEditFrame, "Данные успешно сохранены", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
				categoryEditFrame.update(service.findAll());
				categoryEditFrame.dispose();
			} catch(ContainerException | ServiceException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(categoryEditFrame, "Ошибка взаимодействия с базой данных", "Ошибка", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
