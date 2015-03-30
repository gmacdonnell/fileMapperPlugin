package edu.fsu.idiginfo.i2b2.fileMapper.fileMapperUtil;

import java.util.List;
import org.eclipse.jface.viewers.IStructuredContentProvider;

import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.Column;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.ColumnData;

public interface IFileParser extends IStructuredContentProvider{
	
	public static int SAMPLES = 5;
	public void loadFile(String filePath) throws Exception;
	public void loadFileShort(String filePath) throws Exception; // loads only SAMPLES number of records
	public void setDelimiters(List<Object> del);
	public int getFieldCount();
	public List<ColumnData> parseFile();
	public String[][] getItems();
	public Column getColumn();
	@Override
	public String toString();
	public int showEditor();


	
}

