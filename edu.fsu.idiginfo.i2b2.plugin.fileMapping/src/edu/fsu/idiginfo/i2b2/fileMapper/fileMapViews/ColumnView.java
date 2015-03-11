package edu.fsu.idiginfo.i2b2.fileMapper.fileMapViews;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.Column;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ColumnView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5747552307035951539L;
	private JTextField txtName;
	protected Column column;
	private JTextField txtIndex;

	/**
	 * Create the panel.
	 */
	public ColumnView(Column col) {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{133, 60, 0};
		gridBagLayout.rowHeights = new int[]{32, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		txtName = new JTextField();
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.fill = GridBagConstraints.BOTH;
		gbc_txtName.insets = new Insets(0, 0, 0, 5);
		gbc_txtName.gridx = 0;
		gbc_txtName.gridy = 0;
		add(txtName, gbc_txtName);
		txtName.setColumns(10);
		
		txtIndex = new JTextField();
		GridBagConstraints gbc_txtIndex = new GridBagConstraints();
		gbc_txtIndex.fill = GridBagConstraints.BOTH;
		gbc_txtIndex.gridx = 1;
		gbc_txtIndex.gridy = 0;
		add(txtIndex, gbc_txtIndex);
		txtIndex.setColumns(10);
		setColumn(col);

	}

	public int getIndex()
	{
		return column.getIndex();
	}
	public void setIndex(int value)
	{
		txtIndex.setText(new Integer(value).toString());
		column.setIndex(value);
	}
	
	
	public String getName()
	{
		return column.getName();
	}

	public void setName(String Name) {
		this.txtName.setText(Name);
		column.setName(Name);
	}

	public Column getColumn() {
		return column;
	}

	public void setColumn(Column column) {
		this.column = column;
		setIndex(column.getIndex());
		setName(column.getName());
	}
	
	public String toString()
	{
		return getName();
	}

}
