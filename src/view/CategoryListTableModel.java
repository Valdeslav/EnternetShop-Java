package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import domain.Category;
public class CategoryListTableModel implements TableModel{
	private List<Category> categories=new ArrayList<>();
	
	public void setCategories(List<Category> categories) {
		this.categories=categories;
		TableModelEvent event=new TableModelEvent(this);
		for(TableModelListener listener : listeners) {
			listener.tableChanged(event);
		}
	}
	
	public Category getCategory(int index) {
		return categories.get(index);
	}
	
	@Override
	public int getRowCount() {
		return categories.size();
	}
	
	@Override
	public int getColumnCount() {
		return CategoryField.values().length;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return CategoryField.values()[columnIndex].getColumnName();
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex){
		return String.class;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Category category=categories.get(rowIndex);
		return CategoryField.values()[columnIndex].getValue(category);
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {}
	private List<TableModelListener> listeners=new ArrayList<>();
	
	@Override
	public void addTableModelListener(TableModelListener listener) {
		listeners.add(listener);
	}
	
	@Override
	public void removeTableModelListener(TableModelListener listener) {
		listeners.remove(listener);
	}
	
	private static enum CategoryField {
		ID("ID"){
			@Override
			public String getValue(Category category) {
				return String.format("%03d", category.getId());
			}
		},
		NAME("Èìÿ"){
			@Override
			public String getValue(Category category) {
				return category.getName();
			}
		};
		private final String columnName;
		
		private CategoryField(String columnName) {
			this.columnName=columnName;
		}
		
		public String getColumnName() {
			return columnName;
		}
		
		public abstract String getValue(Category category);
	}
}
