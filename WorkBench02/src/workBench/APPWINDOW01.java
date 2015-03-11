package workBench;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import fileMapViews.DataTypes;
import javax.swing.JInternalFrame;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class APPWINDOW01 {

	private JFrame frame;
	private JMenuBar menuBar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					APPWINDOW01 window = new APPWINDOW01();
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
	public APPWINDOW01() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 645, 452);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{637, 0};
		gridBagLayout.rowHeights = new int[]{391, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JDesktopPane desktopPane = new JDesktopPane();
		GridBagConstraints gbc_desktopPane = new GridBagConstraints();
		gbc_desktopPane.fill = GridBagConstraints.BOTH;
		gbc_desktopPane.gridx = 0;
		gbc_desktopPane.gridy = 0;
		frame.getContentPane().add(desktopPane, gbc_desktopPane);
		
		JInternalFrame internalFrame = new JInternalFrame("New JInternalFrame");
		internalFrame.setBounds(105, 94, 104, 291);
		desktopPane.add(internalFrame);
		
		DataTypes dataTypes = new DataTypes();
		internalFrame.getContentPane().add(dataTypes, BorderLayout.CENTER);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		internalFrame.setVisible(true);
		initMenu(getSample());
	}
	private ArrayList<String> getSample()
	{
		ArrayList<String> list = new ArrayList<String>();
		list.add("Provider");
		list.add("Patient");
		list.add("Visit");
		list.add("Concept");
		list.add("Observation Fact");
		return list;
		
	}
	private void initMenu(ArrayList<String> Types)
	{
		JMenu mnAddDataType = new JMenu("Add Data Type");
		menuBar.add(mnAddDataType);
		
		for(String name : Types)
		{
		JMenuItem mntmExample = new JMenuItem(name);
		mntmExample.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
			
		}
				);
		mnAddDataType.add(mntmExample);
		
		}
	}
}
