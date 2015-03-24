package edu.fsu.idiginfo.i2b2.fileMapper.fileMapViews;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.ColumnData;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.DataFile;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.DataSource;
import edu.fsu.idiginfo.i2b2.fileMapper.fileMapperUtil.IFileParser;
import edu.fsu.idiginfo.i2b2.fileMapper.fileMapperUtil.models.ColumnTableModel;
import edu.fsu.idiginfo.i2b2.fileMapper.fileMapperUtil.models.FileTableModel;

public class TypeSourceMap extends JInternalFrame {
	/**
	 * map file from a data source to datatypes from i2b2
	 * 
	 */
	private static final long serialVersionUID = -3755640751213165068L;
	private JTable tblFields;
	private JTable tblDataColumns;
	private ColumnTableModel sourceModel;
	private JComboBox<FileParserPnl> DataTypes;
	private JButton btnAddFile;
	private JButton btnShowFiles;
	private ArrayList<DataFile> files;
	

	/**
	 * Create the frame.
	 */
	public TypeSourceMap() {
		setResizable(true);
		setMaximizable(true);
		setBounds(100, 100, 507, 386);
		
		JPanel pnlHead = new JPanel();
		getContentPane().add(pnlHead, BorderLayout.NORTH);
		GridBagLayout gbl_pnlHead = new GridBagLayout();
		gbl_pnlHead.columnWidths = new int[]{84, 168, 96, 0, 0};
		gbl_pnlHead.rowHeights = new int[]{25, 0};
		gbl_pnlHead.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_pnlHead.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		pnlHead.setLayout(gbl_pnlHead);
		
		JLabel lblDataType = new JLabel("Data Type");
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0, 0, 0, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		pnlHead.add(lblDataType, gbc);
		
		DataTypes = new JComboBox<FileParserPnl>();
		initDataTypes();
		DataTypes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileParserPnl pnl = ((FileParserPnl)DataTypes.getSelectedItem());
				int result = pnl.showEditor();
				
				if(result == AbsEditorDlg.OK )
				{
					btnAddFile.setEnabled(true);
				}
			}
		});
		
		GridBagConstraints gbc_1 = new GridBagConstraints();
		gbc_1.fill = GridBagConstraints.BOTH;
		gbc_1.insets = new Insets(0, 0, 0, 5);
		gbc_1.gridx = 1;
		gbc_1.gridy = 0;
		pnlHead.add(DataTypes, gbc_1);
		
		btnAddFile = new JButton("Add File");
		btnAddFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FileTableDlg fileTable = new FileTableDlg();
				int result = fileTable.showDialog();
				if(result == AbsEditorDlg.OK)
				{
					
					btnShowFiles.setEnabled(true);
					//TODO add code to handle the file 
					DataSource source = fileTable.getDataSource() ;
					if(files.size()>0)
					{
						
					}
					sourceModel.setDataSource(source);
					files.add(fileTable.getDataFile());
				}
				
		
			}
		});
		btnAddFile.setEnabled(true);
		GridBagConstraints gbc_2 = new GridBagConstraints();
		gbc_2.insets = new Insets(0, 0, 0, 5);
		gbc_2.fill = GridBagConstraints.BOTH;
		gbc_2.gridx = 2;
		gbc_2.gridy = 0;
		pnlHead.add(btnAddFile, gbc_2);
		
		btnShowFiles = new JButton("Show Files");
		btnShowFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnShowFiles.setEnabled(false);
		GridBagConstraints gbc_btnShowFiles = new GridBagConstraints();
		gbc_btnShowFiles.gridx = 3;
		gbc_btnShowFiles.gridy = 0;
		pnlHead.add(btnShowFiles, gbc_btnShowFiles);
		
		JPanel pnlBody = new JPanel();
		getContentPane().add(pnlBody, BorderLayout.CENTER);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{40, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		pnlBody.setLayout(gridBagLayout);
		
		tblFields = new JTable();
		tblFields.setRowSelectionAllowed(false);
		tblFields.setCellSelectionEnabled(true);
		tblFields.setColumnSelectionAllowed(true);
	
		GridBagConstraints gbc_tblFields = new GridBagConstraints();
		gbc_tblFields.insets = new Insets(0, 0, 5, 0);
		gbc_tblFields.fill = GridBagConstraints.BOTH;
		gbc_tblFields.gridx = 0;
		gbc_tblFields.gridy = 0;
		pnlBody.add(tblFields, gbc_tblFields);
		
		tblDataColumns = new JTable();
		sourceModel = new ColumnTableModel();
		tblDataColumns.setModel(sourceModel);
		GridBagConstraints gbc_tblDataColumns = new GridBagConstraints();
		gbc_tblDataColumns.fill = GridBagConstraints.BOTH;
		gbc_tblDataColumns.gridx = 0;
		gbc_tblDataColumns.gridy = 1;
		pnlBody.add(tblDataColumns, gbc_tblDataColumns);
		files=new ArrayList<DataFile>();
	}
	private void initDataTypes()
	{
		//TODO create code to message db for data types
	}
	
}
