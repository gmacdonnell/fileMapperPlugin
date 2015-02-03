package workBench;

import java.awt.EventQueue;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.TransferHandler;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

import java.util.LinkedList;


import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import org.jdom.input.SAXBuilder;


import fileMapper.fileMapperUtil.models.DataImportModel;
import fileMapViews.FileOpener;
import fileMapViews.ColumnViewer;
import fileMapViews.TextDelimiterView;
import fileMapViews.FileTypeView;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SwingWorkBench {

	private JFrame frame;
public static int COLUMNS = 3;
public static int ROWS = 5;
private ColumnViewer columnViewer;
private FileTypeView fileTypeView;
private TextDelimiterView delimiterView;
private FileOpener fileOpener;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingWorkBench window = new SwingWorkBench();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SwingWorkBench() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 632, 635);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		columnViewer = new ColumnViewer();
		columnViewer.setBounds(12, 241, 586, 350);
		frame.getContentPane().add(columnViewer);
		
		fileOpener = new FileOpener();
		fileOpener.setBounds(12, 23, 451, 37);
		fileOpener.setPath("C:\\Projects\\PCOR\\ExportToI2b2");
		frame.getContentPane().add(fileOpener);
		
		delimiterView = new TextDelimiterView();
		delimiterView.setBounds(12, 86, 327, 90);
		frame.getContentPane().add(delimiterView);
		
		//DelimiterView delimiterView = new DelimiterView();
		//delimiterView.setBounds(12, 97, 331, 88);
		//frame.getContentPane().add(delimiterView);
		
		fileTypeView = new FileTypeView();
		fileTypeView.setDelView(delimiterView);
		fileTypeView.addFrame(frame);
		fileTypeView.setBounds(401, 97, 153, 90);
		frame.getContentPane().add(fileTypeView);
		
		JButton btnParse = new JButton("Parse");
		btnParse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				load();
			}
		});
		btnParse.setBounds(22, 189, 99, 25);
		frame.getContentPane().add(btnParse);
		
	}
	private void load()
	{
		columnViewer.setParser(delimiterView.getParser());
		try{
		columnViewer.getParser().loadFile(fileOpener.getFile().getPath());
		columnViewer.Load();
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "File Loading issue: "+e.toString());
		}
	}
	public LinkedList<String[]> getData()
	{
		LinkedList<String[]>data = new LinkedList<String[]>();
		data.push(new String[]{"1","A","Alpha"});
		data.push(new String[]{"2","b","beta"});
		data.push(new String[]{"3","c","gama"});
		data.push(new String[]{"4","d","delta"});
		data.push(new String[]{"5","e","epsilon"});
		return data;
	}
	
	private String[] getNames()
	{
		
		String[] names = new String[COLUMNS];
		for(int index = 0; index < COLUMNS; index++)
		{
			names[index]="COL"+index;
		}
		return names;
	}
	public JTable fillTable()
	{
		DataImportModel model = new DataImportModel(5);
		model.setData(getData());
		model.setNames(getNames());
		JTable table = new JTable();
		table.setModel(model);
		
		//table.setTransferHandler(new TextHandler());
		table.getTableHeader().setTransferHandler(new TextHandler());
		//JTableHeader header = table.getTableHeader();
		
	
		return table;
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
		
		public boolean importData(JComponent comp, Transferable t) {
			try {
				final String text = (String) t
						.getTransferData(DataFlavor.stringFlavor);
				JTableHeader header=null;
				int colIndex=-1;
				if(comp instanceof JTableHeader)
					{
						header=(JTableHeader)comp;
					
						//JTable table = header.getTable();
						java.awt.Point point = header.getMousePosition();
						 colIndex = header.columnAtPoint(point);
						
						//Component col =table.getComponentAt(point);
					
						System.out.println(colIndex + " was selected");
						
					
				
				try {    
					SAXBuilder parser = new SAXBuilder();
					String xmlContent = text;
					java.io.StringReader xmlStringReader = new java.io.StringReader(
							xmlContent);
					final org.jdom.Document tableDoc = parser.build(xmlStringReader);
					org.jdom.Element tableXml1 = null;
					for (int i = 0; i < tableDoc.getRootElement().getContent()
							.size(); i++) {
						if (tableDoc.getRootElement().getContent().get(i)
								.getClass().getSimpleName().equalsIgnoreCase(
										"Element")) {
							tableXml1 = (org.jdom.Element) tableDoc
									.getRootElement().getContent().get(i);
							break;
						}
					}
					org.jdom.Element concept = tableXml1.getChild("concept");
					header.getColumnModel().getColumn(colIndex).setHeaderValue(concept.getChild("name").getValue());
					header.repaint();
					
			}
				catch(Exception e)
				{
					System.out.println(e.toString());
				}
					}
			}catch(Exception e)
			{
				System.out.println(e.toString());
			}
			
			return true;
		}
	}//end TextHandler
}
