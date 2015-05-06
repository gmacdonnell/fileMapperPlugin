package edu.fsu.idiginfo.i2b2.fileMapper.fileMapperUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

	public static String TYPE = "DelimitedFile";
	protected ArrayList<String> Lines;
	protected String[] Delimiters;
	protected int FieldCount;
	protected int RowCount;
	private static String ERROR_LOG_EXT = "_error.log";
	public static String COMMA = ",";
	public static char QUOTE = '\"';
	public static String AND = ";";
	public static String NOT_NUM = "[\\D]+";
	public static String BLANK = "";
	public static String NEW_RECORD = "\\r\\d*[^A-z]";
	public static String NEW_LINE = System.getProperty("line.separator");
	public static int CHUNCK = 100;
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
	public static int GOOD_COUNT = 0;
	public static int BAD_COUNT = 1;

	public static void main(String[] args) {
		// for testing
		String InFile = "C:\\Users\\GMACDONNELL\\Desktop\\TMH_TESTING\\FSU_Dx.csv";
		String OutFile = "C:\\Users\\GMACDONNELL\\Desktop\\TMH_TESTING\\FSU_DX_CLEANED.csv";
		String VOutFile = "C:\\Users\\GMACDONNELL\\Desktop\\TMH_TESTING\\FSU_Valid.csv";
		String COutFile = "C:\\Users\\GMACDONNELL\\Desktop\\TMH_TESTING\\FSU_Comp.csv";

		try {
			File out = new File(OutFile);
			File errors = new File(InFile + ERROR_LOG_EXT);
			File verrors = new File(OutFile + ERROR_LOG_EXT);
			File cerrors = new File(VOutFile + ERROR_LOG_EXT);
			File vout = new File(VOutFile);
			File cout = new File(COutFile);
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
			if (vout.exists()) {
				vout.delete();
				vout = null;
			}
			if (cout.exists()) {
				cout.delete();
				cout = null;
			}
			int[] count = cleanRows(InFile, OutFile);
			System.out.println("Total rows cleaned: " + count[GOOD_COUNT]);
			System.out.println("Total Error Records: " + count[BAD_COUNT]);
			int[] vcount = validateRows(OutFile, VOutFile);
			System.out.println("Total rows  " + vcount[GOOD_COUNT]);
			System.out.println("Total invalid rows: " + vcount[BAD_COUNT]);
			int[] ccount = parseComplaint(VOutFile, COutFile);
			System.out.println("Total rows into parseComplaint"
					+ ccount[GOOD_COUNT]);
			System.out.println("Total Error Records: " + ccount[BAD_COUNT]);
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
		Integer  visitID = new Integer(0);
		String line;
		try {
			is = new FileInputStream(in_file);
			oStream = new FileOutputStream(out_file);
			errors = new FileOutputStream(error_log);
			sc = new Scanner(is).useDelimiter(NEW_RECORD);
			// sc.useDelimiter("\\n\\d*[^A-z][,]");
			while (sc.hasNext()) {
				line = sc.next();
				bad = false;
				String[] value = line.split(COMMA);
				if (value.length == 10) {
					visitID++;
					TMHVisit current = new TMHVisit();
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
					for (String complaint : complaints) {
						current.getChfCmpln().add(complaint);
						String text = current.getHSPMDNUM() + COMMA + complaint + COMMA + visitID.toString();
						oStream.write(text.getBytes());
						oStream.write(NEW_LINE.getBytes());
						oStream.flush();
					}

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
					line = line + out[GOOD_COUNT];
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
			while (sc.hasNext()) {
				line = sc.next();
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
						if (!error_log.exists()) {
							error_log.createNewFile();
						}
						out[BAD_COUNT]++;
						errors.write(line.getBytes());
						errors.write(NEW_LINE.getBytes());
						errors.flush();
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
