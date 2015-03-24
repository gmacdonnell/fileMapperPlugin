package edu.fsu.idiginfo.i2b2.fileMapper.fileMapperUtil.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.Column;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.ColumnData;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.ColumnMatch;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.DataSource;
import edu.fsu.idiginfo.i2b2.fileMapper.fileMapperUtil.ColumnUtil;

import javax.swing.table.AbstractTableModel;

public class ColumnTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3064644578128279706L;

	
	private List<ColumnMatch> columns = new ArrayList<ColumnMatch>();


	public static int ROWS = 5;

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		
		return false;
	}

	@Override
	public String getColumnName(int index) {
		ColumnMatch match = columns.get(index);
		Column column = ColumnUtil.getLast(match);
		return column.getName();
	}
	


	@Override
	public int getColumnCount() {
		return columns.size();
	}

	@Override
	public int getRowCount() {
		if (columns.size() > 0) {
			return columns.get(0).getColumns().size();
		}
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		try {
			
			return columns.get(columnIndex).getColumns().get(rowIndex);
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if(aValue instanceof Column)
		{
			columns.get(columnIndex).getColumns().set(rowIndex, (Column)aValue);
		}
		
	}

	public void removeAllColumns() {
		columns.clear();
	}

	public void removeColumn(int selectedColumn) {
		columns.remove(selectedColumn);
		fireTableStructureChanged();
	}

	public void addColumns(List<ColumnMatch> data) {
		columns = data;
		fireTableStructureChanged();
	}

	

	

	public void setDataSource(DataSource source)
	{
		columns = source.getColumns();
		fireTableStructureChanged();
	}
	public DataSource getDataSource()
	{
		DataSource outVal = new DataSource();
		for(ColumnMatch match : columns)
		{
			
			outVal.getColumns().add(match);
		}
		
		return outVal;
	}
	

	

}
