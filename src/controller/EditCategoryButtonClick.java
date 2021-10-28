package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.Category;
import ioc.IocContainer;
import view.CategoryEditFrame;
import view.CategoryListFrame;
public class EditCategoryButtonClick implements ActionListener {
	private CategoryListFrame categoriesListFrame;
	private IocContainer container;
	
	public EditCategoryButtonClick(CategoryListFrame categoriesListFrame, IocContainer container) {
		this.categoriesListFrame=categoriesListFrame;
		this.container=container;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Category category=categoriesListFrame.getSelectedCategory();
		if(category!=null) {
			new CategoryEditFrame(categoriesListFrame, category, container);
		}
		else {
			JOptionPane.showMessageDialog(categoriesListFrame, "В таблице не выбрана ни одна категория", "Предупреждение", JOptionPane.WARNING_MESSAGE);;
		}
	}
}
