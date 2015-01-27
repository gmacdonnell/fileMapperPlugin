package edu.fsu.idiginfo.i2b2.filemapping.requiredfields;
import java.math.BigDecimal;
import java.util.Date;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.JAXBElement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.harvard.i2b2.common.util.jaxb.DTOFactory;
import edu.harvard.i2b2.common.util.jaxb.JAXBUtilException;
import edu.fsu.idiginfo.i2b2.plugin.filemapping.util.*;
import edu.harvard.i2b2.eclipse.UserInfoBean;


import edu.fsu.idiginfo.i2b2.fmpclient.datavo.psm.query.*;

import edu.fsu.idiginfo.i2b2.fmpclient.datavo.psm.query.PsmQryHeaderType;
import edu.fsu.idiginfo.i2b2.fmpclient.datavo.requiredFields.*;
import edu.fsu.idiginfo.i2b2.plugin.filemapping.util.*;
import edu.harvard.i2b2.common.util.jaxb.DTOFactory;




public class RequestTables {
	public static final String THIS_CLASS_NAME = RequestTables.class.getName();
    private Log log = LogFactory.getLog(THIS_CLASS_NAME);
	
	public String Test()
	{
		return "Hello";
	}
	
	public String doBuildXML(ResultRequestType resultData){ 
		String requestString = null;
			try {
				MessageHeaderType messageHeader = getMessageHeader(); 	
				RequestHeaderType reqHeader  = getRequestHeader();
				BodyType bodyType = getBodyType(resultData) ;
				RequestMessageType reqMessageType = getRequestMessageType(messageHeader,
						reqHeader, bodyType);
				requestString = getXMLString(reqMessageType);
				messageHeader = null;
				reqHeader = null;
				reqMessageType = null;
			} catch (JAXBUtilException e) {
				log.error(e.getMessage());
			} 

		return requestString;
	}
	
	public BodyType getBodyType(ResultRequestType requestType) {
		
		PsmQryHeaderType headerType = buildPsmHeaderType(PsmRequestTypeType.CRC_QRY_GET_RESULT_DOCUMENT_FROM_RESULT_INSTANCE_ID);
		edu.fsu.idiginfo.i2b2.fmpclient.datavo.psm.query.ObjectFactory of = new edu.fsu.idiginfo.i2b2.fmpclient.datavo.psm.query.ObjectFactory();
		
		BodyType bodyType = new BodyType();
		bodyType.getAny().add(of.createPsmheader(headerType));
		bodyType.getAny().add(of.createRequest(requestType));
		
		return bodyType;
	}
	
