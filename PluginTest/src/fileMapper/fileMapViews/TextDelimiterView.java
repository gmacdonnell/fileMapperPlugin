package fileMapper.fileMapViews;

import javax.swing.JCheckBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import fileMapper.fileMapperUtil.DelimitedFileParser;

import fileMapper.fileMapperUtil.IFileParser;

public class TextDelimiterView extends DelimiterView{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String TAB = "\t";
	public static String SPACE = " ";
	public static String COLON =":";
	public static String PIPE = "|";
	public static String COMMA = ",";
	private JTextField txtOther;
	private JCheckBox chbxColon;
	private JCheckBox chbxComma;
	private JCheckBox chbxPipe;
	private JCheckBox chbxTab;
	private JCheckBox chbxSpace;
	private JCheckBox chbxOther;
	
	/**
	 * Create the panel.
	 */
	public TextDelimiterView() {
		clear();
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{125, 69, 120, 0};
		gridBagLayout.rowHeights = new int[]{31, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		chbxComma = new JCheckBox("Comma \",\"");
		GridBagConstraints gbc_chckbxComma = new GridBagConstraints();
		gbc_chckbxComma.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxComma.anchor = GridBagConstraints.WEST;
		gbc_chckbxComma.gridx = 0;
		gbc_chckbxComma.gridy = 0;
		add(chbxComma, gbc_chckbxComma);
		
		 chbxPipe = new JCheckBox("Pipe \"|\"");
		GridBagConstraints gbc_chbxPipe = new GridBagConstraints();
		gbc_chbxPipe.anchor = GridBagConstraints.WEST;
		gbc_chbxPipe.insets = new Insets(0, 0, 5, 5);
		gbc_chbxPipe.gridx = 1;
		gbc_chbxPipe.gridy = 0;
		add(chbxPipe, gbc_chbxPipe);
		
		chbxTab = new JCheckBox("Tab");
		GridBagConstraints gbc_Tab = new GridBagConstraints();
		gbc_Tab.anchor = GridBagConstraints.WEST;
		gbc_Tab.insets = new Insets(0, 0, 5, 5);
		gbc_Tab.gridx = 0;
		gbc_Tab.gridy = 1;
		add(chbxTab, gbc_Tab);
		
		chbxColon = new JCheckBox("Colon \":\"");
		GridBagConstraints gbc_chbxColon = new GridBagConstraints();
		gbc_chbxColon.anchor = GridBagConstraints.WEST;
		gbc_chbxColon.insets = new Insets(0, 0, 5, 5);
		gbc_chbxColon.gridx = 1;
		gbc_chbxColon.gridy = 1;
		add(chbxColon, gbc_chbxColon);
		
	    chbxSpace = new JCheckBox("Space");
		GridBagConstraints gbc_chbxSpace = new GridBagConstraints();
		gbc_chbxSpace.anchor = GridBagConstraints.WEST;
		gbc_chbxSpace.insets = new Insets(0, 0, 0, 5);
		gbc_chbxSpace.gridx = 0;
		gbc_chbxSpace.gridy = 2;
		add(chbxSpace, gbc_chbxSpace);
		
		chbxOther = new JCheckBox("Other");
		GridBagConstraints gbc_chbxOther = new GridBagConstraints();
		gbc_chbxOther.anchor = GridBagConstraints.WEST;
		gbc_chbxOther.insets = new Insets(0, 0, 0, 5);
		gbc_chbxOther.gridx = 1;
		gbc_chbxOther.gridy = 2;
		add(chbxOther, gbc_chbxOther);
		
		txtOther = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 2;
		add(txtOther, gbc_textField);
		txtOther.setColumns(10);

	}
	
	public List<Object> getDelimiters()
	{
		LinkedList<Object> delim = new LinkedList<Object>();
	
		if( chbxColon.isSelected())
			{
				delim.add(COLON);
			}
		if(  chbxComma.isSelected())
		{
			delim.add(COMMA);
		}
		if(  chbxPipe.isSelected())
		{
			delim.add(PIPE);
		}
		if(  chbxTab.isSelected())
		{
			delim.add(TAB);
		}
		if(  chbxSpace.isSelected())
		{
			delim.add(SPACE);
		}
		if(  chbxOther.isSelected())
		{
			delim.add(txtOther.getText());
		}
		 
		return delim;
		
	}

	@Override
	public IFileParser getParser() {
		return new DelimitedFileParser(getDelimiters());
	}

}
