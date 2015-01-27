package plugintest.ui;

import javax.swing.JPanel;

import edu.harvard.i2b2.query.data.ModifierData;
import edu.harvard.i2b2.query.data.QueryConceptTreeNodeData;

import javax.swing.JComponent;
import javax.swing.JLabel;

import javax.swing.TransferHandler;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;


import java.util.Iterator;
import java.util.List;
import javax.swing.BoxLayout;
import java.awt.Component;


import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import plugintest.views.*;

public class ConceptPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4689788240961279829L;

	/**
	 * Create the panel.
	 */
	private JComboBox cbcolumnTerm = null;
	private JTable tblFileColumnData;
	private JLabel lblDropConceptHere;
	private ViewTest parentView = null;
	private int iPosition;
	public ConceptPanel() {
		setSize(new Dimension(144, 500));
		setMinimumSize(new Dimension(20, 100));
		setPreferredSize(new Dimension(144, 500));
		init();
	}
	
	public ConceptPanel(String title)
	{
		init();
		setTitle(title);
	}
	public void setPosition(int position)
	{
		this.iPosition = position;
	}
	public int getPosition(){
		return this.iPosition;
	}
	private void init(){
		iPosition = 0;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		

		lblDropConceptHere = new JLabel("Ontology Term");
		lblDropConceptHere.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblDropConceptHere.setBounds(10, 9, 93, 16);
		add(lblDropConceptHere);
		
		cbcolumnTerm = new JComboBox();
		add(cbcolumnTerm);
		cbcolumnTerm.setTransferHandler(new TextHandler());
		
		JScrollPane spFileColumnData = new JScrollPane();
		add(spFileColumnData);
		
		tblFileColumnData = new JTable();
		tblFileColumnData.setPreferredSize(new Dimension(150, 450));
		tblFileColumnData.setPreferredScrollableViewportSize(new Dimension(150, 500));
		tblFileColumnData.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				
			},
			new String[] {
				"New column"
			}
		));
		tblFileColumnData.setTableHeader(null);
		spFileColumnData.setViewportView(tblFileColumnData);

	}
	
	public void setTitle(String title){
		lblDropConceptHere.setText(title);
	}
	public void setModel(Object model [][]){
		tblFileColumnData.setModel(new DefaultTableModel(model,	new String[] {
			"New column"
		}));
	}

	public ViewTest getView()
	{
		return parentView;
	}
	
	public void setView(ViewTest parent)
	{
		this.parentView = parent;
	}
	class TextHandler extends TransferHandler {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public TextHandler() {
			super("text");
		}

		public boolean canImport(JComponent comp, DataFlavor[] transferFlavor) {
			// Accepts all drops

			return true;
		}
		public void test()
		{
			parentView.sendQuery();
		}

		public boolean importData(JComponent comp, Transferable t) 
		{
			
			try {
				
				final String text =(String)t.getTransferData(DataFlavor.stringFlavor);

				String description = null;
			//	String id = null;

				try {
					SAXBuilder parser = new SAXBuilder();
					String xmlContent = text;
					java.io.StringReader xmlStringReader = new java.io.StringReader(
							xmlContent);
					final org.jdom.Document tableDoc = parser
							.build(xmlStringReader);
					org.jdom.Element tableXml1 = null;
					for (int i = 0; i < tableDoc.getRootElement().getContent()
							.size(); i++) {
						if (tableDoc.getRootElement().getContent().get(i)
								.getClass().getSimpleName()
								.equalsIgnoreCase("Element")) {
							tableXml1 = (org.jdom.Element) tableDoc
									.getRootElement().getContent().get(i);
							break;
						}
					}
					String Name = tableXml1.getName();
					
					
					org.jdom.Element tableXml = tableDoc
							.getRootElement()
							.getChild(
									"concepts",
									Namespace
											.getNamespace("http://www.i2b2.org/xsd/cell/ont/1.1/"));
					
							
					List conceptChildren = tableXml.getChildren();
					for (Iterator itr = conceptChildren.iterator(); itr
							.hasNext();) {
						
						Element conceptXml = (org.jdom.Element) itr.next();
						String conceptText = conceptXml.getText().trim();
						if (conceptText.equals("null")) // this is root level
						// node
						{
							java.awt.EventQueue.invokeLater(new Runnable() {
								public void run() {
									/*JOptionPane
											.showMessageDialog(this,
													"Please note, You can not drop this item here.");*/
								}
							});
							return true;
						}
						

						final Element conTableXml = conceptXml;
						String TermName = conTableXml.getChildText("name");
						cbcolumnTerm.addItem(TermName);
						cbcolumnTerm.setSelectedItem(TermName);
						//setText( conTableXml.getChildText("key"));
						org.jdom.Element nameXml = conTableXml.getChild("modifier");
						
						QueryConceptTreeNodeData node = null;
						if(nameXml == null) {
						  node = new QueryConceptTreeNodeData();
						}
						else {
							node = new ModifierData();
							node.isModifier(true);
							
							org.jdom.Element modifierXml = nameXml.getChild("applied_path");
							String applied_path = modifierXml.getText();
							((ModifierData)node).applied_path(applied_path);
							
							modifierXml = nameXml.getChild("key");
							String key1 = modifierXml.getText();
							((ModifierData)node).modifier_key(key1);
							
							modifierXml = nameXml.getChild("name");
							String name = modifierXml.getText();
							((ModifierData)node).modifier_name(name);
							
							//((ModifierData)node).updateModifierMetaDataXML();
							//parentPanel.enableSameVisit(true);
						}
						nameXml = conTableXml.getChild("name");
						String c_name = nameXml.getText();
					}
				} catch (Exception e) {}

			} catch (Exception e) {
				return false;
			}

			return true;
		}

	}
	
	
}

