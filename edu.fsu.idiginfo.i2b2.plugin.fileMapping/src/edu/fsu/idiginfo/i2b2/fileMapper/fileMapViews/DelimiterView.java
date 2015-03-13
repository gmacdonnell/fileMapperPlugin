package edu.fsu.idiginfo.i2b2.fileMapper.fileMapViews;

import java.util.List;

import javax.swing.JPanel;


import edu.fsu.idiginfo.i2b2.fileMapper.fileMapperUtil.IFileParser;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;

public  class DelimiterView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Create the panel.
	 */
	public DelimiterView() {
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		
		 
		

	}

	
	public IFileParser getParser() {
		// TODO Auto-generated method stub
		return null;
	}

	public void clear()
	{
		
		revalidate();
		repaint();
	}
	public List<Object> getDelimiters() {
		// TODO Auto-generated method stub
		return null;
	}

}
