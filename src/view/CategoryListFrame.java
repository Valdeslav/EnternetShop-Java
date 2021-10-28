package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import controller.AddCategoryButtonClick;
import controller.EditCategoryButtonClick;
import domain.Category;
import ioc.IocContainer;

public class CategoryListFrame extends JFrame{
	private CategoryListTableModel model;
	private JTable categoriesListTable;
	
	public CategoryListFrame(IocContainer container) throws HeadlessException{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		model=new CategoryListTableModel();
		categoriesListTable=new JTable(model);
		categoriesListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jScrollPane=new JScrollPane(categoriesListTable);
		add(jScrollPane);
		FlowLayout buttonsPanelLayout=new FlowLayout(FlowLayout.LEFT);
		JPanel buttonsPanel=new JPanel(buttonsPanelLayout);
		JButton addCategoryButton=new JButton("Добавить");
		addCategoryButton.setPreferredSize(new Dimension(100, 30));
		addCategoryButton.addActionListener(new AddCategoryButtonClick(this, container));
		buttonsPanel.add(addCategoryButton);
		JButton editCategoryButton=new JButton("Изменить");
		editCategoryButton.setPreferredSize(new Dimension(100, 30));
		editCategoryButton.addActionListener(new EditCategoryButtonClick(this, container));
		buttonsPanel.add(editCategoryButton);
		add(buttonsPanel, BorderLayout.SOUTH);
		setVisible(true);
	}
	
	public void setCategories(List<Category> categories) {
		model.setCategories(categories);
	}
	
	public Category getSelectedCategory() {
		int index=categoriesListTable.getSelectedRow();
		if(index!=-1) {
			return model.getCategory(index);
		}
		return null;
	}
}
