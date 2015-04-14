package edu.fsu.idiginfo.i2b2.fileMapper.fileMapperUtil.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.Column;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.ColumnData;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.ColumnMatch;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.DataField;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.DataSource;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.DataTypeField;
import edu.fsu.idiginfo.i2b2.fileMapper.fileMapperUtil.ColumnUtil;

import javax.swing.table.AbstractTableModel;

public class ColumnTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3064644578128279706L;

	
	protected DataSource columns = new DataSource();


	//public static int ROWS = 5;

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		
		return false;
	}

	@Override
	public String getColumnName(int index) {
		DataTypeField field = columns.getColumns().get(index).getField();
		if(field == null)
		{
		ColumnMatch match = columns.getColumns().get(index).getFileColumn();
		Column column = ColumnUtil.getLastColumn(match);
		return column.getName();
		}
		return field.getFieldCD();
	}
	


	@Override
	public int getColumnCount() {
		return columns.getColumns().size();
	}

	@Override
	public int getRowCount() {
		if (columns.getColumns().size() > 0) {
			return columns.getColumns().get(0).getFileColumn().getColumns().size();
		}
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		try {
				return columns.getColumns().get(columnIndex).getFileColumn().getColumns().get(rowIndex).getValues().get(0);
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if(aValue instanceof Column)
		{
			columns.getColumns().get(columnIndex).getFileColumn().getColumns().set(rowIndex, (ColumnData)aValue);
		}
		
	}

	public void removeAllColumns() {
		columns.getColumns().clear();
	}

	public void removeColumn(int selectedColumn) {
		columns.getColumns().remove(selectedColumn);
		fireTableStructureChanged();
	}

	public void addColumns(List<ColumnMatch> data) {
		if (columns == null)
		{
			columns = new DataSource();
		}
		for(ColumnMatch match :data )
				{
					DataField field = new DataField();
					field.setFileColumn(match);
					columns.getColumns().add(field);
				}
		fireTableStructureChanged();
		fireTableStructureChanged();
	}

	

	

	public void setDataSource(DataSource source)
	{
		columns = source;
		fireTableStructureChanged();
	}
	public DataSource getDataSource()
	{
		
		return columns;
	}
	

	

}
