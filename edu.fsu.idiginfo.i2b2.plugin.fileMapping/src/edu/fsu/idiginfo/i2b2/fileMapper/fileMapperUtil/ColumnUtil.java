package edu.fsu.idiginfo.i2b2.fileMapper.fileMapperUtil;

import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.Column;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.ColumnData;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.ColumnMatch;


public class ColumnUtil extends FileMapperUtil{
	
	
	public static ColumnData getLastData(ColumnMatch match)
	{
		return (ColumnData) match.getColumns().get(match.getColumns().size()-1);
	}
	public static Column getLastColumn(ColumnMatch match)
	{
		return (match.getColumns().get(match.getColumns().size()-1)).getColumn();
	}
	
	

}
