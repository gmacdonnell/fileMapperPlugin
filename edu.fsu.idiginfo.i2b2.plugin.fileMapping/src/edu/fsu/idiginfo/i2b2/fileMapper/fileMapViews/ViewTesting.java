package edu.fsu.idiginfo.i2b2.fileMapper.fileMapViews;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;



import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.DataSource;

public class ViewTesting extends ViewPart {

	public static final String ID = "edu.fsu.idiginfo.i2b2.fileMapper.fileMapViews.ViewTesting"; //$NON-NLS-1$
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Table tblDataSources;
	private ArrayList<DataSource> sources;

	public ViewTesting() {
		sources = new ArrayList<DataSource>();
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = toolkit.createComposite(parent, SWT.NONE);
		toolkit.paintBordersFor(container);
		container.setLayout(new GridLayout(1, false));
		{
			tblDataSources = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
			tblDataSources.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
			toolkit.adapt(tblDataSources);
			toolkit.paintBordersFor(tblDataSources);
			tblDataSources.setHeaderVisible(true);
			tblDataSources.setLinesVisible(true);
		}
		
		Composite comControls = new Composite(container, SWT.NONE);
		comControls.setBounds(0, 0, 64, 64);
		toolkit.adapt(comControls);
		toolkit.paintBordersFor(comControls);
		
		Button btnAddData = new Button(comControls, SWT.NONE);
		btnAddData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
			ImportFileDlg importFile = new ImportFileDlg();
			DataSource source = importFile.showDialog();
				if(source != null)
				{
					//todo handle this
					/*
					 * will show table of configured data sources with button to load when loading a status display will be visible. 
					 */
				}
				
			}
		});
		btnAddData.setBounds(0, 10, 122, 27);
		toolkit.adapt(btnAddData, true, true);
		btnAddData.setText("Add Data Source");

		createActions();
		initializeToolBar();
		initializeMenu();
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
