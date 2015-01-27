package fileMapper.fileMapViews;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;


public class FileTypeView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	private int FileType;
	private DelimiterView DelView;
	private JFrame Frame;
	
	public int getFileType() {
		return FileType;
	}

	static public int NONE = 0;
	static public int XML =1;
	static public int DELIMITEDTEXT=2;
	static public int FIXEDWIDTHTEXT=3;
	
	public FileTypeView() {
		FileType=NONE;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{153, 0};
		gridBagLayout.rowHeights = new int[]{25, 25, 25, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JRadioButton rdbXML = new JRadioButton("XML File");
		GridBagConstraints gbc_rdbXML = new GridBagConstraints();
		gbc_rdbXML.anchor = GridBagConstraints.NORTHWEST;
		gbc_rdbXML.insets = new Insets(0, 0, 5, 0);
		gbc_rdbXML.gridx = 0;
		gbc_rdbXML.gridy = 0;
		add(rdbXML, gbc_rdbXML);
		
		JRadioButton rdbTextDelimited = new JRadioButton("Delimited Text File",true);
		GridBagConstraints gbc_rdbTextDelimited = new GridBagConstraints();
		gbc_rdbTextDelimited.anchor = GridBagConstraints.NORTHWEST;
		gbc_rdbTextDelimited.insets = new Insets(0, 0, 5, 0);
		gbc_rdbTextDelimited.gridx = 0;
		gbc_rdbTextDelimited.gridy = 1;
		add(rdbTextDelimited, gbc_rdbTextDelimited);
		
		JRadioButton rdbFixedWidthText = new JRadioButton("Fixed Width Text File");
		GridBagConstraints gbc_rdbFixedWidthText = new GridBagConstraints();
		gbc_rdbFixedWidthText.anchor = GridBagConstraints.NORTHWEST;
		gbc_rdbFixedWidthText.gridx = 0;
		gbc_rdbFixedWidthText.gridy = 2;
		add(rdbFixedWidthText, gbc_rdbFixedWidthText);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbXML);
		group.add(rdbTextDelimited);
		group.add(rdbFixedWidthText);
		
		rdbXML.addItemListener(new ItemListener() {
	         public void itemStateChanged(ItemEvent e) {
	        	 if(e.getStateChange() == 1)
	        	 {
	        		 FileType=XML;
	        		 DelimiterView TV = new DelimiterView();
			           TV.setBounds(DelView.getBounds());
			           Frame.getContentPane().remove(DelView);
			           Frame.getContentPane().repaint();
			           DelView = TV;
			           Frame.getContentPane().add(DelView);
			           DelView.revalidate();
			           Frame.getContentPane().revalidate();
	        	 }
	         }           
	      });
		rdbTextDelimited.addItemListener(new ItemListener() {
	         public void itemStateChanged(ItemEvent e) {  
	        	 if(e.getStateChange() == 1)
	        	 {
		           FileType = DELIMITEDTEXT;
		           TextDelimiterView TV = new TextDelimiterView();
		           TV.setBounds(DelView.getBounds());
		           Frame.getContentPane().remove(DelView);
		           Frame.getContentPane().repaint();
		           DelView = TV;
		          
		           Frame.getContentPane().add(DelView);
		           Frame.getContentPane().revalidate();
		         //  DelView.revalidate();
		          
		        
		          
	        	 }
	         }           
	      });
		rdbFixedWidthText.addItemListener(new ItemListener() {
	         public void itemStateChanged(ItemEvent e) { 
	        	 if(e.getStateChange() == 1)
	        	 {
	        		 FileType = FIXEDWIDTHTEXT;
	        		 DelimiterView TV = new DelimiterView();
			           TV.setBounds(DelView.getBounds());
			           Frame.getContentPane().remove(DelView);
			           Frame.getContentPane().repaint();
			           DelView = TV;
			           Frame.getContentPane().add(DelView);
			           Frame.revalidate();
	        	 }
	         }           
	      });

	}
	public DelimiterView getDelView() {
		return DelView;
	}

	public void setDelView(DelimiterView delView) {
		DelView = delView;
	}
	public void addFrame(JFrame frame) {
		Frame = frame;
		
	}

}
