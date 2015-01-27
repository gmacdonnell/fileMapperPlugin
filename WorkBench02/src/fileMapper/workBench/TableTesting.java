package fileMapper.workBench;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class TableTesting extends ApplicationWindow {
	private Table table;

	/**
	 * Create the application window.
	 */
	public TableTesting() {
		super(null);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 10, 494, 159);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		Button btnRun = new Button(container, SWT.NONE);
		btnRun.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				loadTable(table);
			}
		});
		btnRun.setBounds(87, 241, 84, 27);
		btnRun.setText("Run");

		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Create the menu manager.
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");
		return menuManager;
	}

	/**
	 * Create the toolbar manager.
	 * @return the toolbar manager
	 */
	@Override
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager toolBarManager = new ToolBarManager(style);
		return toolBarManager;
	}

	/**
	 * Create the status line manager.
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			TableTesting window = new TableTesting();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("New Application");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(537, 506);
	}
	
	public void loadTable(Table table)
	{
		clearTable(table);
		int SAMPLES = 5;
		int COLUMNCOUNT = 3;
		List<String[]> Records = getData();
		TableItem[] Rows = new TableItem[SAMPLES];
		for(int index = 0; index < COLUMNCOUNT; index++)
		{
			TableColumn column = new TableColumn(table, SWT.NONE, index);
			column.setText("Column:"+index);
			column.setWidth(100);
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
		data.add(new String[]{"1","A","Alpha"});
		data.add(new String[]{"2","b","beta"});
		data.add(new String[]{"3","c","gama"});
		data.add(new String[]{"4","d","delta"});
		data.add(new String[]{"5","e","epsilon"});
		return data;
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
}
