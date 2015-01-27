package plugintest.fileLoad;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import plugintest.Data.CSVFileReader;

public class fileLoader {
	private CSVFileReader CSVReader= null;
	String FileName = null;
	Vector<String> headers = null;
	Vector<Vector<String>> grid = null;
	int colCount =0;
	int rowCount = 0;
	public fileLoader() throws FileNotFoundException
	{
		CSVReader = new CSVFileReader(FileName);
	}
	public fileLoader(String inputFileName, char sep)throws FileNotFoundException
	{
		CSVReader = new CSVFileReader(inputFileName, sep);
	}
	public fileLoader(String inputFileName, char sep, char qual, String eol)throws FileNotFoundException
	{
		CSVReader = new CSVFileReader(inputFileName, sep,  qual, eol);
		
	}
	public CSVFileReader getCSVReader() {
		return CSVReader;
	}
	public void setCSVReader(CSVFileReader cSVReader) {
		CSVReader = cSVReader;
	}
	public String getFileName() {
		return FileName;
	}
	public void setFileName(String fileName) {
		FileName = fileName;
	}
	
	public Vector<String> getHeaders()throws IOException {
		if (headers == null){
			load();
		}
		return headers;
	}
	public Vector<Vector<String>> getGrid()throws IOException {
		if (grid == null){
			load();
		}
		return grid;
	}
	public int getColCount() {
		return colCount;
	}
	public int getRowCount(){
		return rowCount;
	}
	public Vector<String> getRow(int count) throws IOException
	{
		//return CSVReader.readFields(count);
		if (grid == null){
			load();
		}
		return grid.elementAt(count);
	}
	
	private void load() throws IOException
	{
		int count = 0;
		headers= CSVReader.readFields(count++);
		grid = new Vector<Vector<String>>();
	    colCount = headers.size();
		Vector<String> row = null;
		do{
			row = CSVReader.readFields(count++);
			grid.add(row);
		}while(row != null &&  count < 25);
		rowCount = count;
		CSVReader.close();
		
	}
}
