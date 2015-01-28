package fileMapper.fileMapperUtil.models;

public class Concept {
	private String TableCD;
	private String FullName;
	private String Name;
	private String FactTableColumn;
	private String ColumnName;
	private String ColumnDataType;
	private int SourceIndex;

	
	
	public Concept()
	{
		TableCD="";
		FullName="";
	}
	public Concept(String tableCD, String fullName) {

		TableCD = tableCD;
		FullName = fullName;
	}
	
	public String getFactTableColumn() {
		return FactTableColumn;
	}
	public void setFactTableColumn(String factTableColumn) {
		FactTableColumn = factTableColumn;
	}
	public String getColumnName() {
		return ColumnName;
	}
	public void setColumnName(String columnName) {
		ColumnName = columnName;
	}
	public String getColumnDataType() {
		return ColumnDataType;
	}
	public void setColumnDataType(String columnDataType) {
		ColumnDataType = columnDataType;
	}
	
	public int getColum() {
		return SourceIndex;
	}
	public void setColum(int column) {
		SourceIndex = column;
	}
	public String getTableCD() {
		return TableCD;
	}
	public void setTableCD(String tableCD) {
		TableCD = tableCD;
	}
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	
	public String getName()
	{
		return Name;
	}
	public void setName(String name)
	{
		Name= name;
	}

}
