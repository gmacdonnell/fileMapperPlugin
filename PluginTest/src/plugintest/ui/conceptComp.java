package plugintest.ui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Panel;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;



import javax.swing.JRootPane;

import org.eclipse.swt.layout.FillLayout;

public class conceptComp extends Composite {
	
	private ConceptPanel conceptPanel;
	private int position;
	/**
	 * @wbp.nonvisual location=58,74
	 */
	//private  ConceptPanel conceptPanel = null;
	/**
	

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public conceptComp(Composite parent, int style) {
		super(parent, style);
		
		
		createContents(parent);
		
		
	}
	public conceptComp(Composite parent, int style, int position)
	{
		super( parent, style);
		createContents(parent);
		setPosition(position);
	}
	public void setPosition(int position)
	{
		this.position = position;
	}
	public int getPosition(){
		return this.position;
	}
	protected Control createContents(Composite parent) {
		setLayout(new FillLayout(SWT.HORIZONTAL));
		Composite composite = new Composite(this, SWT.EMBEDDED);
		
		Frame frame = SWT_AWT.new_Frame(composite);
		
		Panel panel = new Panel();
		frame.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JRootPane rootPane = new JRootPane();
		panel.add(rootPane);
		
		conceptPanel = new ConceptPanel();
		rootPane.getContentPane().add(conceptPanel, BorderLayout.CENTER);
		
		return parent;
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	public void setTitle(String title){
		 conceptPanel.setTitle(title);
	}
	public void setModel(Object model[][]){
		 conceptPanel.setModel(model);
	}
	
}
