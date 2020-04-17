package htmlpull;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {
	

	private static final long serialVersionUID = 1L;
	private String[] column;
	public Object[][] data;
	
	TableModel(String col1,String col2,String col3,String col4,int anzahl) {
		column = new String[4];
		column[0] = col1;
		column[1] = col2;
		column[2] = col3;
		column[3] = col4;
		
		data = new Object[anzahl][4];
	}

	@Override
	public int getRowCount() {
		int mv_numberRows = data.length;
		return mv_numberRows;
	}

	@Override
	public int getColumnCount() {
		int mv_numberCol = data[0].length;
		return mv_numberCol;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object mv_value = data[rowIndex][columnIndex];
		return mv_value;
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (columnIndex == 3) {
	    data[rowIndex][columnIndex] = aValue;
		}
	}
	
	@Override
	public Class<?> getColumnClass(int column) {
		Class<?> mv_variable = boolean.class;
		switch (column) {
		case 0: mv_variable = String.class;
		break;
		case 1: mv_variable = String.class;
		break;
		case 2: mv_variable = String.class;
		break;
		case 3: mv_variable = Boolean.class;
		}
		return mv_variable;
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
		if (col == 3) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String getColumnName(int col) {
	    return column[col];
	}

	
	
}
