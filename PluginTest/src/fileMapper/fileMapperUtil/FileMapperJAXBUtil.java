/*
 * Copyright (c) 2006-2014 Massachusetts General Hospital 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the i2b2 Software License v2.1 
 * which accompanies this distribution. 
 * 
 * Contributors:
 * 		Raj Kuttan
 * 		Lori Phillips
 */
package fileMapper.fileMapperUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import fileMapper.fileMapperUtil.JAXBConstant;

public class FileMapperJAXBUtil {

	private static edu.harvard.i2b2.common.util.jaxb.JAXBUtil jaxbUtil = null;
	private static edu.harvard.i2b2.common.util.jaxb.JAXBUtil jaxbDndUtil = null;
	private static JAXBContext jaxbContext = null;
	private static ClassLoader loader = null;
	private FileMapperJAXBUtil() {
		loader = getClass().getClassLoader();
	}

	public static edu.harvard.i2b2.common.util.jaxb.JAXBUtil getJAXBUtil() {
		if (jaxbUtil == null) {
			jaxbUtil = new edu.harvard.i2b2.common.util.jaxb.JAXBUtil(
					JAXBConstant.DEFAULT_PACKAGE_NAME);
		}
		return jaxbUtil;
	}

	public static edu.harvard.i2b2.common.util.jaxb.JAXBUtil getJAXBDndUtil() {
		if (jaxbDndUtil == null) {
			jaxbDndUtil = new edu.harvard.i2b2.common.util.jaxb.JAXBUtil(
					JAXBConstant.DND_PACKAGE_NAME);
		}
		return jaxbDndUtil;
	}

	public static JAXBContext getJAXBContext(Class jaxbClass) throws JAXBException {

		if (jaxbContext == null) {
			if (jaxbClass != null) {
				jaxbContext = JAXBContext.newInstance(jaxbClass);
			} else {
				String packages = makePackageString(JAXBConstant.DEFAULT_PACKAGE_NAME);
				if(loader == null){
					loader = FileMapperJAXBUtil.class.getClassLoader();
				}
				jaxbContext = JAXBContext.newInstance(packages, loader);
			}
		}

		return jaxbContext;
	}

	public static Unmarshaller getUnmarshaller(Class jaxbClass) throws JAXBException {
		Unmarshaller unmarshaller = getJAXBContext(jaxbClass)
				.createUnmarshaller();
		return unmarshaller;
	}

	public static String makePackageString(String[] packageName) {
		StringBuffer givenPackageName = new StringBuffer();

		for (int i = 0; i < packageName.length; i++) {
			givenPackageName.append(packageName[i]);

			if ((i + 1) < packageName.length) {
				givenPackageName.append(":");
			}
		}

		return givenPackageName.toString();
	}

}
