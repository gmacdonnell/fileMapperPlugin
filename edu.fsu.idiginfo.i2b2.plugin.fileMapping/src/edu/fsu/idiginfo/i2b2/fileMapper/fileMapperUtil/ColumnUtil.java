package edu.fsu.idiginfo.i2b2.fileMapper.fileMapperUtil;

import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.Column;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.ColumnMatch;


public class ColumnUtil extends FileMapperUtil{
	
	
	public static Column getLast(ColumnMatch match)
	{
		return match.getColumns().get(match.getColumns().size()-1);
	}
	
	

}
