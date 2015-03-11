package edu.fsu.idiginfo.i2b2.fileMapper.fileMapperUtil;

import javax.swing.JOptionPane;

public class FileMapperUtil {
	
	public static void ShowError(Exception e)
	{
		 JOptionPane.showMessageDialog(null, e.toString());
	}

}
