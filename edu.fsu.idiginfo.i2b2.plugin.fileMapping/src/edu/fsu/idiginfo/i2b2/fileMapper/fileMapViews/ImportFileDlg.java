package edu.fsu.idiginfo.i2b2.fileMapper.fileMapViews;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ImportFileDlg extends JDialog {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6010693989489708891L;
	private JDesktopPane deskTop;

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

	/**
	 * Create the dialog.
	 */
	public ImportFileDlg() {
		setTitle("Import File Data to I2B2 Types");
		setBounds(100, 100, 685, 617);
		getContentPane().setLayout(new BorderLayout());
		
		
		{
			deskTop = new JDesktopPane();
			newTypeSourceMap();
			getContentPane().add(deskTop, BorderLayout.CENTER);
			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnAddFileMap = new JButton("Add File Map");
				btnAddFileMap.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						newTypeSourceMap();
					}
				});
				buttonPane.add(btnAddFileMap);
			}
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

	private void newTypeSourceMap()
	{
		TypeSourceMap typeSourceMap = new TypeSourceMap();
		//typeSourceMap.setSelected(true);
		typeSourceMap.setTitle("File Data Map");
		typeSourceMap.setClosable(true);
		typeSourceMap.setLocation(25, 0);
		typeSourceMap.setVisible(true);
		deskTop.add(typeSourceMap);
	}
}
