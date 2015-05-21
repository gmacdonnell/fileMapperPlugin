/*
 * Copyright (c) 2006-2014 Massachusetts General Hospital 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the i2b2 Software License v2.1 
 * which accompanies this distribution. 
 * 
 * Contributors:
 * 		Lori Phillips
 *      Raj Kuttan
 */

package edu.fsu.idiginfo.i2b2.fileMapper.ws;

import java.io.StringReader;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.Node;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axiom.soap.SOAPFactory;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.OperationClient;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xerces.dom.ElementNSImpl;
//import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;
import edu.harvard.i2b2.eclipse.UserInfoBean;

import edu.fsu.idiginfo.i2b2.fileMapper.fileMapperUtil.FileMapperJAXBUtil;
import edu.fsu.idiginfo.i2b2.fileMapper.fileMapperUtil.messaging.MessageUtil;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.i2b2message.BodyType;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.i2b2message.MessageHeaderType;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.i2b2message.RequestMessageType;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.i2b2message.ResponseMessageType;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.DataType;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.DataTypeField;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.GetDataTypes;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo.GetKeys;

import edu.harvard.i2b2.common.exception.I2B2Exception;
import edu.harvard.i2b2.common.util.jaxb.JAXBUnWrapHelper;
import edu.harvard.i2b2.common.util.jaxb.JAXBUtilException;
import edu.harvard.i2b2.common.util.xml.*;

public class FileMapperServiceDriver {

	public static final String THIS_CLASS_NAME = FileMapperServiceDriver.class
			.getName();
	private static Log log = LogFactory.getLog(THIS_CLASS_NAME);
	private static String serviceURL = UserInfoBean.getInstance()
			.getCellDataUrl("MAP");
	private static String serviceMethod = UserInfoBean.getInstance()
			.getCellDataMethod("MAP").toUpperCase();
	private static EndpointReference soapEPR = new EndpointReference(serviceURL);

	private static EndpointReference dataTypesERP = new EndpointReference(
			serviceURL + "getDataTypes");

	private static EndpointReference keysEPR = new EndpointReference(serviceURL
			+ "getKeys");

	private static EndpointReference dimensionsEPR = new EndpointReference(
			serviceURL + "getDimensions");

	private static EndpointReference servicesEPR = new EndpointReference(
			serviceURL + "getServices");

	public static OMElement getVersion() {
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace(
				"http://axisversion.sample/xsd", "tns");

		OMElement method = fac.createOMElement("getVersion", omNs);

		return method;
	}

	
	public static GetDataTypes extractTypes(String resltMessage) throws I2B2Exception
	{
		
	try{
		JAXBElement jaxbElement = FileMapperJAXBUtil.getJAXBUtil()
				.unMashallFromString(resltMessage);
		ResponseMessageType respMessageType = (ResponseMessageType) jaxbElement
				.getValue();

		BodyType bodyType = respMessageType.getMessageBody();
		
		
			Unmarshaller unmarshaller = FileMapperJAXBUtil
					.getUnmarshaller(GetDataTypes.class);
			for(int index = 0; index < bodyType.getAny().size(); index ++)
			{
				Object temp = bodyType.getAny().get(index);
				if(temp.getClass() == JAXBElement.class)
				{
					GetDataTypes types = (GetDataTypes)((JAXBElement)temp).getValue();
					return types;
				}
				
			}
			return null;
		
		}catch(Exception e)
		{
			log.error(e.toString());
			throw new I2B2Exception(e.getMessage());
		}
				
	}
	
	public static DataType extractTypeFields(String resltMessage) throws I2B2Exception
	{
		
	try{
		JAXBElement jaxbElement = FileMapperJAXBUtil.getJAXBUtil()
				.unMashallFromString(resltMessage);
		ResponseMessageType respMessageType = (ResponseMessageType) jaxbElement
				.getValue();

		BodyType bodyType = respMessageType.getMessageBody();
		
		
			Unmarshaller unmarshaller = FileMapperJAXBUtil
					.getUnmarshaller(DataType.class);
			for(int index = 0; index < bodyType.getAny().size(); index ++)
			{
				Object temp = bodyType.getAny().get(index);
				if(temp.getClass() == JAXBElement.class)
				{
					DataType type = (DataType)((JAXBElement)temp).getValue();
					return type;
				}
				
			}
			return null;
		
		}catch(Exception e)
		{
			log.error(e.toString());
			throw new I2B2Exception(e.getMessage());
		}
				
	}
	
		
	
