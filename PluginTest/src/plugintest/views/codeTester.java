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
import java.awt.Panel;
import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JButton;
import org.eclipse.swt.layout.GridLayout;

import fileMapper.fileMapperUtil.messaging.*;

import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class codeTester extends ViewPart {

	public static final String ID = "plugintest.views.codeTester"; //$NON-NLS-1$
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private JButton btnCode01 = null;
private Composite parent;
	public codeTester() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		this.parent = parent;
		Composite composite = new Composite(parent, SWT.EMBEDDED);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		composite.setLayout(new GridLayout(1, false));
		
		Frame frame = SWT_AWT.new_Frame(composite);
		
		Panel panel = new Panel();
	
		frame.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JRootPane rootPane = new JRootPane();
		panel.add(rootPane);
		{
			JPanel pnlMain = new JPanel();
			rootPane.getContentPane().add(pnlMain, BorderLayout.CENTER);
			{
				btnCode01 = new JButton("Test Code block 01");
				btnCode01.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Test01();
					}
				});
				pnlMain.add(btnCode01);
			
			}
			{
				JButton btnSendMessage = new JButton("Send Message");
				btnSendMessage.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						SendMessage();
					}
				});
				pnlMain.add(btnSendMessage);
			}
		}
		

		createActions();
		initializeToolBar();
		initializeMenu();
	}
	
	public void SendMessage()
	{
		FileMappingMessageEngine meSender = new FileMappingMessageEngine();
		meSender.test01();
		//meSender.sendQuery();
		//meSender.test04();
	}
	public void Test01()
	{
		JOptionPane.showMessageDialog(btnCode01, "HI");
		
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
