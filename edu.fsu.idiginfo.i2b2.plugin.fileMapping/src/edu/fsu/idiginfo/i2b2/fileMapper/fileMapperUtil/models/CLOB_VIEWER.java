package edu.fsu.idiginfo.i2b2.fileMapper.fileMapperUtil.models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class CLOB_VIEWER extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<ArrayList<String>> Records;
	public CLOB_VIEWER()
	{
		init();
	}
	private void init()
	{
		Records = new ArrayList<ArrayList<String>>();
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		if(Records.get(0) != null)
		{
			return Records.get(0).size();
		}
		else
		{
			return 0;
		}
	}

	@Override
	public int getRowCount() {
		return Records.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		if(Records.size()>row)
		{
			ArrayList<String> record = Records.get(row);
			if(record != null)
			{
				if(record.size()> column)
				{
					return record.get(column);
				}
			}
		}
		
		return null;
	}

}
