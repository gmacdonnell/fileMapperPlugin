package plugintest.ui;

import java.util.ArrayList;
import java.util.List;

public enum TermProvider {
	instance;
	
	public List<RequiredTerm> getModel(){
		List<RequiredTerm> list = new ArrayList<RequiredTerm>();
		list.add(new RequiredTerm("Patent_ID","OB_FACT"));
		list.add(new RequiredTerm("Diagnosis","OB_FACT"));
		list.add(new RequiredTerm("Provider_ID","Provider_Dimension"));
		list.add(new RequiredTerm("Test","I2B2"));
		return list;
		
	}

}
