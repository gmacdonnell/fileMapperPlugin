package fileMapper.workBench;



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

import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class WorkBench extends ApplicationWindow {
	/**
	 * Create the application window.
	 */
	public WorkBench() {
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
		container.setLayout(new FormLayout());
		{
			Composite composite = new Composite(container, SWT.NONE);
			FormData fd_composite = new FormData();
			fd_composite.bottom = new FormAttachment(0, 537);
			fd_composite.right = new FormAttachment(0, 834);
			fd_composite.top = new FormAttachment(0, 10);
			fd_composite.left = new FormAttachment(0);
			composite.setLayoutData(fd_composite);
			composite.setLayout(new GridLayout(2, false));
			FormToolkit formToolkit = new FormToolkit(Display.getCurrent());
			
			
			
		
			new Label(composite, SWT.NONE);
		//	FileFinder fileFinder = new FileFinder(composite, formToolkit, 0);
			
			
			
			
		
			
			
			
		
			new Label(composite, SWT.NONE);
			new Label(composite, SWT.NONE);
							
			Button btnRun = new Button(composite, SWT.NONE);
			formToolkit.adapt(btnRun, true, true);
			btnRun.setText("Run");
			btnRun.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
				//	textFileVw.getParser().setDelimiters(recordParser.getDelimiters());
				//	textFileVw.load();
				
					//fileTableView.setFilePath(fileFinder.getPath());
					//fileTableView.load();
				}
	
			});
		}

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
			WorkBench window = new WorkBench();
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
		newShell.setText("File Map WorkBench");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(852, 658);
	}
}
