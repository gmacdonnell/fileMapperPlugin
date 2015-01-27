package fileMapper.workBench;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FillLayout;

public class TableTest extends SectionPart {
	private Table table;

	/**
	 * Create the SectionPart.
	 * @param parent
	 * @param toolkit
	 * @param style
	 */
	public TableTest(Composite parent, FormToolkit toolkit, int style) {
		super(parent, toolkit, style);
		createClient(getSection(), toolkit);
	}

	/**
	 * Fill the section.
	 */
	private void createClient(Section section, FormToolkit toolkit) {
		section.setText("Table Testing Section Part");
		Composite container = toolkit.createComposite(section);

		section.setClient(container);
		container.setLayout(new FormLayout());
		
		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(0, 279);
		fd_composite.right = new FormAttachment(0, 588);
		fd_composite.top = new FormAttachment(0, 10);
		fd_composite.left = new FormAttachment(0);
		composite.setLayoutData(fd_composite);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		TableViewer tableViewer = new TableViewer(composite, SWT.BORDER |SWT.MULTI | SWT.H_SCROLL
			      | SWT.V_SCROLL | SWT.FULL_SELECTION);
		//setColumns(tableViewer);
	//	load(tableViewer);
		//tableViewer.setContentProvider(new ArrayContentProvider());
		//tableViewer.setInput(getData());
	
		table = tableViewer.getTable();
	//	loadTable(table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		toolkit.paintBordersFor(table);
		//tableViewer.refresh();
	}
	
	public void loadTable(Table table)
	{
		int SAMPLES = 5;
		int COLUMNCOUNT = 3;
		List<String[]> Records = getData();
		TableItem[] Rows = new TableItem[SAMPLES];
		for(int index = 0; index < COLUMNCOUNT; index++)
		{
			TableColumn column = new TableColumn(table, SWT.NONE, index);
			column.setText("Column:"+index);
			column.setWidth(50);
		}
		
		//set rows
		for(int index = 0; index < SAMPLES; index++)
		{
			Rows[index]= new TableItem(table, SWT.NONE);
			for(int xindex = 0; xindex < COLUMNCOUNT; xindex++)
			{
				Rows[index].setText(xindex, Records.get(index)[xindex]);
			}
		}
		
	}
	
	public List<String[]> getData()
	{
		LinkedList<String[]>data = new LinkedList<String[]>();
		data.push(new String[]{"1","A","Alpha"});
		data.push(new String[]{"2","b","beta"});
		data.push(new String[]{"3","c","gama"});
		data.push(new String[]{"4","d","delta"});
		data.push(new String[]{"5","e","epsilon"});
		return data;
	}

	public void setColumns(TableViewer viewer)
	{
		for(int i = 0; i<5; i++)
		{
			TableViewerColumn col = new TableViewerColumn(viewer, SWT.None);
			org.eclipse.swt.widgets.TableColumn tcol = col.getColumn();
			final String lable = "COL:"+i;
			tcol.setText(lable);
			tcol.setWidth(200);
			tcol.setResizable(true);
			tcol.setMoveable(true);
			col.setLabelProvider(new ColumnLabelProvider(){
				public String getText(Object element)
				{
					if(element instanceof String)
					{
						return (String)element;
					}
					return null;
				}
			});
			
		}
	}
	

	public Table getTable()
	{
		return table;
	}
}
