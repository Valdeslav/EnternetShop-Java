package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.DeleteCategoryButtonClick;
import controller.SaveCategoryButtonClick;
import domain.Category;
import ioc.IocContainer;

public class CategoryEditFrame extends JDialog {
	private Category category;
	private JTextField nameTextField;
	
	private CategoryEditFrame(JFrame owner, Category category, String title, IocContainer container) {
		super(owner, title);
		this.category=category;
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 175);
		setResizable(false);
		GridBagLayout layout=new GridBagLayout();
		GridBagConstraints constraints=new GridBagConstraints();
		setLayout(layout);
		
		constraints.fill=GridBagConstraints.BOTH;
		constraints.weightx=1;
		constraints.weighty=1;
		
		JLabel nameLabel=new JLabel("Имя:");
		nameLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth=1;
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.insets=new Insets(40, 20, 10, 10);
		layout.setConstraints(nameLabel, constraints);
		add(nameLabel);
		
		nameTextField=new JTextField(category.getName());
		constraints.gridwidth=2;
		constraints.gridx=1;
		constraints.gridy=0;
		constraints.insets=new Insets(10, 20, 10, 10);
		layout.setConstraints(nameTextField, constraints);
		add(nameTextField);
		
		JButton saveButton=new JButton("Сохранить");
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.insets = new Insets(20, 10, 40, 10);
		layout.setConstraints(saveButton, constraints);
		saveButton.addActionListener(new SaveCategoryButtonClick(this, container));
		add(saveButton);
		
		if(category.getId()!=null) {
			JButton deleteButton = new JButton("Удалить");
			constraints.gridwidth = 1;
			constraints.gridx = 2;
			constraints.gridy = 2;
			constraints.insets = new Insets(20, 10, 40, 20);
			layout.setConstraints(deleteButton, constraints);
			deleteButton.addActionListener(new DeleteCategoryButtonClick(this, container));
			add(deleteButton);
		}
		else {
			JLabel emptyLabel = new JLabel();
			constraints.gridwidth = 1;
			constraints.gridx = 2;
			constraints.gridy = 2;
			constraints.insets = new Insets(20, 10, 40, 20);
			layout.setConstraints(emptyLabel, constraints);
			add(emptyLabel);
		}
		setVisible(true);
	}
	
	public CategoryEditFrame(JFrame owner, Category category, IocContainer container) {
		this(owner, category, String.format("Редактирование Категории %s", category.getName()), container);
	}
	public CategoryEditFrame(JFrame owner, IocContainer container) {
		this(owner, new Category(), "Добавление Категории", container);
	}
	
	public Category getCategory() {
		String name=nameTextField.getText();
		if(name.isBlank()) {
			JOptionPane.showMessageDialog(getOwner(), "Поле «Имя» не заполнено", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		category.setName(name);
		return category;
	}
	
	public Long getCategoryId() {
		return category !=null ? category.getId() : null;
	}
	
	public void update(List<Category> categories) {
		((CategoryListFrame)getOwner()).setCategories(categories);
	}
}
