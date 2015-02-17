package edu.fsu.idiginfo.i2b2.fileMapper.fileMapViews;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Panel;

import javax.swing.JPanel;
import javax.swing.JRootPane;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;

public class ColumnMappingView extends ViewPart {

	public static final String ID = "edu.fsu.idiginfo.i2b2.fileMapper.fileMapViews.ColumnMappingView"; //$NON-NLS-1$
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Composite composite;
	private JPanel pnlMain;
	
	/**
	 * @wbp.nonvisual location=93,87
	 */
	private  DataTypesViewer dataTypesViewer;

	public ColumnMappingView() {
		setPartName("ColumnMapping");
		
	}
	private void initMain()
	{
		Frame frame = SWT_AWT.new_Frame(composite);
	
	Panel panel = new Panel();

	frame.add(panel);
	panel.setLayout(new BorderLayout(0, 0));
	
	JRootPane rootPane = new JRootPane();
	panel.add(rootPane);
	pnlMain = new JPanel();
	rootPane.getContentPane().add(pnlMain, BorderLayout.CENTER);
		
	}
	private void initTypes()
	{
		
		if(dataTypesViewer == null)
		{
			dataTypesViewer = new DataTypesViewer();
		}
		dataTypesViewer.displayTypes();
		pnlMain.add(dataTypesViewer);
	}
	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		 composite = toolkit.createComposite(parent, SWT.EMBEDDED);
		toolkit.paintBordersFor(composite);

		createActions();
		initializeToolBar();
		initializeMenu();
		initMain();
		initTypes();
	}

	public void dispose() {
		toolkit.dispose();
		super.dispose();
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager tbm = getViewSite().getActionBars().getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager manager = getViewSite().getActionBars().getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

}
