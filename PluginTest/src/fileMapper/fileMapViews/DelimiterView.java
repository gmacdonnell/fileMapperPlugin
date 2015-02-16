package fileMapViews;

import java.util.List;

import javax.swing.JPanel;


import fileMapper.fileMapperUtil.IFileParser;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;

public  class DelimiterView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JLabel lblNoFileType;

	/**
	 * Create the panel.
	 */
	public DelimiterView() {
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		 lblNoFileType = new JLabel("No File Type Selected");
		 add(lblNoFileType);
		

	}

	
	public IFileParser getParser() {
		// TODO Auto-generated method stub
		return null;
	}

	public void clear()
	{
		remove(lblNoFileType);
		revalidate();
		repaint();
	}
	public List<Object> getDelimiters() {
		// TODO Auto-generated method stub
		return null;
	}

}
