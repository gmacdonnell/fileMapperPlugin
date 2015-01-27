package plugintest.views;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;
import java.awt.Frame;
import org.eclipse.swt.awt.SWT_AWT;

import plugintest.fileLoad.fileLoader;
import plugintest.messaging.MessageEngine;
import plugintest.ui.ConceptPanel;


import java.awt.Dimension;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JRootPane;
import javax.swing.JPanel;

import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;

import edu.harvard.i2b2.eclipse.plugins.query.utils.Messages;


import javax.swing.JScrollPane;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ViewTest extends ViewPart {

	public static final String ID = "plugintest.views.ViewTest"; //$NON-NLS-1$
	private final FormToolkit formToolkit = new FormToolkit(
			Display.getDefault());
	private Vector<ConceptPanel> Concepts;

	private String fileToLoad = null;
	private JPanel panel_1 = null;

	
	private Text txtFileName;

	public ViewTest() {
		Concepts = new Vector<ConceptPanel>();

	}

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		final Composite viewParent = parent;
		parent.setLayout(new RowLayout(SWT.VERTICAL));
		{
			Composite composite_1 = new Composite(parent, SWT.NONE);
			composite_1.setLayoutData(new RowData(500, SWT.DEFAULT));
			formToolkit.adapt(composite_1);
			formToolkit.paintBordersFor(composite_1);

			txtFileName = new Text(composite_1, SWT.BORDER);
			txtFileName.setText("Choose a file to load");
			txtFileName.setEditable(false);
			txtFileName.setBounds(0, 0, 342, 26);
			formToolkit.adapt(txtFileName, true, true);

			Button btnFileToLoad = new Button(composite_1, SWT.NONE);
			btnFileToLoad.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					try {
						txtFileName.setText(fileFind());
						fill(panel_1);
						viewParent.redraw();
					} catch (Exception exp) {
						txtFileName.setText("failed to find file");
					}
				}
			});
			btnFileToLoad.setBounds(364, -2, 90, 30);
			formToolkit.adapt(btnFileToLoad, true, true);
			btnFileToLoad.setText("Load File");
		}

		Composite composite = new Composite(parent, SWT.EMBEDDED
				| SWT.DragDetect);
		composite.setLayoutData(new RowData(585, 428));

		formToolkit.adapt(composite);
		formToolkit.paintBordersFor(composite);
		composite.setLayout(new RowLayout(SWT.HORIZONTAL));

		Frame frame = SWT_AWT.new_Frame(composite);

		Panel panel = new Panel();
		frame.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JRootPane rootPane = new JRootPane();
		panel.add(rootPane);

		JScrollPane scrollPane = new JScrollPane();
		rootPane.getContentPane().add(scrollPane, BorderLayout.CENTER);

		panel_1 = new JPanel();
		panel_1.setSize(new Dimension(20, 20));

		scrollPane.setViewportView(panel_1);

		// ConceptPanel conceptPanel = new ConceptPanel();
		// conceptPanel.setView(this);
		// panel_1.add(conceptPanel);

		// fill(panel_1);

		createActions();
		initializeToolBar();
		initializeMenu();
	}

	public void sendQuery()
	{
		MessageEngine meTemp = new MessageEngine();
		meTemp.test01();
		meTemp.sendQuery();
	}
	private String fileFind() {
		FileDialog fd = new FileDialog(Display.getCurrent().getActiveShell(),
				SWT.OPEN);
		fd.setText(Messages.getString("ClientFolderView.Step1ButtonOpenText")); //$NON-NLS-1$
		String[] filterExt = { "*.*" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		fd.setFilterExtensions(filterExt);
		fileToLoad = fd.open();
		return fileToLoad;
	}

	private void fill(JPanel parent) {
		// Dimension size =parent.getSize();
		// size.setSize(size.getWidth()/columnCount, size.getHeight()) ;
		fileLoader loader;
		Vector<String> headers;

		try {
			loader = new fileLoader(fileToLoad, ',');
			try {
				headers = loader.getHeaders();
				int colCount = headers.size() - 1;
				for (int colCounter = 0; colCounter < colCount; colCounter++) {

					ConceptPanel concept = new ConceptPanel();
					concept.setPosition(colCounter);
					Concepts.add(concept);
					concept.setView(this);
					// concept.
					// concept.setPreferredSize(size);

					int rowMax = loader.getRowCount() - 1;
					concept.setTitle(headers.elementAt(colCounter));
					String[][] model = new String[rowMax][2];

					for (int rowCounter = 0; rowCounter < rowMax; rowCounter++) {

						model[rowCounter][0] = loader.getRow(rowCounter)
								.elementAt(colCounter);
					}
					concept.setModel(model);
					parent.add(concept);
				}
				parent.repaint(); //?
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}

		/*
		 * for(int count = 0; count < columnCount; count++) { ConceptPanel
		 * concept = new ConceptPanel(); concept.setView(this); //concept.
		 * //concept.setPreferredSize(size);
		 * 
		 * 
		 * int max = 100; concept.setTitle("Column "+ count + " of " +
		 * columnCount); String[][] model = new String[max][2]; String text
		 * =count + ":test-"; for(int i = 0; i< max; i++) {
		 * 
		 * model[i][0]= new String(text + i); } concept.setModel(model);
		 * parent.add(concept);
		 * 
		 * }
		 */

	}

	public void dispose() {

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