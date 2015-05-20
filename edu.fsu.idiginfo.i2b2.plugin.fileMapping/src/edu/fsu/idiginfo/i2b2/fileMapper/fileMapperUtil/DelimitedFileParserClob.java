package edu.fsu.idiginfo.i2b2.fileMapper.fileMapperUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;

import org.eclipse.jface.viewers.Viewer;

import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.Column;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.ColumnData;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.DataFile;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.TMHVisit;
import edu.fsu.idiginfo.i2b2.fileMapper.fileMapViews.AbsEditorDlg;
import edu.fsu.idiginfo.i2b2.fileMapper.fileMapViews.DelimiterView;
import edu.fsu.idiginfo.i2b2.fileMapper.fileMapViews.TextDelimiterEditorDlg;

public class DelimitedFileParserClob implements IFileParser {

	private static String HEADER;
	public static String TYPE = "DelimitedFile";
	protected ArrayList<String> Lines;
	protected String[] Delimiters;
	protected int FieldCount;
	protected int RowCount;
	private static String ERROR_LOG_EXT = "_error.log.csv";
	private static String XML_EXT = ".xml";
	public static String COMMA = ",";
	public static char QUOTE = '\"';
	public static String AND = ";";
	public static String NOT_NUM = "[\\D]+";
	public static String BLANK = "";
	public static String SPACE = " ";
	public static String UNDERSCORE = "_";
	public static String NEW_RECORD = "\\r\\d*[^A-z]";
	public static String ILLLEGAL_CHARS = "[^A-z_,\\d\\s:.?;]";
	public static String NEW_LINE = System.getProperty("line.separator");
	public static int CHUNCK = 100;
	public static int TAIL_LENGTH = 6;
	public static int HSP_MD_NUM = 0;
	public static int CHF_CMPLN = 1;
	public static int ENCOUNTERDIAGDESCR = 2;
	public static int LCHL_NTK = 3;
	public static int SMK_HW_MCH = 4;
	public static int SMKNG_FRMR_SMKRS_QT_TM = 5;
	public static int SMKNG_STTS = 6;
	public static int TBCC_YRS_FS = 7;
	public static int TBCC_YRS_FS_DT = 8;
	public static int SURG_HIST_PROC = 9;
	public static int VISIT_ID = 10;
	public static int VISIT_STATUS = 11;
	public static int FIELD_BREAKS = 9;
	public static int NUM_FIELDS = 10;
	public static int GOOD_COUNT = 0;
	public static int BAD_COUNT = 1;
	private static String[] NTK_TYPE = { "HEAVY", "MODERATE", "OCCASIONAL",
			"NONE" };
	private static boolean DEBUG = false;
	private static Integer[] max_lengths = new Integer[NUM_FIELDS];

