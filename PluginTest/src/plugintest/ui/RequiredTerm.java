package plugintest.ui;

public class RequiredTerm {
	
	private String column_name;
	private String table_name;
	
	public RequiredTerm(String column, String table)
	{
		this.column_name = column;
		this.table_name = table;
	}
	
	public String getColumn_name() {
		return column_name;
	}

	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}



}
