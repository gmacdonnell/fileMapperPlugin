package edu.fsu.idiginfo.i2b2.fileMapper.fileMapperUtil.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.Column;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.ColumnData;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.ColumnMatch;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.DataSource;

import javax.swing.table.AbstractTableModel;

public class FileTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3064644578128279706L;
	public static int TITLES=1;
	public static int NOTITLES=0;
	public static int TITLESINDEX =0;
	
	private List<ColumnData> columns = new ArrayList<ColumnData>();
	private boolean editTitle;
	private boolean hasTitles;

	public static int ROWS = 5;

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 0 && editTitle)
			return true;
		return false;
	}

	@Override
	public String getColumnName(int column) {
		return columns.get(column).getColumn().getName();
	}
	
	public void setColumnName(int column, String value)
	{
		 columns.get(column).getColumn().setName(value);
		 if(hasTitles)
		 {
				columns.get(column).getValues().set(0,value);
				
		 }
		 fireTableStructureChanged();
		
	}

	@Override
	public int getColumnCount() {
		return columns.size();
	}

	@Override
	public int getRowCount() {
		if (columns.size() > 0) {
			return columns.get(0).getValues().size();
		}
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		try {
			
			return columns.get(columnIndex).getValues().get(rowIndex);
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (aValue instanceof ColumnData) {
			columns.set(columnIndex, (ColumnData) aValue);
			fireTableStructureChanged();
		} else {
			if (editTitle && aValue instanceof String && rowIndex ==0 ) {
				setColumnName(columnIndex, aValue.toString());
				
			}
		}
	}

	public void removeAllColumns() {
		columns.clear();
	}

	public void removeColumn(int selectedColumn) {
		columns.remove(selectedColumn);
		fireTableStructureChanged();
	}

	public void addColumns(List<ColumnData> data) {
		columns = data;
		fireTableStructureChanged();
	}

	public void enableTitleEdit(boolean value) {
		editTitle = value;
	}

	public List<ColumnData> getColumns() {
		
			for(int index = 0; index < columns.size(); index++)
			{
				if(hasTitles)
				{
				columns.get(index).getValues().remove(0);
				columns.get(index).getColumn().getSourceFile().getNotes().add(TITLESINDEX,TITLES);
				}
				else{
					columns.get(index).getColumn().getSourceFile().getNotes().add(TITLESINDEX,NOTITLES);
				}
			}
		return columns;
	}

	public DataSource getDataSource()
	{
		DataSource outVal = new DataSource();
		for(ColumnData cd : columns)
		{
			ColumnMatch match = new ColumnMatch();
			Column column = cd.getColumn();
			if(hasTitles)
			{
				column.getSourceFile().getNotes().set(TITLESINDEX, TITLES);
			}
			else
			{
				column.getSourceFile().getNotes().set(TITLESINDEX,TITLES);
			}
			match.getColumns().add(column);
			outVal.getColumns().add(match);
		}
		
		return outVal;
	}
	public boolean isHasTitles() {
		return hasTitles;
	}

	public void setHasTitles(boolean hasTitles) {
		this.hasTitles = hasTitles;
	}

}
