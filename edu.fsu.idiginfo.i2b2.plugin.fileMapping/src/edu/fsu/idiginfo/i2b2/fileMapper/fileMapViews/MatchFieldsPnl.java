package edu.fsu.idiginfo.i2b2.fileMapper.fileMapViews;

import javax.swing.JPanel;

import java.awt.Component;
import java.awt.GridBagLayout;
import javax.swing.JCheckBox;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;



import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.Column;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.ColumnMatch;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.DataSource;
import edu.fsu.idiginfo.i2b2.fileMapper.fileMapperUtil.models.ColumnUtil;

import java.awt.Insets;
import java.util.List;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MatchFieldsPnl extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7973313718711200485L;
	private int index;
	protected JComboBox<ColumnView> FileField;
	protected JComboBox<ColumnMatchView> ExistingField;
	private JCheckBox cbMatch;
	private JCheckBox cbInclude;
	/**
	 * Create the panel.
	 */
	public MatchFieldsPnl(DataSource fromFile, DataSource existing, int index) {
		this.index = index;
		setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 75, 75, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		cbInclude = new JCheckBox("Include");
		cbInclude.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbMatch.setEnabled(cbInclude.isSelected());
				FileField.setEnabled(cbInclude.isSelected());
				
			}
		});
		GridBagConstraints gbc_cbInclude = new GridBagConstraints();
		gbc_cbInclude.insets = new Insets(0, 0, 0, 5);
		gbc_cbInclude.gridx = 0;
		gbc_cbInclude.gridy = 0;
		add(cbInclude, gbc_cbInclude);
		
		cbMatch = new JCheckBox("Match");
		cbMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					ExistingField.setEnabled(cbMatch.isSelected());
					if(cbMatch.isSelected())
					{
						ExistingField.setSelectedIndex(findMatch());					}
				
			}
		});
		cbMatch.setEnabled(false);
		GridBagConstraints gbc_cbMatch = new GridBagConstraints();
		gbc_cbMatch.insets = new Insets(0, 0, 0, 5);
		gbc_cbMatch.gridx = 1;
		gbc_cbMatch.gridy = 0;
		add(cbMatch, gbc_cbMatch);
		
		FileField = new JComboBox<ColumnView>();
		FileField.setEnabled(false);
		GridBagConstraints gbc_FileField = new GridBagConstraints();
		gbc_FileField.fill = GridBagConstraints.HORIZONTAL;
		gbc_FileField.insets = new Insets(0, 0, 0, 5);
		gbc_FileField.gridx = 2;
		gbc_FileField.gridy = 0;
		add(FileField, gbc_FileField);
		
		ExistingField = new JComboBox<ColumnMatchView>();
		ExistingField.setEnabled(false);
		GridBagConstraints gbc_ExistingField = new GridBagConstraints();
		gbc_ExistingField.fill = GridBagConstraints.HORIZONTAL;
		gbc_ExistingField.gridx = 3;
		gbc_ExistingField.gridy = 0;
		add(ExistingField, gbc_ExistingField);
		fillColumnCombo(FileField, fromFile);
		fillMatchCombo(ExistingField, existing);
		FileField.setSelectedIndex(index);

	}
	private void fillMatchCombo(JComboBox<ColumnMatchView> box, DataSource source)
	{
		List<ColumnMatch> columns = source.getColumns();
		for(ColumnMatch colMatch : columns)
		{
			box.addItem(new ColumnMatchView(colMatch));
		}
		box.repaint();
	}
	private void fillColumnCombo(JComboBox<ColumnView> box, DataSource source)
	{
		List<ColumnMatch> columns = source.getColumns();
		for(ColumnMatch colMatch : columns)
		{
			Column col = ColumnUtil.getLast(colMatch);
			box.addItem(new ColumnView(col));
		}
		box.repaint();
	}
	public void setColumntoMatch(int index)
	{
		FileField.setSelectedIndex(index);
	}
	
	public ColumnMatch getMatch()
	{
		ColumnMatch match;
		
		if(cbMatch.isSelected())
		{
			match = ((ColumnMatchView)ExistingField.getSelectedItem()).getColumnMatch();
			
		}else
		{
			match = new ColumnMatch();
			
		}
		match.getColumns().add(((ColumnView)FileField.getSelectedItem()).getColumn());
		return match;
	}
	
	private int findMatch()
	{
		ColumnView toMatch = (ColumnView)FileField.getSelectedItem();

		for(int index = 0 ; index < ExistingField.getItemCount(); index++)
		{
			ColumnMatchView cmv = ExistingField.getItemAt(index);
			if(cmv.isMatch(toMatch.getName()))
			{
				return index;
			}
		}
		return 0;
	}

	public int getIndex()
	{
		return index;
	}
}
