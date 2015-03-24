package edu.fsu.idiginfo.i2b2.fileMapper.fileMapViews;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TextDelimiterEditorDlg extends AbsEditorDlg implements ActionListener{

	/**
	 * Select the characters that delimit the records on the file
	 */
	private static final long serialVersionUID = -5224024122386091885L;


	private final JPanel contentPanel = new JPanel();
	protected TextDelimiterView textDelimiterView;
	private int value;


	/**
	 * Create the dialog.
	 */
	public TextDelimiterEditorDlg() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			textDelimiterView = new TextDelimiterView();
			contentPanel.add(textDelimiterView);
		}
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
		value = AbsEditorDlg.CANCEL;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	public List getDelimiters()
	{
		return textDelimiterView.getDelimiters();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		setVisible(false); 
	    dispose(); 
	}

	public int showDialog()
	{
		this.setModal(true);
		setVisible(true);
		
		return value;
	}
}