	public static void main(String[] args) {
		// for testing
		String InFile = "C:\\Users\\GMACDONNELL\\Desktop\\TMH_TESTING\\FSU_Dx.csv";
		String OutFile = "C:\\Users\\GMACDONNELL\\Desktop\\TMH_TESTING\\FSU_DX_CLEANED.csv";
		String VOutFile = "C:\\Users\\GMACDONNELL\\Desktop\\TMH_TESTING\\FSU_Valid.csv";
		String COutFile = "C:\\Users\\GMACDONNELL\\Desktop\\TMH_TESTING\\FSU_Comp.csv";
		String DOutFile = "C:\\Users\\GMACDONNELL\\Desktop\\TMH_TESTING\\FSU_Dup.csv";

		try {
			File out = new File(OutFile);
			File errors = new File(InFile + ERROR_LOG_EXT);
			File verrors = new File(OutFile + ERROR_LOG_EXT);
			File cerrors = new File(VOutFile + ERROR_LOG_EXT);
			File derrors = new File(COutFile + ERROR_LOG_EXT);
			File vout = new File(VOutFile);
			File cout = new File(COutFile);
			File dout = new File(DOutFile);
			if (out.exists()) {
				out.delete();
				out = null;
			}
			if (errors.exists()) {
				errors.delete();
				errors = null;
			}
			if (verrors.exists()) {
				verrors.delete();
				verrors = null;
			}
			if (cerrors.exists()) {
				cerrors.delete();
				cerrors = null;
			}
			if (derrors.exists()) {
				derrors.delete();
				derrors = null;
			}
			if (vout.exists()) {
				vout.delete();
				vout = null;
			}
			if (cout.exists()) {
				cout.delete();
				cout = null;
			}
			if (dout.exists()) {
				dout.delete();
				dout = null;
			}
			double start, end;
			start = System.currentTimeMillis()/1000;
			int[] count = cleanRows(InFile, OutFile);
			end = System.currentTimeMillis()/1000;
			System.out.println("Cleaning rows elapsed time: " + new Double( end- start).toString());
			System.out.println("Total rows cleaned: " + count[GOOD_COUNT]);
			System.out.println("Total Error Records: " + count[BAD_COUNT]);
			start = System.currentTimeMillis()/1000;
			int[] vcount = validateRows(OutFile, VOutFile);
			end = System.currentTimeMillis()/1000;
			System.out.println("Validating rows elapsed time: " + new Double( end- start).toString());
			System.out.println("Total rows  " + vcount[GOOD_COUNT]);
			System.out.println("Total invalid rows: " + vcount[BAD_COUNT]);
			start = System.currentTimeMillis()/1000;
			int[] ccount = parseComplaint(VOutFile, COutFile);
			end = System.currentTimeMillis()/1000;
			System.out.println("Parsing rows elapsed time: " + new Double( end- start).toString());
			System.out.println("Total rows into parseComplaint: "
					+ ccount[GOOD_COUNT]);
			System.out.println("Total Error Records: " + ccount[BAD_COUNT]);
			start = System.currentTimeMillis()/1000;
			int[] dcount = removeDuplicateRecords(COutFile, DOutFile);
			end = System.currentTimeMillis()/1000;
			System.out.println("Removing duplicate rows elapsed time: " + new Double( end- start).toString());
			System.out.println("Total rows into parsed File: "
					+ dcount[GOOD_COUNT]);
			System.out.println("Total Duplicates: " + dcount[BAD_COUNT]);
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	public DelimitedFileParserClob(List<Object> delimiters) {
		setDelimiters(delimiters);
		init();

	}

	public DelimitedFileParserClob() {

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

	/**
	 * Count the number of instances of substring within a string.
	 * 
	 * @param string
	 *            String to look for substring in.
	 * @param substring
	 *            Sub-string to look for.
	 * @return Count of substrings in string.
	 */
	public static int countInstances(final String string, final String substring) {
		int count = 0;
		int idx = 0;

		while ((idx = string.indexOf(substring, idx)) != -1) {
			idx++;
			count++;
		}

		return count;
	}

	/**
	 * Count the number of instances of character within a string.
	 * 
	 * @param string
	 *            String to look for substring in.
	 * @param c
	 *            Character to look for.
	 * @return Count of substrings in string.
	 */
	public static int countInstances(final String string, final char c) {
		return countInstances(string, String.valueOf(c));
	}

	public boolean hasID(String line) {
		return false;
	}

	private static void makeXML(TMHVisit visit) {
		// TODO write code to add visit to a container class

	}

	private static void cleanHeader(String header) {
		header = header.replaceAll(SPACE, UNDERSCORE);
		HEADER = header.replaceAll("[^A-z_,]", UNDERSCORE);
		HEADER = HEADER + ",VISIT_ID";

	}

	public static int getMax(int A, int B) {
		if (A >= B)
			return A;
		else
			return B;
	}

	private static void writeMax(FileOutputStream oStream) throws IOException {
		String output = "";
		int index = 0;
		for (; index < NUM_FIELDS - 1; index++) {
			if (max_lengths[index] != null) {
				output = output + max_lengths[index].toString() + COMMA;
			} else {
				output = output + "0" + COMMA;
			}
		}
		if (max_lengths[index] != null) {
			output = output + max_lengths[index].toString();
		} else {
			output = output + "0";
		}
		// so no trailing comma
		oStream.write(output.getBytes());
		oStream.write(NEW_LINE.getBytes());
		oStream.flush();
	}

	static void checkMaxValues(String[] value) {
		for (int index = 0; index < NUM_FIELDS; index++) {
			if (max_lengths[index] == null) {
				max_lengths[index] = new Integer(0);
			}
			if (index != CHF_CMPLN) {
				max_lengths[index] = getMax(max_lengths[index],
						value[index].length());
			}
		}

	}

	public static int[] parseComplaint(String infile, String outfile)
			throws Exception {
		File in_file = new File(infile);
		File out_file = new File(outfile);
		File error_log = new File(infile + ERROR_LOG_EXT);
		FileInputStream is = null;
		Scanner sc = null;
		FileOutputStream oStream = null;
		FileOutputStream errors = null;
		boolean bad;
		int[] out = new int[2];
		Integer visitID = new Integer(0);
		String line;
		try {
			is = new FileInputStream(in_file);
			oStream = new FileOutputStream(out_file);
			errors = new FileOutputStream(error_log);
			sc = new Scanner(is).useDelimiter(NEW_RECORD);
			// oStream.write(HEADER.getBytes());
			// sc.useDelimiter("\\n\\d*[^A-z][,]");
		
				error_log.createNewFile();
				errors.write(HEADER.getBytes());
				errors.write(NEW_LINE.getBytes());
				errors.flush();
				
			
			while (sc.hasNext()) {
				line = sc.next();
				bad = false;
				String[] value = line.split(COMMA);
				if (value.length < NUM_FIELDS) {
					String[] temp = new String[NUM_FIELDS];
					for(int i = 0; i < NUM_FIELDS; i ++)
					{
						if(i < value.length){
						temp[i]= value[i];
						}else {
							temp[i]=BLANK;
						}
					}
					value = temp;
				}
				if(value.length == NUM_FIELDS){
					visitID++;
					TMHVisit current = new TMHVisit(); // will be used later to
														// generate xml
					current.setHSPMDNUM(value[HSP_MD_NUM]);
					current.setEncounterdiagdescr(value[ENCOUNTERDIAGDESCR]);

					current.setLchlNtk(value[LCHL_NTK]);
					current.setSmkHwMch(value[SMK_HW_MCH]);
					current.setSmkngFrmrSmkrsQtTm(value[SMKNG_FRMR_SMKRS_QT_TM]);
					current.setSmkngStts(value[SMKNG_STTS]);
					current.setTbccYrsFs(value[TBCC_YRS_FS]);
					current.setTbccYrsFsDt(value[TBCC_YRS_FS_DT]);
					current.setSurgHistProc(value[SURG_HIST_PROC]);
					current.setVisitId(visitID.toString());
					String[] complaints = value[CHF_CMPLN].split(AND);
					String head = value[HSP_MD_NUM] + COMMA;

					String tail = COMMA + value[ENCOUNTERDIAGDESCR] + COMMA
							+ value[LCHL_NTK] + COMMA + value[SMK_HW_MCH]
							+ COMMA + value[SMKNG_FRMR_SMKRS_QT_TM] + COMMA
							+ value[SMKNG_STTS] + COMMA + value[TBCC_YRS_FS]
							+ COMMA + value[TBCC_YRS_FS_DT] + COMMA
							+ value[SURG_HIST_PROC] + COMMA
							+ visitID.toString();
					checkMaxValues(value);
					for (String complaint : complaints) {
						current.getChfCmpln().add(complaint);
						max_lengths[CHF_CMPLN] = getMax(complaint.length(),
								max_lengths[CHF_CMPLN]);
						String text = head + complaint + tail;
						oStream.write(text.getBytes());
						oStream.write(NEW_LINE.getBytes());
						oStream.flush();
					}
					makeXML(current);

				} else {
					bad = true;

				}

				if (bad) {
					out[BAD_COUNT]++;
					
					errors.write(line.getBytes());
					errors.write(NEW_LINE.getBytes());
					errors.flush();
				}

				out[GOOD_COUNT]++;
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
				if (oStream != null) {
					oStream.close();
				}
				if (errors != null) {
					errors.close();
				}

			} catch (Exception e) {

				throw (e);
			}
		}
		return out;
	}

	public static int[] removeDuplicateRecords(String infile, String outfile)
			throws Exception {
		int[] out = new int[2];
		File in_file = new File(infile);
		File out_file = new File(outfile);
		File error_log = new File(infile + ERROR_LOG_EXT);
		FileInputStream is = null;
		Scanner sc = null;
		FileOutputStream oStream = null;
		FileOutputStream errors = null;
		boolean bad;
		String line;
		try {
			is = new FileInputStream(in_file);
			oStream = new FileOutputStream(out_file);
			errors = new FileOutputStream(error_log);
			sc = new Scanner(is).useDelimiter(NEW_RECORD);
			writeMax(oStream);
			oStream.write(HEADER.getBytes());
			oStream.write(NEW_LINE.getBytes());

			oStream.flush();
			ArrayList<String> records = new ArrayList<String>();
		
				error_log.createNewFile();
				errors.write(HEADER.getBytes());
				errors.write(NEW_LINE.getBytes());
				errors.flush();
				
			
			while (sc.hasNextLine()) {
				line = sc.nextLine();
				String crushed = line.replaceAll(SPACE, BLANK);
				bad = false;

				if (records.contains(crushed)) {
					bad = true;
				} else {
					records.add(crushed);
				}

				if (bad) {
					
					errors.write(line.getBytes());
					errors.write(NEW_LINE.getBytes());
					errors.flush();
					out[BAD_COUNT]++;
				} else {
					if (DEBUG) {
						line = line + out[GOOD_COUNT];
					}
					oStream.write(line.getBytes());
					oStream.write(NEW_LINE.getBytes());
					oStream.flush();

				}
				out[GOOD_COUNT]++;
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
				if (oStream != null) {
					oStream.close();
				}
				if (errors != null) {
					errors.close();
				}

			} catch (Exception e) {

				throw (e);
			}
		}
		return out;
	}

	public static int[] validateRows(String infile, String outfile)
			throws Exception {
		int[] out = new int[2];
		File in_file = new File(infile);
		File out_file = new File(outfile);
		File error_log = new File(infile + ERROR_LOG_EXT);
		FileInputStream is = null;
		Scanner sc = null;
		FileOutputStream oStream = null;
		FileOutputStream errors = null;
		boolean bad;
		String line;
		try {
			is = new FileInputStream(in_file);
			oStream = new FileOutputStream(out_file);
			errors = new FileOutputStream(error_log);
			sc = new Scanner(is).useDelimiter(NEW_RECORD);
		
				error_log.createNewFile();
				errors.write(HEADER.getBytes());
				errors.write(NEW_LINE.getBytes());
				errors.flush();
				
			while (sc.hasNextLine()) {
				line = sc.nextLine();
				bad = false;
				int idIndex = line.indexOf(COMMA);
				if (idIndex > 1) {
					String ID = line.substring(0, idIndex);
					String[] subs = ID.split(NOT_NUM);
					if (subs.length > 1 || ID.matches(NOT_NUM)) {
						bad = true;
					}

				} else // no id or invalid record
				{
					bad = true;
				}
				if (bad) {
					
					errors.write(line.getBytes());
					errors.write(NEW_LINE.getBytes());
					errors.flush();
					out[BAD_COUNT]++;
				} else {
					if (DEBUG) {
						line = line + out[GOOD_COUNT];
					}
					oStream.write(line.getBytes());
					oStream.write(NEW_LINE.getBytes());
					oStream.flush();

				}
				out[GOOD_COUNT]++;
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
				if (oStream != null) {
					oStream.close();
				}
				if (errors != null) {
					errors.close();
				}

			} catch (Exception e) {

				throw (e);
			}
		}
		return out;
	}

	public static int[] cleanRows(String infile, String outfile)
			throws Exception {
		File in_file = new File(infile);
		File out_file = new File(outfile);
		File error_log = new File(infile + ERROR_LOG_EXT);
		FileInputStream is = null;
		Scanner sc = null;
		FileOutputStream oStream = null;
		FileOutputStream errors = null;
		boolean bad;
		int[] out = new int[2];
		String line;
		try {
			is = new FileInputStream(in_file);
			oStream = new FileOutputStream(out_file);
			errors = new FileOutputStream(error_log);
			sc = new Scanner(is).useDelimiter(NEW_RECORD);
			// sc.useDelimiter("\\n\\d*[^A-z][,]");
			cleanHeader(sc.next());
			
				error_log.createNewFile();
				errors.write(HEADER.getBytes());
				errors.write(NEW_LINE.getBytes());
				errors.flush();
				
			
			while (sc.hasNext()) {
				line = sc.next();
				line = line.replaceAll(ILLLEGAL_CHARS, SPACE);
				bad = false;
				int commas = countInstances(line, COMMA);

				if (commas != FIELD_BREAKS) {
					bad = true;
					char cr = 10;
					char lf = 13;
					line = line.replaceAll(String.valueOf(cr), BLANK);
					line = line.replaceAll(String.valueOf(lf), AND);
					line = line.replaceAll(NEW_LINE, AND);
					line = clearCommas(line);
					commas = countInstances(line, COMMA);
					if (commas != FIELD_BREAKS) {
						line = clean_using_ntk(line);
						commas = countInstances(line, COMMA);
						if(commas < FIELD_BREAKS)
						{
							line = restore_ntk_comma(line);
							commas = countInstances(line, COMMA);
						}
						else if(commas != FIELD_BREAKS)
						{
							line = clear_using_end(line);
							commas = countInstances(line, COMMA);
						}
							
						//end
						if (commas != FIELD_BREAKS) {

							
							out[BAD_COUNT]++;
							errors.write(line.getBytes());
							errors.write(NEW_LINE.getBytes());
							errors.flush();
						} // end
						else {
							bad = false;
						}
					} else {
						bad = false;
					}

				}
				if (!bad) {
					oStream.write(line.getBytes());
					oStream.write(NEW_LINE.getBytes());
					oStream.flush();
				}

				out[GOOD_COUNT]++;

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
				if (oStream != null) {
					oStream.close();
				}
				if (errors != null) {
					errors.close();
				}

			} catch (Exception e) {

				throw (e);
			}
		}
		return out;
	}

	public static String clearCommas(String line) {
		int index = 0;
		int endex = 1;
		boolean changed = false;
		String front, middle, end;
		front = BLANK;
		middle = line;
		end = "";
		while (endex > index && index >= 0) {

			index = middle.indexOf(QUOTE);
			endex = middle.indexOf(QUOTE, index + 1);
			if (endex > index) {
				try {
					changed = true;
					front = front + middle.substring(0, index);
					end = middle.substring(endex + 1);
					middle = middle.substring(index + 1, endex);
					front = front + middle.replaceAll(COMMA, AND);
					middle = end;
				} catch (Exception e) {
					System.out.println(e.toString());
				}

			}
		}
		if (changed) {
			line = front + end;
		}
		return line;

	}

	public void loadFile(String filePath) throws Exception {

	}

	@Override
	public void loadFileShort(String filePath) throws Exception {

	}

	public static String restore_ntk_comma(String line)
	{
		line = line.toUpperCase();
		int start = line.indexOf(COMMA);
		int end = -1;
		for (int index = 0; index < NTK_TYPE.length; index++) {
			int temp = line.indexOf(NTK_TYPE[index], start + 1);
			if (temp > end)
				end = temp;
		}
		if(end > 1){
			String front = line.substring(0,end);
			String tail = line.substring(end);
		line = front + COMMA + tail;
		}
		return line;
	}
	public static String clear_using_end(String line)
	{
		int tail_index=-1;
		int count =0;
		String tail ="";
		String head = line;
		for(count =0; count < TAIL_LENGTH; count++)
		{
			tail_index = head.lastIndexOf(COMMA);
			if(tail_index < 1)
			{
				break;
			}
			tail = head.substring(tail_index) + tail;
			head = head.substring(0,tail_index);
		}
		if(count == TAIL_LENGTH)
		{
			int first = head.indexOf(COMMA);
			String front = head.substring(0,first +1);
			String middle = head.substring(first);// 
			middle = middle.replaceAll(COMMA, AND);// is now chfcomplnt
			line = front + middle + COMMA + COMMA+ tail;
			
		}
		return line;
	}
	public static String clean_using_ntk(String line) {
		line = line.toUpperCase();
	 
		int start = line.indexOf(COMMA);
		int end = -1;
		for (int index = 0; index < NTK_TYPE.length; index++) {
			int temp = line.indexOf(NTK_TYPE[index], start + 1);
			if (temp > end){
				end = temp;
				String sub = line.substring(temp);
				int nextComma = sub.indexOf(COMMA);
				if(NTK_TYPE[index].length()<= nextComma && NTK_TYPE[index].length() +3 >= nextComma){
					break;
				}
			}
		}
		if (end > start) {
			String front = line.substring(0, start+1);
			String middle = line.substring(start+1, end);
			String tail = line.substring(end);
			if (countInstances(middle, COMMA) > 1) {
				// removes all commas merging chf cmpl and encouterDiaDesc
				String [] parts = middle.split(COMMA);
				middle = BLANK;
				for(int i = 0; i < parts.length ; i ++)
				{
					if(parts[i].length()>1)
					{
						middle = middle + AND + parts[i];
					}
				}
				middle = middle + COMMA;
			}
			line = front + middle + tail;
		}
		return line;
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

	@Override
	public int showEditor() {
		TextDelimiterEditorDlg editor = new TextDelimiterEditorDlg();
		int result = editor.showDialog();
		if (result == AbsEditorDlg.OK) {
			setDelimiters(editor.getDelimiters());
		}
		return result;

	}

	@Override
	public Object[] getElements(Object inputElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ColumnData> parseFile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Column getColumn() {
		// TODO Auto-generated method stub
		return null;
	}

}
