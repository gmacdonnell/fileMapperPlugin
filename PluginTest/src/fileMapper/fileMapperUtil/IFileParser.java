package fileMapper.fileMapperUtil;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;

public interface IFileParser extends IStructuredContentProvider{
	public static int SAMPLES = 5;
	public void loadFile(String filePath) throws Exception;
	public void setDelimiters(List<Object> del);
	public int getFieldCount();
	public List<String[]> parseFile();
	public String[][] getItems();
	

}
