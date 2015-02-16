package fileMapViews;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.border.BevelBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

public class FileOpener extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File FilePath;
	private JEditorPane txtPath;
	/**
	 * Create the panel.
	 */
	public FileOpener() {
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setLayout(null);
		txtPath = new JEditorPane();
		txtPath.setText("C:\\");
		txtPath.setBounds(59, 0, 391, 39);
		add(txtPath);
		final JButton btnFile = new JButton("File");
		btnFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser dlg = new JFileChooser(txtPath.getText());
				dlg.showOpenDialog(getParent());
				
				FilePath = dlg.getSelectedFile();
				txtPath.setText(FilePath.getPath());
				
				
				
			}
		});
			
		btnFile.setBounds(0, 0, 55, 39);
		add(btnFile);
		
		

	}
	
	public File getFile()
	{
		return FilePath;
	}
	
	public void setPath(String path)
	{
		FilePath = new File(path);
		txtPath.setText(FilePath.getPath());
	}

}
