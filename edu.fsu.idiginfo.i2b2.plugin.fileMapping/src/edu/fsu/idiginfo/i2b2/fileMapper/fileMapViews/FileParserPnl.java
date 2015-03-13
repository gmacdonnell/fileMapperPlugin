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
	
	
	/**
	 * Create the panel.
	 */
	public FileParserPnl() {
		setLayout(new GridLayout(0, 1, 0, 0));
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);

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
	
	public void showEditor()
	{
		
	}
}
