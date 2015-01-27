package plugintest.ui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class TestPanal extends JPanel {

	/**
	 * Create the panel.
	 */
	public TestPanal() {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel fileMap_pnl = new JPanel();
		add(fileMap_pnl);
		fileMap_pnl.setLayout(new BorderLayout(0, 0));
		
		JLabel lblFileMapTitle = new JLabel("File Map");
		lblFileMapTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblFileMapTitle.setFont(new Font("Tahoma", Font.PLAIN, 17));
		fileMap_pnl.add(lblFileMapTitle, BorderLayout.NORTH);
		
		JList matchedMaps_lst = new JList();
		matchedMaps_lst.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		matchedMaps_lst.setVisibleRowCount(3);
		fileMap_pnl.add(matchedMaps_lst, BorderLayout.SOUTH);
		
		JPanel reqTerms_pnl = new JPanel();
		fileMap_pnl.add(reqTerms_pnl, BorderLayout.WEST);
		
		JButton btnTestbutton = new JButton("TestButton");
		reqTerms_pnl.add(btnTestbutton);
		
		JPanel column_pnl = new JPanel();
		fileMap_pnl.add(column_pnl, BorderLayout.CENTER);
		fileMap_pnl.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblFileMapTitle}));

	}

}
