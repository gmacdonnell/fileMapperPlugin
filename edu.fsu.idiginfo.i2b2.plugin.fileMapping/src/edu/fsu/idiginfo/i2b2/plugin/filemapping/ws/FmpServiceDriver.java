package edu.fsu.idiginfo.i2b2.plugin.filemapping.ws;

import java.io.StringReader;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.fsu.idiginfo.i2b2.filemapping.requiredfields.RequestTables;
import edu.fsu.idiginfo.i2b2.fmpclient.datavo.psm.query.ResultRequestType;
import edu.fsu.idiginfo.i2b2.plugin.filemapping.util.MessageUtil;
import edu.harvard.i2b2.common.exception.I2B2Exception;
import edu.harvard.i2b2.eclipse.UserInfoBean;



public class FmpServiceDriver {
	public static final String THIS_CLASS_NAME = FmpServiceDriver.class.getName();
    private static Log log = LogFactory.getLog(THIS_CLASS_NAME);
    private static String serviceURL = UserInfoBean.getInstance().getCellDataUrl("fmp");
    private static String serviceMethod = UserInfoBean.getInstance().getCellDataMethod("fmp").toUpperCase();
	private static EndpointReference soapEPR = new EndpointReference(serviceURL);

	
	private static EndpointReference TableTypeEPR = new EndpointReference(	
			serviceURL + "requestTables");
	
	
	public void Test()
	{
		try{
		ResultRequestType rqst = new ResultRequestType();
		rqst.setQueryResultInstanceId("X");
		rqst.setDescription("Testing");
		String result = getTableTypes(rqst);
		System.out.println(result);
		}catch(Exception e)
		{
			System.out.println("GNM: "+ e.toString());
		}
		
	}
	
	public static String getTableTypes(ResultRequestType self) throws Exception{
		String response = null;
			 try {
				RequestTables reqMsg = new RequestTables();

				 String getTermInfoRequestString = reqMsg.doBuildXML(self);		

	//			log.debug(getTermInfoRequestString);
				
	//			 if(serviceMethod.equals("SOAP")) {
	//				 response = sendSOAP(getTermInfoRequestString, "http://rpdr.partners.org/GetTermInfo", "GetTermInfo", type );
	//			 }
	//			 else {
					 response = sendREST(TableTypeEPR,  getTermInfoRequestString);
		//		 }

//				log.debug("Ont response = " + response);
			} catch (AxisFault e) {
				log.error(e.getMessage());
				//throw new AxisFault(e);
			} catch (Exception e) {
				log.error(e.getMessage());
				throw new Exception(e);
			}
		return response;
	}
	
	public static OMElement getFmpPayLoad(String requestVdo) throws Exception {
		OMElement lineItem  = null;
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
	public static String sendREST(EndpointReference restEPR, String requestString) throws Exception{	
		if(UserInfoBean.getInstance().getCellDataUrl("fmp") == null){
			throw new I2B2Exception("Data Repository cell (fmp) not configured in PM");
		}
		
		OMElement getCrc = getFmpPayLoad(requestString);

//		if(type != null){
//			if(type.equals("ONT"))
//				MessageUtil.getInstance().setNavRequest("URL: " + restEPR + "\n" + getOnt.toString());
//			else 
//				MessageUtil.getInstance().setFindRequest("URL: " + restEPR + "\n" + getOnt.toString());
//		}
		MessageUtil.getInstance().setNavRequest("URL: " + restEPR + "\n" + getCrc.toString());
		Options options = new Options();
		log.debug(restEPR.toString());
		options.setTo(restEPR);
		options.setTransportInProtocol(Constants.TRANSPORT_HTTP);

		options.setProperty(Constants.Configuration.ENABLE_REST, Constants.VALUE_TRUE);
		options.setProperty(HTTPConstants.SO_TIMEOUT,new Integer(185000));
		options.setProperty(HTTPConstants.CONNECTION_TIMEOUT,new Integer(185000));

		ServiceClient sender = FmpServiceClient.getServiceClient();
		sender.setOptions(options);

		OMElement result = sender.sendReceive(getCrc);
		String response = result.toString();
		MessageUtil.getInstance().setNavResponse("URL: " + restEPR + "\n" + response);
	//	if(type != null){
	//		if(type.equals("ONT"))
	//			MessageUtil.getInstance().setNavResponse("URL: " + restEPR + "\n" + response);
	//		else 
	//			MessageUtil.getInstance().setFindResponse("URL: " + restEPR + "\n" + response);
	//	}
		
		return response;

	}
	
}
