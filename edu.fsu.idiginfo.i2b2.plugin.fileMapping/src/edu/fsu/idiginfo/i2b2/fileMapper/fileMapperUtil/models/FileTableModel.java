package edu.fsu.idiginfo.i2b2.fileMapper.fileMapperUtil.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.ColumnData;
import javax.swing.table.AbstractTableModel;

public class FileTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3064644578128279706L;
	private static int colIndex=0;
	private List<ColumnData> columns = new ArrayList<ColumnData>();
	private boolean editTitle;
	
	public static int ROWS = 5;
	
	  @Override
      public boolean isCellEditable(int rowIndex, int columnIndex) {
         if(columnIndex == 0) return true;
         return false;
      }

	  @Override
      public String getColumnName(int column) {
          return columns.get(column).getColumn().getName();
      }

	@Override
	public int getColumnCount() {
		return columns.size();
	}

	@Override
	public int getRowCount() {
		if(columns.size() > 0)
		{
		return columns.get(0).getValues().size();
		}
		return 0;
	}

	
	 @Override
     public Object getValueAt(int rowIndex, int columnIndex) {
        try{
        	if(rowIndex == 0)return columns.get(columnIndex).getColumn();
        	return columns.get(columnIndex).getValues().get(rowIndex-1);
        }catch(Exception e)
        {
        	return null;
        }
    
     }

     @Override
     public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(aValue instanceof ColumnData)
        {
        	columns.set(columnIndex,(ColumnData)aValue);
        	fireTableStructureChanged();
        }
        if(editTitle)
        {
        	columns.get(columnIndex).getColumn().setName(aValue.toString());
        }
       
     }

     public void removeColumn(int selectedColumn) {
         columns.remove(selectedColumn);
         fireTableStructureChanged();
     }

     public void addColumns(List<ColumnData> data)
     {
    	columns = data;
    	fireTableStructureChanged();
     }
     
     public void enableTitleEdit(boolean value)
     {
    	 editTitle = value;
     }

}
