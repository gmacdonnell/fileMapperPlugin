package edu.fsu.idiginfo.i2b2.fileMapper.fileMapViews;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JTable;

import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.DataField;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.DataFile;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.DataSource;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.DataType;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.DataTypeField;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.GetDataTypes;
import edu.fsu.idiginfo.i2b2.fileMapper.fileMapperUtil.models.ColumnTableModel;
import edu.fsu.idiginfo.i2b2.fileMapper.fileMapperUtil.models.TypeColumnModel;
import edu.fsu.idiginfo.i2b2.fileMapper.ws.FileMapperServiceDriver;

import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.Box;

public class TypeSourceMap extends JPanel {
	/**
	 * map file from a data source to datatypes from i2b2
	 * 
	 */
	private static final long serialVersionUID = -3755640751213165068L;
	private JTable tblFields;
	private JTable tblDataColumns;
	protected ColumnTableModel sourceModel;
	protected TypeColumnModel fieldModel; 
	private JComboBox<DataTypeView> DataTypes;
	private JButton btnAddFile;
	private ArrayList<DataFile> files;
	private JScrollPane scrollPaneFields;
	private JScrollPane scrollPaneData;
	private Component horizontalStrut;
	private boolean isBuilt;
	

	/**
	 * Create the frame.
	 */
	public TypeSourceMap() {
		//setResizable(true);
		//setMaximizable(true);
		setBounds(100, 100, 507, 480);
		GridBagLayout gridBagLayout_1 = new GridBagLayout();
		gridBagLayout_1.columnWidths = new int[]{507, 0};
		gridBagLayout_1.rowHeights = new int[]{43, 240, 0};
		gridBagLayout_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout_1.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout_1);
		
		JPanel pnlHead = new JPanel();
		//getContentPane().
		GridBagConstraints gbc_pnlHead = new GridBagConstraints();
		gbc_pnlHead.fill = GridBagConstraints.BOTH;
		gbc_pnlHead.insets = new Insets(0, 0, 5, 0);
		gbc_pnlHead.gridx = 0;
		gbc_pnlHead.gridy = 0;
		add(pnlHead, gbc_pnlHead);
		GridBagLayout gbl_pnlHead = new GridBagLayout();
		gbl_pnlHead.columnWidths = new int[]{0, 84, 168, 96, 0, 0};
		gbl_pnlHead.rowHeights = new int[]{25, 0};
		gbl_pnlHead.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_pnlHead.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		pnlHead.setLayout(gbl_pnlHead);
		
		horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 0;
		pnlHead.add(horizontalStrut, gbc_horizontalStrut);
		
