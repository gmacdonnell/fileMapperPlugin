package fileMapper.fileMapperUtil.UI;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.util.LinkedList;

import javax.swing.JComponent;
import javax.swing.TransferHandler;
import javax.swing.table.JTableHeader;

import org.jdom.input.SAXBuilder;

import fileMapper.fileMapperUtil.models.Concept;

public class ConceptDropHandler extends TransferHandler {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private LinkedList<Concept> Concepts;
	public ConceptDropHandler() {
		super("text");
		Concepts = new LinkedList<Concept>();
	}

	public boolean canImport(JComponent comp, DataFlavor[] transferFlavor) {
		// Accepts all drops

		return true;
	}

	public boolean importData(JComponent comp, Transferable t) {
		try {
			final String text = (String) t
					.getTransferData(DataFlavor.stringFlavor);
			JTableHeader header = null;
			int colIndex = -1;
			if (comp instanceof JTableHeader) {
				header = (JTableHeader) comp;

				// JTable table = header.getTable();
				java.awt.Point point = header.getMousePosition();
				colIndex = header.columnAtPoint(point);
				clear(colIndex);

				// Component col =table.getComponentAt(point);

			//	System.out.println(colIndex + " was selected");

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
					Concept concept = new Concept();
					org.jdom.Element element = tableXml1.getChild("concept");
					concept.setFullName(element.getChild("name").getValue());
					concept.setName(element.getChild("basecode").getValue());
					concept.setColum(colIndex);
					concept.setColumnDataType(element.getChild("columndatatype").getValue());
					concept.setColumnName(element.getChild("columnname").getValue());
					concept.setFactTableColumn(element.getChild("facttablecolumn").getValue());
					concept.setTableCD(element.getChild("tablename").getValue());
					Concepts.add(concept);
					header.getColumnModel()
							.getColumn(colIndex)
							.setHeaderValue(concept.getFullName());
					header.repaint();

				} catch (Exception e) {
					System.out.println(e.toString());
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return true;
	}
	
	private void clear(int index)
	{
		for(int counter = 0; counter < Concepts.size(); counter++)
		{
			if(Concepts.get(counter).getColum()== index)
			{
				Concepts.remove(counter);
			}
		}
	}
	
	public LinkedList<Concept> getLabledColumns()
	{
		return Concepts;
	}
}// end TextHandler

