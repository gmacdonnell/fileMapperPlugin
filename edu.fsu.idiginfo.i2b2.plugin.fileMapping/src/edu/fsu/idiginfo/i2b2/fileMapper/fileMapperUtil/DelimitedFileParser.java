package edu.fsu.idiginfo.i2b2.fileMapper.fileMapperUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;

import org.eclipse.jface.viewers.Viewer;

import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.Column;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.ColumnData;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.DataFile;
import edu.fsu.idiginfo.i2b2.fileMapper.fileMapViews.AbsEditorDlg;
import edu.fsu.idiginfo.i2b2.fileMapper.fileMapViews.DelimiterView;
import edu.fsu.idiginfo.i2b2.fileMapper.fileMapViews.TextDelimiterEditorDlg;

public class DelimitedFileParser implements IFileParser {

	public static String TYPE = "DelimitedFile";
	protected ArrayList<String> Lines;
	protected String[] Delimiters;
	protected int FieldCount;
	protected int RowCount;
	private String filePath;

	public DelimitedFileParser(List<Object> delimiters) {
		setDelimiters(delimiters);
		init();

	}

	public DelimitedFileParser() {

		init();

	}

	private void init() {
		Lines = new ArrayList<String>();
	}

	public String[][] getItems() {
		return (String[][]) parseFile().toArray();
	}

	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

	public void loadFile(String filePath) throws Exception {
		this.filePath = filePath;
		FileInputStream is = null;
		Scanner sc = null;
		int count = 0;
		try {
			is = new FileInputStream(filePath);
			sc = new Scanner(is);
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				Lines.add(line);
				if (count == 0) {
					FieldCount = ParseLine(line).length;

				}
				count++;
			}
		} catch (Exception e) {

			throw (e);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (sc != null) {
					sc.close();
				}

			} catch (Exception e) {

				throw (e);
			}
		}
		RowCount = count;
	}

	@Override
	public void loadFileShort(String filePath) throws Exception {
		this.filePath = filePath;
		FileInputStream is = null;
		Scanner sc = null;
		try {
			is = new FileInputStream(filePath);
			sc = new Scanner(is);
			int count = 0;

			while (sc.hasNextLine() && count < SAMPLES) {

				String line = sc.nextLine();
				Lines.add(line);
				if (count == 0) {
					FieldCount = ParseLine(line).length;

				}
				count++;
			}

		} catch (Exception e) {

			throw (e);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (sc != null) {
					sc.close();
				}

			} catch (Exception e) {

				throw (e);
			}
		}

	}

	public void setDelimiters(List<Object> del) {
		Object[] input = del.toArray();
		Delimiters = new String[input.length];
		for (int index = 0; index < input.length; index++) {
			if (input[index] instanceof String) {
				Delimiters[index] = (String) input[index];
			}
		}

	}

	public int getFieldCount() {

		return FieldCount;
	}

	public List<ColumnData> parseFile() {

		String[][] records = new String[Lines.size()][];
		for (int index = 0; index < Lines.size(); index++) {
			records[index] = (ParseLine(Lines.get(index)));
		}
		return makeColumns(records);
	}

	public List<ColumnData> makeColumns(String[][] records) {
		ArrayList<ColumnData> columns = new ArrayList<ColumnData>();
		for (int col = 0; col < records[0].length; col++) {
			ColumnData cd = new ColumnData();
			Column column = getColumn();
			column.setName(records[0][col]);
			cd.setColumn(column);

			for (int row = 0; row < records.length; row++) {
				cd.getValues().add(records[row][col]);

			}
			columns.add(cd);
		}
		return columns;
	}

	public String[] ParseLine(String line) {
		String[] fields = new String[1];
		fields[0] = line;
		String[] newFields = null;
		if (Delimiters != null) {
			for (String del : Delimiters) {

				for (String block : fields) {
					newFields = Combine(newFields, ParseBlock(block, del));
				}
				fields = newFields;

			}
		}
		return fields;
	}

	private String[] ParseBlock(String line, String del) {
		return line.split(del);

	}

	private String[] Combine(String[] A, String[] B) {
		int size = 0;
		if (A != null) {
			size += A.length;
		}
		if (B != null) {
			size += B.length;
		}
		String[] out = new String[size];
		int index = 0;
		if (A != null) {
			for (String s : A) {
				out[index] = s;
				index++;
			}
		}
		if (B != null) {
			for (String s : B) {
				out[index] = s;
				index++;
			}
		}
		return out;
	}

	public Object[] getElements(Object inputElement) {
		// TODO Auto-generated method stub
		if (inputElement instanceof String[][]) {
			String[][] in = (String[][]) inputElement;
			String[] out = in[0];
			int index = 1;
			while (index < in.length) {
				out = Combine(out, in[index]);
				index++;
			}
			return out;
		}
		return null;
	}

	public Column getColumn() {
		Column col = new Column();
		DataFile file = new DataFile();
		file.setID(filePath);
		file.setType(TYPE);
		file.getNotes().add(0, -1);
		int count = 1;
		for(String delim : Delimiters)
		{
			char letter = delim.charAt(0);
			file.getNotes().add(count, (int)letter);
			count++;
		}
		col.setSourceFile(file);

		return col;
	}

	public String toString() {
		return TYPE;
	}

	@Override
	public int showEditor() {
		TextDelimiterEditorDlg editor = new TextDelimiterEditorDlg();
		int result = editor.showDialog();
		if(result == AbsEditorDlg.OK)
		{
		setDelimiters(editor.getDelimiters());
		}
		return result;
		
	}



}
