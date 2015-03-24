package edu.fsu.idiginfo.i2b2.fileMapper.fileMapViews;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.DataSource;

import java.awt.GridLayout;

public class CombineColumnDlg extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2837645348407514890L;
	private final JPanel contentPanel = new JPanel();
	protected TypeSourceMap caller;
	protected CombineColumnPnl combiner;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CombineColumnDlg dialog = new CombineColumnDlg();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	
	public CombineColumnDlg() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
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

	public void setData(DataSource fromFile, DataSource existing)
	{
		combiner = new CombineColumnPnl(fromFile, existing);
		contentPanel.add(combiner);
	}
	public void setCallingView(TypeSourceMap caller)
	{
		this.caller = caller;
	}
}
