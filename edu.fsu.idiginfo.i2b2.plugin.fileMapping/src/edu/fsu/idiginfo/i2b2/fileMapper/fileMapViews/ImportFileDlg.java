package edu.fsu.idiginfo.i2b2.fileMapper.fileMapViews;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.DataSource;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class ImportFileDlg extends JDialog {

	
	/**
	 * Display the typeSourceMapping windows
	 * has an arraylist of datasources;
	 */
	private static final long serialVersionUID = -6010693989489708891L;
	private int result;
	protected TypeSourceMap typeSourceMap;
	

	/**
	 * Launch the application.
	*/
	public static void main(String[] args) {
		try {
			ImportFileDlg dialog = new ImportFileDlg();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 /**/
	/**
	 * Create the dialog.
	 */
	public ImportFileDlg() {
		setTitle("Import File Data to I2B2 Types");
		setBounds(100, 100, 685, 617);
		getContentPane().setLayout(new BorderLayout());
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
						result = AbsEditorDlg.OK;
						dispose();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
						result = AbsEditorDlg.CANCEL;
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		{
			typeSourceMap = new TypeSourceMap();
			getContentPane().add(typeSourceMap, BorderLayout.CENTER);
		}
		init();
	}
private void init()
{
	result = AbsEditorDlg.CANCEL; 
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	
}
	
	
	public DataSource showDialog()
	{
	//	setModal(true);
		setVisible(true);
		if(result == AbsEditorDlg.OK)
		{
			return getSource();
		}
		return null;
	}
	
	public DataSource getSource()
	{
		
			return typeSourceMap.getDataSource();
	
	}
}
