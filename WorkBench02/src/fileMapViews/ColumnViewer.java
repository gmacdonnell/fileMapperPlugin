package fileMapViews;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.util.LinkedList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import fileMapper.fileMapperUtil.IFileParser;
import fileMapper.fileMapperUtil.UI.ConceptDropHandler;
import fileMapper.fileMapperUtil.models.Concept;
import fileMapper.fileMapperUtil.models.DataImportModel;

public class ColumnViewer extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	protected IFileParser Parser;
	protected ConceptDropHandler Handler;
	public static int DISPLAYROWS=5;

	/**
	 * Create the panel.
	 */
	public ColumnViewer() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		table = new JTable(DISPLAYROWS,3);
		
		scrollPane.setViewportView(table);

	}
	public void fillTable(JTable table)
	{
		if(Parser != null)
		{
			DataImportModel model = new DataImportModel(Parser.getFieldCount());
			model.setData(Parser.parseFile());
			//model.setNames(getNames());
			
			table.setModel(model);
			
			//table.setTransferHandler(new TextHandler());
			Handler = new ConceptDropHandler();
			table.getTableHeader().setTransferHandler(Handler);
		}
	
	}
	public boolean Load()
	{
		
		fillTable(table); 
		return true;
	}
	public void setParser(IFileParser parser)
	{
		Parser = parser;
	}
	public IFileParser getParser()
	{
		return Parser;
	}
	
	
	public LinkedList<Concept> getLabledColumns()
	{
		return Handler.getLabledColumns();
	}
	

}
