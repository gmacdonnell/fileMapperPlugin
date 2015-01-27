package fileMapper.fileMapperUtil.models;



import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class DataImportModel implements TableModel {
	protected String[] Names;
	protected String[] TableCDs;
	protected List<String[]> Data;
	private int ColumnCount;
	
	public DataImportModel(int columnCount)
	{
		ColumnCount = columnCount;
		Names = new String[ColumnCount];
		initNames();
		TableCDs= new String[ColumnCount];
	}
	@Override
	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public Class<?> getColumnClass(int index) {
		return getValueAt(0,index).getClass();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return Names.length;
	}

	@Override
	public String getColumnName(int index) {
		return Names[index];
	}
	public void setColumnName(int index, String value)
	{
		Names[index]=value;
	}

	private void initNames()
	{
	
		for(int index = 1; index <= getColumnCount(); index++)
		{
			String name = "Col:";
			if(index < 10){
				name = name+"0"+index;
			}
			else{
				name=name+index;
			}
			Names[index-1] = name;
		}
	
	}
	public String[] getNames()
	{ 
		
		return Names;
	}
	public void setNames(String[] names)
	{
		Names = names;
	}
	@Override
	public int getRowCount() {
		return  Data.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		return Data.get(row)[column];
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setValueAt(Object input, int row, int column) {
		if(input instanceof String)
		{
			if(row == Data.size())
			{
				Data.add(new String[ColumnCount]);
			}
			Data.get(row)[column]=(String)input;
		}

	}
	public void setData(List<String[]> data)
	{
		Data = data;
	}
	public List<String[]> getData()
	{
		return Data;
	}
	public void addRecord(String[] record)
	{
		Data.add(record);
	}

}
