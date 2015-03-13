package edu.fsu.idiginfo.i2b2.fileMapper.fileMapViews;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
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

import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.ColumnData;
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

public class FileTableDlg extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5303411874690216585L;
	private final JPanel contentPanel = new JPanel();
	protected String FilePath;
	protected IFileParser Parser;
	protected FileTableModel model;
	private JTable fileView;
	private JComboBox<IFileParser> bxFileType;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FileTableDlg dialog = new FileTableDlg();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
		gbl_contentPanel.rowHeights = new int[]{40, 205, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JPanel hdrPanel = new JPanel();
			GridBagConstraints gbc_hdrPanel = new GridBagConstraints();
			gbc_hdrPanel.insets = new Insets(0, 0, 5, 0);
			gbc_hdrPanel.fill = GridBagConstraints.BOTH;
			gbc_hdrPanel.gridx = 0;
			gbc_hdrPanel.gridy = 0;
			contentPanel.add(hdrPanel, gbc_hdrPanel);
			hdrPanel.setLayout(new GridLayout(0, 2, 0, 0));
			{
				bxFileType = new JComboBox<IFileParser>();
				bxFileType.setToolTipText("Select a File Type");
				initFileType();
				bxFileType.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Parser =(IFileParser) bxFileType.getSelectedItem();
					}
				});
				hdrPanel.add(bxFileType);
			}
			{
				JButton btnLoad = new JButton("Load File");
				btnLoad.setEnabled(false);
				btnLoad.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try{
						Parser.loadFileShort(FilePath);
						fillTable(Parser.parseFile());
						
						}catch(Exception e)
						{
							FileMapperUtil.ShowError(e);
						}
					}
				});
				hdrPanel.add(btnLoad);
			}
		}
		{
			fileView = new JTable(model = new FileTableModel());
			GridBagConstraints gbc_fileView = new GridBagConstraints();
			gbc_fileView.fill = GridBagConstraints.BOTH;
			gbc_fileView.gridx = 0;
			gbc_fileView.gridy = 1;
			contentPanel.add(fileView, gbc_fileView);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}


	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	private void initFileType()
	{
		
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
		
		
		}
		catch(Exception e)
		{

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
	
	
}
