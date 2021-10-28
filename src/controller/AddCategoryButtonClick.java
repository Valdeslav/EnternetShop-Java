package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ioc.IocContainer;
import view.CategoryEditFrame;
import view.CategoryListFrame;

public class AddCategoryButtonClick implements ActionListener{
	private CategoryListFrame categoryListFrame;
	private IocContainer container;
	
	public AddCategoryButtonClick(CategoryListFrame categoryListFrame, IocContainer container) {
		this.categoryListFrame=categoryListFrame;
		this.container=container;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		new CategoryEditFrame(categoryListFrame, container);
	}
}
