/*
 * Copyright (c) 2006-2014 Massachusetts General Hospital 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the i2b2 Software License v2.1 
 * which accompanies this distribution. 
 * 
 * Contributors:
 * 	    Raj Kuttan
 * 		Lori Phillips
 */

package fileMapper.ws;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.harvard.i2b2.common.util.jaxb.JAXBUtilException;
import fileMapper.data.datavo.i2b2message.BodyType;
import fileMapper.data.datavo.i2b2message.MessageHeaderType;
import fileMapper.data.datavo.i2b2message.RequestHeaderType;
import fileMapper.data.datavo.i2b2message.RequestMessageType;
import fileMapper.data.datavo.vdo.UpdateCrcConceptType;


/**
 * @author Raj Kuttan, Lori Phillips
 *
 */
public class SynchronizeRequestMessage extends FileMapperRequestData {
	
	public static final String THIS_CLASS_NAME = SynchronizeRequestMessage.class.getName();
    private Log log = LogFactory.getLog(THIS_CLASS_NAME);	

    public SynchronizeRequestMessage() {}
	
	/**
	 * Function to build get return type for a given request
	 * 
	 * @return GetReturnType object
	 */
	public UpdateCrcConceptType getUpdateCrcConceptType(String operationType, boolean includeHiddens) { 
		UpdateCrcConceptType returnType = new UpdateCrcConceptType();		
		returnType.setOperationType(operationType);
		returnType.setHiddens(includeHiddens);
		return returnType;
	}
	
	

	
	/**
	 * Function to build vocabData body type
	 * 
	 * @param 
	 * @return BodyType object
	 */
	
	public BodyType getBodyType(UpdateCrcConceptType returnType) {
		fileMapper.data.datavo.vdo.ObjectFactory of = new fileMapper.data.datavo.vdo.ObjectFactory();
		
		BodyType bodyType = new BodyType();
		bodyType.getAny().add(of.createUpdateCrcConcept(returnType));
		return bodyType;
	}
	
	/**
	 * Function to build Ont Request message type and return it as an XML string
	 * 
	 * @param UpdateCrcConceptType returnType is the update crc concept request message
	 * @return A String data type containing the Ont RequestMessage in XML format
	 */
	public String doBuildXML(UpdateCrcConceptType returnType){ 
		String requestString = null;
			try {
				MessageHeaderType messageHeader = getMessageHeader(); 
				RequestHeaderType reqHeader  = getRequestHeader();
				BodyType bodyType = getBodyType(returnType) ;
				RequestMessageType reqMessageType = getRequestMessageType(messageHeader,
						reqHeader, bodyType);
				requestString = getXMLString(reqMessageType);
			} catch (JAXBUtilException e) {
				log.error(e.getMessage());
			} 
		return requestString;
	}
}


	
	