		JLabel lblDataType = new JLabel("Data Type");
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0, 0, 0, 5);
		gbc.gridx = 1;
		gbc.gridy = 0;
		pnlHead.add(lblDataType, gbc);
		
		DataTypes = new JComboBox<DataTypeView>();
		isBuilt = false;
		initDataTypes();
		DataTypes.setSelectedIndex(-1);
		DataTypes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isBuilt)
				{
				//TODO what should be done here
				DataTypeView typeView = (DataTypeView)DataTypes.getSelectedItem();
				DataType type = typeView.getDataType();
				showFields(type);
				
					btnAddFile.setEnabled(true);
				}
			}
		});
		
		GridBagConstraints gbc_1 = new GridBagConstraints();
		gbc_1.fill = GridBagConstraints.BOTH;
		gbc_1.insets = new Insets(0, 0, 0, 5);
		gbc_1.gridx = 2;
		gbc_1.gridy = 0;
		pnlHead.add(DataTypes, gbc_1);
		
		btnAddFile = new JButton("Add File");
		btnAddFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FileTableDlg fileTable = new FileTableDlg();
				int result = fileTable.showDialog();
				if(result == AbsEditorDlg.OK)
				{
					
				
					//TODO add code to handle the file 
					DataSource source = fileTable.getDataSource() ;
					if(doMatching(source))
					{
					
						sourceModel.setDataSource(source);
						files.add(fileTable.getDataFile());
					}
				}
				
		
			}
		});
		btnAddFile.setEnabled(true);
		GridBagConstraints gbc_2 = new GridBagConstraints();
		gbc_2.insets = new Insets(0, 0, 0, 5);
		gbc_2.fill = GridBagConstraints.BOTH;
		gbc_2.gridx = 3;
		gbc_2.gridy = 0;
		pnlHead.add(btnAddFile, gbc_2);
		
		JPanel pnlBody = new JPanel();
		//getContentPane().
		GridBagConstraints gbc_pnlBody = new GridBagConstraints();
		gbc_pnlBody.fill = GridBagConstraints.BOTH;
		gbc_pnlBody.gridx = 0;
		gbc_pnlBody.gridy = 1;
		add(pnlBody, gbc_pnlBody);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{50, 50, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		pnlBody.setLayout(gridBagLayout);
		
		tblFields = new JTable();
		tblFields.setRowSelectionAllowed(false);
		tblFields.setCellSelectionEnabled(true);
		tblFields.setColumnSelectionAllowed(true);
		
			
			
			tblDataColumns = new JTable(sourceModel = new ColumnTableModel());
			tblDataColumns.setFillsViewportHeight(true);
			
			
			scrollPaneFields = new JScrollPane();
			GridBagConstraints gbc_scrollPaneFields = new GridBagConstraints();
			gbc_scrollPaneFields.insets = new Insets(0, 0, 5, 0);
			gbc_scrollPaneFields.fill = GridBagConstraints.BOTH;
			gbc_scrollPaneFields.gridx = 0;
			gbc_scrollPaneFields.gridy = 0;
			pnlBody.add(scrollPaneFields, gbc_scrollPaneFields);
			scrollPaneFields.setViewportView(tblFields);
			scrollPaneData = new JScrollPane();
			GridBagConstraints gbc_scrollPaneData = new GridBagConstraints();
			gbc_scrollPaneData.fill = GridBagConstraints.BOTH;
			gbc_scrollPaneData.gridx = 0;
			gbc_scrollPaneData.gridy = 1;
			pnlBody.add(scrollPaneData, gbc_scrollPaneData);
			scrollPaneData.setViewportView(tblDataColumns);
		files=new ArrayList<DataFile>();
		
		
	}
	private void showFields(DataType type)
	{
		fieldModel = new TypeColumnModel();
		tblFields.setModel(fieldModel);
		try {
			GetDataTypes gdt = new GetDataTypes();
			gdt.getTypes().add(type);
			String types = FileMapperServiceDriver.getKeys(gdt);
			DataType fields = FileMapperServiceDriver.extractTypeFields(types);
			ArrayList<DataField>list = new ArrayList<DataField>();
			for(DataTypeField field : fields.getFieldSet())
			{
				DataField current = new DataField();
				current.setType(fields);
				current.setField(field);
				list.add(current);				
			}
			fieldModel.addColumns(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showInternalMessageDialog(null, "Error loading data Types see log for details");
			
		}
	}
	private void initDataTypes()
	{
	
	
		try {
			String types = FileMapperServiceDriver.getDataTypes();
			GetDataTypes feilds = FileMapperServiceDriver.extractTypes(types);
			for(DataType type : feilds.getTypes())
			{
				DataTypes.addItem( new DataTypeView(type));
				
			}
			isBuilt = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showInternalMessageDialog(null, "Error loading data Types see log for details");
			
		}
		
	}
	private boolean doMatching(DataSource source)
	{
		if(files.size()>0)
		{
			DataSource current = sourceModel.getDataSource();
			CombineColumnDlg ccd = new CombineColumnDlg(source,current);
			if(ccd.showDialog()==AbsEditorDlg.OK)
			{
				sourceModel.setDataSource(ccd.getDataSource());
			}
			return false;
		}
		
		return true;
	}
	
	public DataSource getDataSource()
	{
		if(sourceModel != null)
		{
			return sourceModel.getDataSource();
		}
		return null;
	}
	
}
