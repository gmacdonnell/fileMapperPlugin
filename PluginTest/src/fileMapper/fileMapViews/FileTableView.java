package fileMapViews;

import java.util.List;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.swt.layout.FillLayout;


import fileMapper.fileMapperUtil.IFileParser;
import fileMapper.fileMapperUtil.models.Concept;

public class FileTableView extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	protected String FilePath;
	protected IFileParser Parser;
	private Table table;
	public static int SAMPLES = 5;
	private int FieldCount;
private TableViewer tableViewer ;
private Concept[] Concepts;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public FileTableView(Composite parent, int style) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite textView = new Composite(this, SWT.NONE);
		textView.setLayout(new TableColumnLayout());
		toolkit.adapt(textView);
		toolkit.paintBordersFor(textView);
		
		tableViewer = new TableViewer(textView, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		toolkit.paintBordersFor(table);
		

	}


	/*protected void addGroupPanelGroupDragHandler()
	{
		GroupPanelGroupDragHandler groupDragHandler = new GroupPanelGroupDragHandler( this.myGroup );
		DragSource titleCompSource = new DragSource( titleComp, UIConst.DND_DRAG_OPS );
		titleCompSource.setTransfer( UIConst.DND_TRANSFER_TYPES );
		titleCompSource.addDragListener( groupDragHandler );
		
		DragSource titleLabelSource = new DragSource( titleLabel, UIConst.DND_DRAG_OPS );
		titleLabelSource.setTransfer( UIConst.DND_TRANSFER_TYPES );
		titleLabelSource.addDragListener( groupDragHandler );
	}
	*/
	public void setFilePath(String filePath)
	{
		FilePath = filePath;
	}
	
	public void load(){
		
		
		
		try{
			
			Parser.loadFile(FilePath);
			Parser.parseFile();
			FieldCount = Parser.getFieldCount();
			clearTable(getTable());
			List<String[]> records = Parser.parseFile();
			fillTable(getTable(),records);
			//this.refresh();
		
		
		}
		catch(Exception e)
		{

			Shell dialogShell = new Shell(Display.getDefault(),SWT.None);
			MessageBox dialog = new MessageBox(dialogShell, SWT.ICON_ERROR|SWT.OK);
			dialog.setText("DATA LOADING ERROR");
			dialog.setMessage(e.toString());
			dialog.open();
		}
		
	
	}
	public void setRecordParser(IFileParser parser)
	{
		Parser=parser;
	
	}
	
	public int getFieldCount()
	{
		return FieldCount;
	}
	public void refresh()
	{
		tableViewer.refresh();
	}
	
	public Table fillTable(Table table, List<String[]> records) {

		if(records != null)
		{
			
			int width = table.getParent().getBounds().width/getFieldCount();
			if (width < 50){width = 50;}
			Concepts = new Concept[getFieldCount()];
			for(int index = 0; index < getFieldCount(); index++)
			{
				TableColumn column = new TableColumn(table, SWT.NONE, index);
				column.setText("Column:"+index);
				column.setWidth(width);
				Concepts[index]= new Concept();
		
			}
			
			//set rows
			for(int index = 0; index < SAMPLES; index++)
			{
				TableItem row = new TableItem(table, SWT.NONE);
				for(int xindex = 0; xindex < getFieldCount(); xindex++)
				{
					row.setText(xindex, records.get(index)[xindex]);
				}
			}
			
		}
		
		return table;
	}
	
	public void clearTable(Table table)
	{
		while(table.getColumnCount() > 0)
		{
			table.getColumns()[0].dispose();
		}
		while(table.getItemCount() > 0)
		{
			table.getItems()[0].dispose();
		}
	
	}

	public IFileParser getParser() {
		// TODO Auto-generated method stub
		return Parser;
	}
	public Table getTable()
	{
		return table;
	}
}
