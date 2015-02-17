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

package edu.fsu.idiginfo.i2b2.fileMapper.ws;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.harvard.i2b2.common.util.jaxb.JAXBUtilException;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.i2b2message.BodyType;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.i2b2message.MessageHeaderType;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.i2b2message.RequestHeaderType;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.i2b2message.RequestMessageType;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.DataType;


public class GetDimensionsRequestMessage extends FileMapperRequestData {
	
	public static final String THIS_CLASS_NAME = GetDimensionsRequestMessage.class.getName();
    private Log log = LogFactory.getLog(THIS_CLASS_NAME);	

    public GetDimensionsRequestMessage() {}
	
	/**
	 * Function to build get return type for a given request
	 * 
	 * @return GetReturnType object
	 */
	public DataType getDataType() { 
		DataType returnType = new DataType();		
		return returnType;
	}
		
	/**
	 * Function to build returnType body type
	 * 
	 * @param 
	 * @return BodyType object
	 */
	
	public BodyType getBodyType() {
		DataType returnType = getDataType();
		edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.ObjectFactory of = new edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.ObjectFactory();
		
		BodyType bodyType = new BodyType();
		bodyType.getAny().add(of.createGetDataType(returnType));
		return bodyType;
	}
	
	/**
	 * Function to build returnType body type
	 * 
	 * @param GetReturnType returnType
	 * @return BodyType object
	 */
	
	public BodyType getBodyType(DataType returnType) {
		edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.ObjectFactory of = new edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.ObjectFactory();
		
		BodyType bodyType = new BodyType();
		bodyType.getAny().add(of.createGetDataType(returnType));
		return bodyType;
	}
	
	/**
	 * Function to build Ont Request message type and return it as an XML string
	 * 
	 * @param GetReturnType returnData 
	 * @return A String data type containing the Ont RequestMessage in XML format
	 */
	public String doBuildXML(DataType datatype){ 
		String requestString = null;
			try {
				MessageHeaderType messageHeader = getMessageHeader(); 
				RequestHeaderType reqHeader  = getRequestHeader();
				BodyType bodyType = getBodyType(datatype) ;
				RequestMessageType reqMessageType = getRequestMessageType(messageHeader,
						reqHeader, bodyType);
				requestString = getXMLString(reqMessageType);
			} catch (JAXBUtilException e) {
				log.error(e.getMessage());
			} 
		return requestString;
	}

}
