package fileMapper.fileMapperUtil;

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.eclipse.jface.viewers.Viewer;



public class DelimitedFileParser implements  IFileParser {

	
	
	
	protected String[] Lines;
	protected String[] Delimiters;
	protected int FieldCount;
	
	public DelimitedFileParser(List<Object> delimiters)
	{
		setDelimiters(delimiters);
		Lines = new String[SAMPLES];
		
	}	
	public DelimitedFileParser()
	{
	
		Lines = new String[SAMPLES];
		
	}
	

	public String[][] getItems()
	{
		return (String[][])parseFile().toArray();
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadFile(String filePath) throws Exception {
		FileInputStream is = null;
		Scanner sc = null;
		try{
			is = new FileInputStream(filePath);
			sc = new Scanner(is);
			int count =0;

			while( sc.hasNextLine() && count < SAMPLES)
			{		
				
				Lines[count] = sc.nextLine();
				if(count==0)
				{
					FieldCount = ParseLine(Lines[count]).length;
					
				}
				count++;
			}
		
			
		}catch(Exception e)
		{
			
			throw(e);
		}
		finally{
			try{
				if(is != null){
					is.close();
				}
				  if (sc != null) {
				        sc.close();
				    }
				
			}catch(Exception e)
			{
				
				throw(e);
			}
		}
		
	}
		
	

	@Override
	public void setDelimiters(List<Object> del) {
		Object[] input = del.toArray();
		Delimiters = new String[input.length];
			for(int index = 0 ; index < input.length; index++)
			{
				if(input[index] instanceof String)
				{
					Delimiters[index] =(String) input[index];
				}
			}
		
	}

	@Override
	public int getFieldCount() {
		
		return FieldCount;
	}

	@Override
	public List<String[]> parseFile() {
		List<String[]>records = new LinkedList<String[]>();
		for(int index=0; index < SAMPLES; index++)
		{
			records.add( ParseLine(Lines[index]));
		}
		return records;
	}
	
	public String[] ParseLine(String line)
	{
		String[] fields = new String[1];
		fields[0]=line;
		String[] newFields = null;
		if(Delimiters != null){
			for( String del : Delimiters)
			{
				
				for(String block : fields)
				{
					newFields = Combine(newFields, ParseBlock(block,del));
				}
				fields = newFields;
				
			}
		}
		return fields;
	}
	
	private String[] ParseBlock(String line, String del)
	{
		return line.split(del);
		
	}
	private String[] Combine(String[] A, String[] B)
	{
		int size =0;
		if(A != null){size += A.length;}
		if(B != null){size += B.length;}
		String[] out = new String[size];
		int index = 0;
		if(A != null){
			for(String s : A)
			{
				out[index]=s;
				index++;
			}
		}
		if(B != null)
		{
			for(String s : B)
			{
				out[index]= s;
				index++;
			}
		}
		return out;
	}


	@Override
	public Object[] getElements(Object inputElement) {
		// TODO Auto-generated method stub
		if(inputElement instanceof String[][])
		{
			String[][] in =(String[][])inputElement;
			String[] out = in[0];
			int index = 1;
			while(index < in.length)
			{
				out = Combine(out,in[index]);
				index++;
			}
			return out;
		}
		return null;
	}






	


	
}
