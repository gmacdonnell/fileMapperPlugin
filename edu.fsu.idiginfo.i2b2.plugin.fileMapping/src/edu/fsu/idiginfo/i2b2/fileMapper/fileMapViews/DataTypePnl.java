package edu.fsu.idiginfo.i2b2.fileMapper.fileMapViews;

import javax.swing.JPanel;

import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.DataType;

public class DataTypePnl extends JPanel {

	/**
	 * Display A dataType for dragging onto a column 
	 */
	protected DataType type;
	public DataTypePnl( DataType dataType) {
		type = dataType;

	}

}
