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


/**
 * Define JAXB constants here.
 * For dynamic configuration, move these values to property file
 * and read from it.
 *
 * @author rkuttan
 */
public class JAXBConstant {
    public static final String[] DEFAULT_PACKAGE_NAME = new String[] {
            "fileMapper.data.datavo.i2b2message",
            "fileMapper.data.datavo.i2b2result",
            "fileMapper.data.datavo.vdo",
            "fileMapper.data.datavo.pdo.query",
            "fileMapper.data.datavo.psm.query"
        };
    
    public static final String[] DND_PACKAGE_NAME = new String[] {
               "fileMapper.data.datavo.vdo"
    };
}
