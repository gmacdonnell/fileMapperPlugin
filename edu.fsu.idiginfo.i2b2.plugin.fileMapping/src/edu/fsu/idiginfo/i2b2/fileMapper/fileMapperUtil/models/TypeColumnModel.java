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
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.DataType;

import javax.swing.table.AbstractTableModel;

public class TypeColumnModel extends AbstractTableModel {

	/**
	 * This model is used for display of fields in a data type.
	 */
	private static final long serialVersionUID = -3769895722886949931L;
	/**
	 * 
	 */
	
	public static String NAME = "Field Name";
	public static String TABLE = "Table Name";
	public static String KEY= "Is Key";
	public static String FIELD = "Field Name";
	public static String FILES = "FILES";
	public static final int INAME = 0;
	public static final int ITABLE = 1;
	public static final int IKEY =2;
	public static final int IFIELD=3;
	public static final int IFILES=4;
	public static final int COLUMNCOUNT = 5;
	private List<DataField> types = new ArrayList<DataField>();
	
	public static int ROWS = 5;

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		
		return false;
	}

	@Override
	public String getColumnName(int column) {
		switch(column){
		case INAME: return NAME;
		case ITABLE: return TABLE;
		case IKEY: return KEY;
		case IFIELD: return FIELD;
		case IFILES: return FILES;
			default: return "ERROR";
		}
		
		
	}
	
	
	@Override
	public int getColumnCount() {
		return COLUMNCOUNT;
	}

	@Override
	public int getRowCount() {
		if (types.size() > 0) {
			return types.size();
		}
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		try {
			
			return getObject(types.get(columnIndex),rowIndex);
		} catch (Exception e) {
			return null;
		}
 
	}

	private Object getObject(DataField type, int column)
	{
		switch(column){
		case INAME: return type.getType().getName();
		case ITABLE: return type.getType().getTableCD();
		case IKEY: return type.getField().isIsKey();
		case IFIELD: return type.getField().getFieldCD();
		case IFILES: return type.getFileColumn().getColumns().get(0).getColumn().getSourceFile();
			default: return "ERROR";
		}
		
	}
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (aValue instanceof ColumnMatch) {
			for(ColumnData col : ((ColumnMatch)aValue).getColumns())
					{
						types.get(rowIndex).getFileColumn().getColumns().add(col);
					}
			fireTableStructureChanged();
		}
		
	}

	public void removeAllColumns() {
		types.clear();
	}

	public void removeColumn(int selectedColumn) {
		types.remove(selectedColumn);
		fireTableStructureChanged();
	}

	public void addColumns(List<DataField> data) {
		types = data;
		fireTableStructureChanged();
	}

	

	

	public DataSource getDataSource()
	{
		DataSource outVal = new DataSource();
	
		
		return outVal;
	}
	
}
