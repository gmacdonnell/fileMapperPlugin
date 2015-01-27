package edu.fsu.idiginfo.i2b2.plugin.filemapping.util;

public class FileMappingJAXUtil {
	

		private static edu.harvard.i2b2.common.util.jaxb.JAXBUtil jaxbUtil = null;
		private static edu.harvard.i2b2.common.util.jaxb.JAXBUtil jaxbDndUtil = null;
		private FileMappingJAXUtil() { 
		}
		
		
		public static edu.harvard.i2b2.common.util.jaxb.JAXBUtil getJAXBUtil() {
			if (jaxbUtil == null) {
				String [] defaultPackage = new String[]{Messages.getString("FileMapping.DefaultPackageName")};
				jaxbUtil = new edu.harvard.i2b2.common.util.jaxb.JAXBUtil(defaultPackage);
			}
			return jaxbUtil;
		}
		public static edu.harvard.i2b2.common.util.jaxb.JAXBUtil getJAXBDndUtil() {
			if (jaxbDndUtil == null) {
				String [] DndtPackage = new String[]{Messages.getString("FileMapping.DndPackageName")};
				jaxbDndUtil = new edu.harvard.i2b2.common.util.jaxb.JAXBUtil(DndtPackage);
			}
			return jaxbDndUtil;
		}
		
	

}
