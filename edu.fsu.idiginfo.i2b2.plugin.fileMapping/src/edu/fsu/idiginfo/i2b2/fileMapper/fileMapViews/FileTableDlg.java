package edu.fsu.idiginfo.i2b2.fileMapper.fileMapViews;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.ColumnData;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.ColumnMatch;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.DataFile;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.DataSource;
import edu.fsu.idiginfo.i2b2.fileMapper.fileMapperUtil.FileMapperUtil;
import edu.fsu.idiginfo.i2b2.fileMapper.fileMapperUtil.IFileParser;
import edu.fsu.idiginfo.i2b2.fileMapper.fileMapperUtil.models.Concept;
import edu.fsu.idiginfo.i2b2.fileMapper.fileMapperUtil.models.FileTableModel;

import java.awt.GridLayout;
import java.util.List;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JScrollPane;
import javax.swing.JCheckBox;

public class FileTableDlg extends JDialog {

	/**
	 * Show the file parsed via the parser into a table 
	 */
	private static final long serialVersionUID = -5303411874690216585L;
	private final JPanel contentPanel = new JPanel();
	protected String FilePath;
	protected IFileParser Parser;
	protected FileTableModel model;
	private JTable fileView;
	private int value;
	private JScrollPane scrollPane;
	private JCheckBox chckbxFirstRowIs;
	private JComboBox<FileParserPnl> DataTypes;
	private JButton btnLoadFile;
	private int result;



	/**
	 * Create the dialog.
	 */
	public FileTableDlg() {
		setBounds(100, 100, 591, 372);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{573, 0};
		gbl_contentPanel.rowHeights = new int[]{205, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		{
			scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 0;
			gbc_scrollPane.gridy = 0;
			contentPanel.add(scrollPane, gbc_scrollPane);
		}
		
		fileView = new JTable(model = new FileTableModel());
		model.enableTitleEdit(true);
	/*	GridBagConstraints gbc_fileView = new GridBagConstraints();
		gbc_fileView.insets = new Insets(0, 0, 5, 0);
		gbc_fileView.fill = GridBagConstraints.BOTH;
		gbc_fileView.gridx = 0;
		gbc_fileView.gridy = 0;
		contentPanel.add(fileView, gbc_fileView);
		*/
		scrollPane.setViewportView(fileView);
		fileView.setRowSelectionAllowed(false);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						value = AbsEditorDlg.OK;
						setVisible(false);
						dispose();
					}
				});
				{
					chckbxFirstRowIs = new JCheckBox("First Row Is Titles");
					chckbxFirstRowIs.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							model.setHasTitles(chckbxFirstRowIs.isSelected());
							model.fireTableStructureChanged();
						}
					});
					buttonPane.add(chckbxFirstRowIs);
				}
				{
					 DataTypes = new JComboBox<FileParserPnl>();
					 initDataTypes();
						DataTypes.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								FileParserPnl pnl = ((FileParserPnl)DataTypes.getSelectedItem());
								int result = pnl.showEditor();
								if(result == AbsEditorDlg.OK)
								{
									btnLoadFile.setEnabled(true);
								}
							}
						});
					 buttonPane.add(DataTypes);
				}
				{
					btnLoadFile = new JButton("Load File");
					btnLoadFile.setEnabled(false);
					btnLoadFile.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							JFileChooser dlg = new JFileChooser();
							//if(dlg.showOpenDialog(getParent()) == JOptionPane.OK_OPTION)
						//	{
							
							//File file = dlg.getSelectedFile();
								File file = new File("C:\\Projects\\PCOR\\ExportToI2b2\\testFile.csv");
							setFilePath(file.getAbsolutePath());
							IFileParser parser = ((FileParserPnl)DataTypes.getSelectedItem()).getParser();
							setParser(parser);
							load();
							
						//	}
						}
					});
					buttonPane.add(btnLoadFile);
				}
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						value = AbsEditorDlg.CANCEL;
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		value = AbsEditorDlg.CANCEL;
	}



	public void setFilePath(String filePath)
	{
		FilePath = filePath;
		this.setTitle(filePath);
	}
	
	public void load(){
		
		
		
		try{
			
			Parser.loadFile(FilePath);
			Parser.parseFile();
			clearTable();
			List<ColumnData> records = Parser.parseFile();
			fillTable(records);
			//this.refresh();
		
			value = AbsEditorDlg.OK;
		}
		catch(Exception e)
		{
			value = AbsEditorDlg.CANCEL;
			FileMapperUtil.ShowError(e);
		}
		
	
	}
	public void setRecordParser(IFileParser parser)
	{
		Parser=parser;
	
	}
	
	
	
	public void  fillTable( List<ColumnData> records) {
		
		
			model.addColumns(records);
	
		
	}
	
	public void clearTable()
	{
		model.removeAllColumns();
	
	}

	public void setParser(IFileParser parser)
	{
		this.Parser = parser;
	}
	public IFileParser getParser() {
		
		return Parser;
	}
	public JTable getTable()
	{
		return fileView;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public int showDialog()
	{
		this.setModal(true);
		this.setVisible(true);
		return value;
	}
	private void initDataTypes()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext( "applicationContext.xml"); 

		 @SuppressWarnings("unchecked")
		List<String> list = (List<String>) context.getBean("FileParserList"); 
		 for(String name : list)
		 {
			 IFileParser parser = (IFileParser)context.getBean(name);
			 FileParserPnl pnl = new FileParserPnl();
			 pnl.setParser(parser);
			 DataTypes.addItem(pnl);
			 
		 }
		 DataTypes.repaint();
	}
	public List<ColumnData> getColumns() {
	 return model.getColumns();
	}
	public DataSource getDataSource()
	{
		return model.getDataSource();
	}
	
	public DataFile getDataFile()
	{
		DataFile dataFile = Parser.getColumn().getSourceFile();
		if(chckbxFirstRowIs.isSelected())
		{
			dataFile.getNotes().set(FileTableModel.TITLESINDEX,FileTableModel.TITLES );
		}else
		{
			dataFile.getNotes().set(FileTableModel.TITLESINDEX,FileTableModel.NOTITLES );
		}
		return dataFile;
	}
}