	public static String getDataTypes() throws Exception {
		String response = null;
/* for testing only
		GetDataTypesRequestMessage reqMsg = new GetDataTypesRequestMessage();
		try {
			String requestString = reqMsg.doBuildXML();

			response = sendREST(dataTypesERP, requestString);
			
		} catch (AxisFault e) {
			log.error(e.getMessage());
			// throw new AxisFault(e);
		} catch (I2B2Exception e) {
			log.error(e.getMessage());
			throw new I2B2Exception(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new Exception(e);
		}
*/
		response = "<ns3:response xmlns:ns2="http://www.fsu.edu/data/datavo/vdo/" xmlns:ns3="http://www.i2b2.org/xsd/hive/msg/1.1/">
    <message_header>
        <i2b2_version_compatible>1.70</i2b2_version_compatible>
        <sending_application>
            <application_name>FileMapper Cell</application_name>
            <application_version>1.6</application_version>
        </sending_application>
        <sending_facility>
            <facility_name>i2b2 Hive</facility_name>
        </sending_facility>
        <receiving_application>
            <application_name>i2b2 FileMapper</application_name>
            <application_version>1.6</application_version>
        </receiving_application>
        <receiving_facility>
            <facility_name>i2b2 Hive</facility_name>
        </receiving_facility>
        <datetime_of_message>2015-05-21T14:12:50.492-04:00</datetime_of_message>
        <security>
            <domain>i2b2demo</domain>
            <username>gmacdonnell</username>
            <password token_ms_timeout="1800000" is_token="true">SessionKey:11krVey6ZtuInaBgunTd</password>
        </security>
        <message_control_id>
            <message_num>srZ9tt7tiMO4q2ZHySv9</message_num>
            <instance_num>1</instance_num>
        </message_control_id>
        <processing_id>
            <processing_id>P</processing_id>
            <processing_mode>I</processing_mode>
        </processing_id>
        <accept_acknowledgement_type>AL</accept_acknowledgement_type>
        <application_acknowledgement_type>AL</application_acknowledgement_type>
        <country_code>US</country_code>
        <project_id>Demo</project_id>
    </message_header>
    <response_header>
        <info>Log information</info>
        <result_status>
            <status type="DONE" />
            <polling_url interval_ms="100" />
        </result_status>
    </response_header>
    <message_body>
        <ns2:get_GetDataTypes>
            <Types>
                <name>Patient_FACT</name>
                <table_CD>OBSERVATION_FACT</table_CD>
                <isDimension>false</isDimension>
            </Types>
            <Types>
                <name>PROVIDER</name>
                <table_CD>PROVIDER_DIMENSION</table_CD>
                <isDimension>false</isDimension>
            </Types>
            <Types>
                <name>VISIT</name>
                <table_CD>VISIT_DIMENSION</table_CD>
                <isDimension>false</isDimension>
            </Types>
            <Types>
                <name>PATIENT</name>
                <table_CD>PATIENT_DIMENSION</table_CD>
                <isDimension>false</isDimension>
            </Types>
            <Types>
                <name>PATIENT_MAP</name>
                <table_CD>PATIENT_MAP</table_CD>
                <isDimension>false</isDimension>
            </Types>
        </ns2:get_GetDataTypes>
    </message_body>
</ns3:response>";
		return response;
	}

