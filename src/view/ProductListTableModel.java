package view;


import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import domain.Product;
public class ProductListTableModel implements TableModel{
private List<Product> products=new ArrayList<>();
	
	public void setProducts(List<Product> products) {
		this.products=products;
		TableModelEvent event=new TableModelEvent(this);
		for(TableModelListener listener : listeners) {
			listener.tableChanged(event);
		}
	}
	
	public Product getProduct(int index) {
		return products.get(index);
	}
	
	@Override
	public int getRowCount() {
		return products.size();
	}
	
	@Override
	public int getColumnCount() {
		return ProductField.values().length;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return ProductField.values()[columnIndex].getColumnName();
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
		Product product=products.get(rowIndex);
		return ProductField.values()[columnIndex].getValue(product);
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
	
	private static enum ProductField {
		ID("ID"){
			@Override
			public String getValue(Product product) {
				return String.format("%03d", product.getId());
			}
		},
		NAME("Название"){
			@Override
			public String getValue(Product product) {
				return product.getName();
			}
		},
		CATEGORY_ID("Категория"){
			@Override
			public String getValue(Product product) {
				return String.format("%03d",product.getCategory_id());
			}
		},
		COST("Цена"){
			@Override
			public String getValue(Product product) {
				return String.format("%03d",product.getCost());
				}
		},
		DESCRIPTION("Описание"){
			@Override
			public String getValue(Product product) {
				return product.getDescription();
				}
		};
		private final String columnName;
		
		private ProductField(String columnName) {
			this.columnName=columnName;
		}
		
		public String getColumnName() {
			return columnName;
		}
		
		public abstract String getValue(Product product);
	}
}
