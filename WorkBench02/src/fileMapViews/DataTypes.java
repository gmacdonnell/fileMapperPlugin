package fileMapViews;

import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import fileMapper.data.DataType;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.jgoodies.forms.factories.FormFactory;

public class DataTypes extends JPanel {
 FormLayout Layout;
 ColumnSpec[] ColSpecs;
 RowSpec[]	RowSpecs;
	/**
	 * Create the panel.
	 */
	public DataTypes() {
	ColSpecs= new ColumnSpec[]{
			FormFactory.RELATED_GAP_COLSPEC,
			ColumnSpec.decode("40dlu:grow"),};
	RowSpecs=new RowSpec[] {	FormFactory.RELATED_GAP_ROWSPEC,
			FormFactory.DEFAULT_ROWSPEC,};
		Layout = new FormLayout  (ColSpecs,RowSpecs);
		setLayout(Layout);
		/*
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,});
		
		*/
		
		
		testAdd(15);
	

	}
	
	private void testAdd(int count)
	{
		for(int index = 1; index < count ; index++)
		{
			DataType temp = new DataType();
			temp.setName("Type:"+index);
			
			addBox(temp, index);
		}
		
		
		
		
	}
	
	private void addBox(DataType type, int index)
	{
		
		JCheckBox checkBox = new JCheckBox(type.getName());
		String position = "2, "+ (index*2);
		// Layout.insertRow(index+1, FormFactory.RELATED_GAP_ROWSPEC);
		Layout.appendRow(FormFactory.RELATED_GAP_ROWSPEC);
		// Layout.insertRow(index+2, RowSpec.decode("8dlu:grow"));
		 Layout.appendRow( RowSpec.decode("8dlu:grow"));
		add(checkBox, position);
	}

}
