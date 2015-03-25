package edu.fsu.idiginfo.i2b2.fileMapper.fileMapViews;

import javax.swing.JPanel;

import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JScrollPane;

import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.ColumnMatch;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.DataSource;
import javax.swing.JMenuBar;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CombineColumnPnl extends JPanel {

	/**
	 * Match columns in the fromFile with columns in the existing data source. 
	 */
	private static final long serialVersionUID = 6283941296740086099L;
	private JPanel pnlMatches;
	private DataSource existing;
	
	/**
	 * Create the panel.
	 */
	public CombineColumnPnl(DataSource fromFile, DataSource existing) {
		this.existing = existing;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{539, 0};
		gridBagLayout.rowHeights = new int[]{25, 439, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.SOUTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		panel.setLayout(new GridLayout(0, 6, 0, 0));
		
		JSeparator separator_1 = new JSeparator();
		panel.add(separator_1);
		
		JLabel lblFromCurrentFile = new JLabel("From Current File");
		panel.add(lblFromCurrentFile);
		
		JSeparator separator_2 = new JSeparator();
		panel.add(separator_2);
		
		JLabel lblExistingFields = new JLabel("Existing Fields");
		panel.add(lblExistingFields);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
		pnlMatches = new JPanel();
		scrollPane.setViewportView(pnlMatches);
		pnlMatches.setLayout(new GridLayout(0, 1, 0, 0));
		pnlMatches.repaint();
	
		initMatches( fromFile,  existing);

	}
	
	public void close()
	{
		
	}
	private void initMatches(DataSource fromFile, DataSource existing)
	{
		for(int index = 0; index < fromFile.getColumns().size(); index ++)
		{
			MatchFieldsPnl newPanel = new MatchFieldsPnl(fromFile,existing, index);
			pnlMatches.add(newPanel);
			newPanel.repaint();
		}
	}
	
	public DataSource getDataSource()
	{
		//DataSource source = new DataSource();
		for(Component comp : pnlMatches.getComponents())
		{
			if(comp instanceof MatchFieldsPnl)
			{
				if(((MatchFieldsPnl) comp).include())
				{
					ColumnMatch match = ((MatchFieldsPnl)comp).getMatch();
					if(!existing.getColumns().contains(match))
					{
						existing.getColumns().add(match);
					}
				}
			}
		}
		return existing;
	}
	
	

}
