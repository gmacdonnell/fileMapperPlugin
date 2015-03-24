package edu.fsu.idiginfo.i2b2.fileMapper.fileMapperUtil;

import java.util.List;

import org.eclipse.jface.viewers.Viewer;



import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.Column;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.ColumnData;
import edu.fsu.idiginfo.i2b2.fileMapper.fileMapViews.AbsEditorDlg;

public class BlankFileParser implements IFileParser {
	public static String TYPE= "Choose a file type";
	@Override
	public Object[] getElements(Object inputElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadFile(String filePath) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadFileShort(String filePath) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDelimiters(List<Object> del) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ColumnData> parseFile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[][] getItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Column getColumn() {
		// TODO Auto-generated method stub
		return null;
	}

	public String toString()
	{
		return TYPE;
	}

	@Override
	public int showEditor() {
		// TODO Auto-generated method stub
		return AbsEditorDlg.CANCEL;
	}
}