	public String getXMLString(RequestMessageType reqMessageType) throws JAXBUtilException{ 
		StringWriter strWriter = null;
		try {
			strWriter = new StringWriter();
			edu.fsu.idiginfo.i2b2.fmpclient.datavo.requiredFields.ObjectFactory of = new edu.fsu.idiginfo.i2b2.fmpclient.datavo.requiredFields.ObjectFactory();
			edu.harvard.i2b2.common.util.jaxb.JAXBUtil jaxbUtil = FileMappingJAXUtil.getJAXBUtil();
			JAXBElement<RequestMessageType> RMT = of
					.createRequest(reqMessageType);
			jaxbUtil.marshaller(RMT, strWriter);
			return strWriter.toString();
		} catch (JAXBUtilException e) {
			log.error("Error marshalling CRC request message");
			throw e;
		} 
	
	}
	private PsmQryHeaderType buildPsmHeaderType(PsmRequestTypeType type){ 
		PsmQryHeaderType psmHeader = new PsmQryHeaderType();

		psmHeader.setEstimatedTime(180000);
		psmHeader.setRequestType(type);
		return psmHeader;
	}
	public RequestHeaderType getRequestHeader() { 
		RequestHeaderType reqHeader = new RequestHeaderType();
		reqHeader.setResultWaittimeMs(180000);
		return reqHeader;
	}
	public MessageHeaderType getMessageHeader() {
		MessageHeaderType messageHeader = new MessageHeaderType();
		
		messageHeader.setI2B2VersionCompatible(new BigDecimal(Messages.getString("FileMappingRequestData.i2b2VersionCompatible"))); //$NON-NLS-1$

		ApplicationType appType = new ApplicationType();
		appType.setApplicationName(Messages.getString("FileMappingRequestData.SendingApplicationName")); //$NON-NLS-1$
		appType.setApplicationVersion(Messages.getString("FileMappingRequestData.SendingApplicationVersion"));  //$NON-NLS-1$
		messageHeader.setSendingApplication(appType);
		
		FacilityType facility = new FacilityType();
		facility.setFacilityName(Messages.getString("FileMappingRequestData.SendingFacilityName")); //$NON-NLS-1$
		messageHeader.setSendingFacility(facility);
		
		ApplicationType appType2 = new ApplicationType();
		appType2.setApplicationVersion(Messages.getString("CRCRequestData.ReceivingApplicationVersion")); //$NON-NLS-1$
		appType2.setApplicationName(Messages.getString("CRCRequestData.ReceivingApplicationName"));		 //$NON-NLS-1$
		messageHeader.setReceivingApplication(appType2);
	
		FacilityType facility2 = new FacilityType();
		facility2.setFacilityName(Messages.getString("CRCRequestData.ReceivingFacilityName")); //$NON-NLS-1$
		messageHeader.setReceivingFacility(facility2);

		Date currentDate = new Date();
		DTOFactory factory = new DTOFactory();
		messageHeader.setDatetimeOfMessage(factory.getXMLGregorianCalendar(currentDate.getTime()));
		
		SecurityType secType = new SecurityType();
		secType.setDomain(UserInfoBean.getInstance().getUserDomain());
		secType.setUsername(UserInfoBean.getInstance().getUserName());
		PasswordType ptype = new PasswordType();
		ptype.setIsToken(UserInfoBean.getInstance().getUserPasswordIsToken());
		ptype.setTokenMsTimeout(UserInfoBean.getInstance().getUserPasswordTimeout());
		ptype.setValue(UserInfoBean.getInstance().getUserPassword());

		secType.setPassword(ptype);
		messageHeader.setSecurity(secType);
		
		MessageControlIdType mcIdType = new MessageControlIdType();
		mcIdType.setInstanceNum(0);
		mcIdType.setMessageNum(generateMessageId());
		messageHeader.setMessageControlId(mcIdType);

		ProcessingIdType proc = new ProcessingIdType();
		proc.setProcessingId(Messages.getString("FileMappingRequestData.ProcessingId")); //$NON-NLS-1$
		proc.setProcessingMode(Messages.getString("FileMappingRequestData.ProcessingMode")); //$NON-NLS-1$
		messageHeader.setProcessingId(proc);
		
		messageHeader.setAcceptAcknowledgementType(Messages.getString("FileMappingRequestData.AcceptAcknowledgementType")); //$NON-NLS-1$
		messageHeader.setApplicationAcknowledgementType(Messages.getString("FileMappingRequestData.ApplicationAcknowledgementType")); //$NON-NLS-1$
		messageHeader.setCountryCode(Messages.getString("FileMappingRequestData.CountryCode")); //$NON-NLS-1$
		messageHeader.setProjectId(UserInfoBean.getInstance().getProjectId());
		return messageHeader;
	}
	public RequestMessageType getRequestMessageType(MessageHeaderType messageHeader,
			RequestHeaderType reqHeader, BodyType bodyType) { 
		RequestMessageType reqMsgType = new RequestMessageType();
		reqMsgType.setMessageHeader(messageHeader);
		reqMsgType.setMessageBody(bodyType);
		reqMsgType.setRequestHeader(reqHeader);
		return reqMsgType;
	}
	protected String generateMessageId() {
		StringWriter strWriter = new StringWriter();
		for(int i=0; i<20; i++) {
			int num = getValidAcsiiValue();
			strWriter.append((char)num);
		}
		return strWriter.toString();
	}
	private int getValidAcsiiValue() {
		int number = 48;
		while(true) {
			number = 48+(int) Math.round(Math.random() * 74);
			if((number > 47 && number < 58) || (number > 64 && number < 91) 
				|| (number > 96 && number < 123)) {
					break;
				}
		}
		return number;
	}

}
