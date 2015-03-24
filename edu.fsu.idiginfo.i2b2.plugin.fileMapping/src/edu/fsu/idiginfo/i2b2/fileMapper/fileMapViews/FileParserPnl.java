package edu.fsu.idiginfo.i2b2.fileMapper.fileMapViews;

import javax.swing.JPanel;

import edu.fsu.idiginfo.i2b2.fileMapper.fileMapperUtil.IFileParser;
import java.awt.GridLayout;
import javax.swing.JTextField;

public class FileParserPnl extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7613685461855050761L;

	protected IFileParser parser;
	private JTextField textField;
	private int value; // holds return from editor
	
	
	/**
	 * Create the panel.
	 */
	public FileParserPnl() {
		setLayout(new GridLayout(0, 1, 0, 0));
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);
		value = 0;

	}
	public IFileParser getParser() {
		return parser;
	}
	public void setParser(IFileParser parser) {
		this.parser = parser;
		textField.setText(parser.toString());
	}
	@Override
	public String toString() {
		if(parser != null)
		{
			return parser.toString();
		}
		return null;
	}
	
	public int  showEditor()
	{
		if(parser !=null){
		value = parser.showEditor();
		}
		return value;
	}
	public int getValue()
	{
		return value;
	}
}
