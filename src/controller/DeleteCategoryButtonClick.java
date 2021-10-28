package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ioc.ContainerException;
import ioc.IocContainer;
import service.CategoryService;
import service.ServiceException;
import view.CategoryEditFrame;
public class DeleteCategoryButtonClick implements ActionListener{
	private CategoryEditFrame categoryEditFrame;
	private IocContainer container;
	
	public DeleteCategoryButtonClick(CategoryEditFrame categoryEditFrame, IocContainer container) {
		this.categoryEditFrame=categoryEditFrame;
		this.container=container;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		Long id=categoryEditFrame.getCategoryId();
		if(id !=null) {
			if(JOptionPane.showConfirmDialog(categoryEditFrame, "�� ������������� ������ ������� ��� ������?", "������������� ��������", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
				try {
					CategoryService service=container.getCategoryService();
					service.delete(id);
					JOptionPane.showMessageDialog(categoryEditFrame, "������ ������� �������", "���������", JOptionPane.INFORMATION_MESSAGE);
					categoryEditFrame.update(service.findAll());
					categoryEditFrame.dispose();
				}
				catch(ContainerException | ServiceException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(categoryEditFrame, "������ �������������� � ����� ������", "������", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
