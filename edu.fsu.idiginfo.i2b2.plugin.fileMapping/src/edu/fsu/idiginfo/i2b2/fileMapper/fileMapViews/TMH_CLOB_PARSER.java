package edu.fsu.idiginfo.i2b2.fileMapper.fileMapViews;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

import edu.fsu.idiginfo.i2b2.fileMapper.fileMapperUtil.DelimitedFileParserClob;

import java.awt.FlowLayout;

public class TMH_CLOB_PARSER {

	private JFrame frame;
	private JTable clob_table;
	private JTextField txtCount;
	private String PATH = "C:\\Users\\GMACDONNELL\\Desktop\\TMH_TEST_DATA.csv";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TMH_CLOB_PARSER window = new TMH_CLOB_PARSER();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TMH_CLOB_PARSER() {
		initialize();
		init();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 830, 584);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{442, 0};
		gridBagLayout.rowHeights = new int[]{87, 259, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		frame.getContentPane().add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		txtCount = new JTextField();
		panel.add(txtCount);
		txtCount.setColumns(10);
		
		JPanel jpnl_table = new JPanel();
		GridBagConstraints gbc_jpnl_table = new GridBagConstraints();
		gbc_jpnl_table.fill = GridBagConstraints.BOTH;
		gbc_jpnl_table.gridx = 0;
		gbc_jpnl_table.gridy = 1;
		frame.getContentPane().add(jpnl_table, gbc_jpnl_table);
		
		clob_table = new JTable();
		jpnl_table.add(clob_table);
	}

	private void init()
	{
		DelimitedFileParserClob parser = new DelimitedFileParserClob();
		try{
		
		
		}catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
}
