package plugintest.ui;

import org.eclipse.swt.widgets.Composite;
import swing2swt.layout.BoxLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.widgets.Button;

public class TestComp extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TestComp(Composite parent, int style) {
		super(parent, SWT.BORDER | SWT.EMBEDDED);
		setLayout(new BoxLayout(BoxLayout.X_AXIS));
		
		Group grpRequiredTerms = new Group(this, SWT.NONE);
		grpRequiredTerms.setText("Required Terms");
		
	//	DragSource dragSource = new DragSource(grpRequiredTerms, DND.DROP_MOVE);
		
		Button btnButton = new Button(this, SWT.NONE);
		btnButton.setText("button");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
