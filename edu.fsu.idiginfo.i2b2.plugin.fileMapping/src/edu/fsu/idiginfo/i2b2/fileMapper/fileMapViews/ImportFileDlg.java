package edu.fsu.idiginfo.i2b2.fileMapper.fileMapViews;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ImportFileDlg extends JDialog {

	
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
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		
		
		{
			deskTop = new JDesktopPane();
			TypeSourceMap typeSourceMap = new TypeSourceMap();
			typeSourceMap.setVisible(true);
			deskTop.add(typeSourceMap);
			getContentPane().add(deskTop, BorderLayout.CENTER);
			
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

}