	public static String getKeys(GetDataTypes dataType) throws Exception {
		String response = null;
		GetKeysRequestMessage reqMsg = new GetKeysRequestMessage();
		try {

			String requestString = reqMsg.doBuildXML(dataType);

			response = sendREST(keysEPR, requestString);

		} catch (AxisFault e) {
			log.error(e.getMessage());
			// throw new AxisFault(e);
		} catch (I2B2Exception e) {
			log.error(e.getMessage());
			throw new I2B2Exception(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new Exception(e);
		}
		return response;
	}

	public static String getDimensions(DataType datatype) throws Exception {
		String response = null;
		List<DataTypeField> results = null;
		try {
			List<DataTypeField> fields = datatype.getFieldSet();

			GetDimensionsRequestMessage reqMsg = new GetDimensionsRequestMessage();

			String requestString = reqMsg.doBuildXML(datatype);

			response = sendREST(dimensionsEPR, requestString);

		} catch (AxisFault e) {
			log.error(e.getMessage());
			// throw new AxisFault(e);
		} catch (I2B2Exception e) {
			log.error(e.getMessage());
			throw new I2B2Exception(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new Exception(e);
		}
		return response;
	}

	public static String getServices(String service) throws Exception {
		String response = null;
		/*
		 * try {
		 * 
		 * } catch (AxisFault e) { log.error(e.getMessage()); // throw new
		 * AxisFault(e); } catch (I2B2Exception e) { log.error(e.getMessage());
		 * throw new I2B2Exception(e.getMessage()); } catch (Exception e) {
		 * log.error(e.getMessage()); throw new Exception(e); }
		 */
		return response;
	}

	/*
	 * public static String getDirtyState(GetReturnType returnData, String type)
	 * throws Exception { String response = null; try {
	 * GetDirtyStateRequestMessage reqMsg = new GetDirtyStateRequestMessage();
	 * String requestString = reqMsg.doBuildXML(returnData);
	 * 
	 * if(serviceMethod.equals("SOAP")) { response =
	 * sendSOAP(requestString,"http://rpdr.partners.org/GetCategories",
	 * "GetCategories", type ); } else { response = sendREST(dirtyStateEPR,
	 * requestString, type); } // log.debug("MAP response = " + response); }
	 * catch (AxisFault e) { log.error(e.getMessage()); //throw new
	 * AxisFault(e); } catch (I2B2Exception e) { log.error(e.getMessage());
	 * throw new I2B2Exception(e.getMessage()); } catch (Exception e) {
	 * log.error(e.getMessage()); throw new Exception(e); } return response; }
	 */

	public static OMElement getMAPPayLoad(String requestVdo) throws Exception {
		OMElement lineItem = null;
		try {
			StringReader strReader = new StringReader(requestVdo);
			XMLInputFactory xif = XMLInputFactory.newInstance();
			XMLStreamReader reader = xif.createXMLStreamReader(strReader);

			StAXOMBuilder builder = new StAXOMBuilder(reader);
			lineItem = builder.getDocumentElement();
		} catch (FactoryConfigurationError e) {
			log.error(e.getMessage());
			throw new Exception(e);
		}
		return lineItem;
	}

	public static String sendREST(EndpointReference restEPR,
			String requestString) throws Exception {
		if (UserInfoBean.getInstance().getCellDataUrl("MAP") == null) {
			throw new I2B2Exception(
					"FileMapper cell (MAP) not configured in PM");
		}
		// requestString.replaceAll("\\p{Cntrl}", ""); did not fix illegal
		// control char error
		OMElement getMAP = getMAPPayLoad(requestString);

		MessageUtil.getInstance().setNavRequest(
				"URL: " + restEPR + "\n" + getMAP.toString());

		Options options = new Options();
		log.debug(restEPR.toString());
		options.setTo(restEPR);
		options.setTransportInProtocol(Constants.TRANSPORT_HTTP);

		options.setProperty(Constants.Configuration.ENABLE_REST,
				Constants.VALUE_TRUE);
		options.setProperty(HTTPConstants.SO_TIMEOUT, new Integer(125000));
		options.setProperty(HTTPConstants.CONNECTION_TIMEOUT, new Integer(
				125000));

		ServiceClient sender = FileMapperServiceClient.getServiceClient();
		sender.setOptions(options);

		OMElement result;
		try {
			result = sender.sendReceive(getMAP);
		} catch (java.lang.OutOfMemoryError e) {
			System.gc();
			throw new I2B2Exception("Out of memory");
			// return null;
		}
		String response = result.toString();

		// ? MessageUtil.getInstance().setNavResponse("URL: " + restEPR + "\n" +
		// response);

		int timeout = processSecurityResult(response);
		// log.info("get timeout from server: "+ timeout +
		// " at: "+Calendar.getInstance().getTime());
		if (timeout != -1) {
			UserInfoBean.setLastActivityTime(Calendar.getInstance().getTime());
			UserInfoBean.getInstance().setUserPasswordTimeout(timeout);
			// log.info("get timeout from server: "+ timeout +
			// " at: "+Calendar.getInstance().getTime());
		}
		return response;

	}

	public static int processSecurityResult(String response) {
		int timeout = -1;
		try {
			JAXBElement jaxbElement = FileMapperJAXBUtil.getJAXBUtil()
					.unMashallFromString(response);
			ResponseMessageType respMessageType = (ResponseMessageType) jaxbElement
					.getValue();

			// Get response message status
			MessageHeaderType messageHeader = respMessageType
					.getMessageHeader();
			if (messageHeader.getSecurity() != null
					&& messageHeader.getSecurity().getPassword() != null
					&& messageHeader.getSecurity().getPassword()
							.getTokenMsTimeout() != null) {
				timeout = messageHeader.getSecurity().getPassword()
						.getTokenMsTimeout().intValue();
			}

			/*
			 * if (procStatus.equals("ERROR")) {
			 * log.error("Error reported by MAP web Service " + procMessage); }
			 * else if (procStatus.equals("WARNING")) {
			 * log.error("Warning reported by MAP web Service" + procMessage); }
			 */

		} catch (JAXBUtilException e) {
			log.error(e.getMessage());
		}
		return timeout;
	}

	public static String sendSOAP(String requestString, String action,
			String operation, String type) throws Exception {

		ServiceClient sender = FileMapperServiceClient.getServiceClient();
		OperationClient operationClient = sender
				.createClient(ServiceClient.ANON_OUT_IN_OP);

		// creating message context
		MessageContext outMsgCtx = new MessageContext();
		// assigning message context's option object into instance variable
		Options opts = outMsgCtx.getOptions();
		// setting properties into option
		log.debug(soapEPR);
		opts.setTo(soapEPR);
		opts.setAction(action);
		opts.setTimeOutInMilliSeconds(180000);

		log.debug(requestString);

		SOAPEnvelope envelope = null;

		try {
			SOAPFactory fac = OMAbstractFactory.getSOAP11Factory();
			envelope = fac.getDefaultEnvelope();
			OMNamespace omNs = fac.createOMNamespace(
					"http://rpdr.partners.org/", "rpdr");

			// creating the SOAP payload
			OMElement method = fac.createOMElement(operation, omNs);
			OMElement value = fac.createOMElement("RequestXmlString", omNs);
			value.setText(requestString);
			method.addChild(value);
			envelope.getBody().addChild(method);
		} catch (FactoryConfigurationError e) {
			log.error(e.getMessage());
			throw new Exception(e);
		}

		outMsgCtx.setEnvelope(envelope);

		// used to be envelope.getBody().getFirstElement().toString()
		if (type != null) {
			String request = envelope.toString();
			String formattedRequest = XMLUtil.StrFindAndReplace("&lt;", "<",
					request);
			if (type.equals("MAP")) {
				MessageUtil.getInstance().setNavRequest(
						"URL: " + soapEPR + "\n" + formattedRequest);
			}

			else {
				MessageUtil.getInstance().setFindRequest(
						"URL: " + soapEPR + "\n" + formattedRequest);
			}
		}

		operationClient.addMessageContext(outMsgCtx);
		operationClient.execute(true);

		MessageContext inMsgtCtx = operationClient.getMessageContext("In");
		SOAPEnvelope responseEnv = inMsgtCtx.getEnvelope();

		OMElement soapResponse = responseEnv.getBody().getFirstElement();

		if (type != null) {
			if (type.equals("MAP")) {
				String formattedResponse = XMLUtil.StrFindAndReplace("&lt;",
						"<", responseEnv.toString());
				String indentedResponse = XMLUtil.convertDOMToString(XMLUtil
						.convertStringToDOM(formattedResponse));
				MessageUtil.getInstance().setNavResponse(
						"URL: " + soapEPR + "\n" + indentedResponse);
			} else {
				String formattedResponse = XMLUtil.StrFindAndReplace("&lt;",
						"<", responseEnv.toString());
				String indentedResponse = XMLUtil.convertDOMToString(XMLUtil
						.convertStringToDOM(formattedResponse));
				MessageUtil.getInstance().setFindResponse(
						"URL: " + soapEPR + "\n" + indentedResponse);
			}
		}

		// System.out.println("Sresponse: "+ soapResponse.toString());
		OMElement soapResult = soapResponse.getFirstElement();
		// System.out.println("Sresult: "+ soapResult.toString());

		String i2b2Response = soapResult.getText();
		log.debug(i2b2Response);

		return i2b2Response;
	}

}
