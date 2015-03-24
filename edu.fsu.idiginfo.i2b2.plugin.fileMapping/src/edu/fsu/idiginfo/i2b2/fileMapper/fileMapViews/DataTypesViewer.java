package edu.fsu.idiginfo.i2b2.fileMapper.fileMapViews;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.DataType;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.GetDataTypes;
import edu.fsu.idiginfo.i2b2.fileMapper.ws.FileMapperServiceDriver;

public class DataTypesViewer extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5599187428304445815L;
	GetDataTypes Types;
	FormLayout Layout;
	ColumnSpec[] ColSpecs;
	RowSpec[] RowSpecs;

	/**
	 * Create the panel.
	 */

	/**
	 * Create the panel.
	 */
	public DataTypesViewer() {
		ColSpecs = new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("60dlu:grow"), };
		RowSpecs = new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC, };
		Layout = new FormLayout(ColSpecs, RowSpecs);
		setLayout(Layout);
	}

	public void displayTypes() {

		if (Types == null) {
			requestDataTypes();
		}
		for (DataType type : Types.getTypes()) {
			addBox(type);
		}

	}

	@SuppressWarnings("restriction")
	private void addBox(DataType type) {
		JCheckBox checkBox = new JCheckBox(type.getName());
		Layout.appendRow(FormFactory.RELATED_GAP_ROWSPEC);
		Layout.appendRow(RowSpec.decode("8dlu:grow"));
		int index = Layout.getRowCount();
		String position = "2, " + index;
		add(checkBox, position);
	}
	
	public void requestDataTypes() {
		try {
			String results = FileMapperServiceDriver.getDataTypes();
			Types = FileMapperServiceDriver.extractTypes(results);
		} catch (Exception e) {
			showError(e);
		}
	}

	private void showError(Exception e) {
		Shell dialogShell = new Shell(Display.getDefault(), SWT.None);
		MessageBox dialog = new MessageBox(dialogShell, SWT.ICON_ERROR | SWT.OK);
		dialog.setText("DATA LOADING ERROR");
		dialog.setMessage(e.toString());
		dialog.open();
	}

}
