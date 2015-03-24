package edu.fsu.idiginfo.i2b2.fileMapper.fileMapViews;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public abstract class AbsEditorDlg extends JDialog {

	private final JPanel contentPanel = new JPanel();
 public static int OK = 1;
 public static int CANCEL = 2;
	
	/**
	 * Create the dialog.
	 */
	public AbsEditorDlg() {
		
		
	}

	abstract public int showDialog();
}
