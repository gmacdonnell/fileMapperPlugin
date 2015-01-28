package plugintest.messaging;

import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBElement;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;

import plugintest.datavo.testJAXBUtil;

import edu.harvard.i2b2.crcxmljaxb.datavo.i2b2message.ApplicationType;
import edu.harvard.i2b2.crcxmljaxb.datavo.i2b2message.BodyType;
import edu.harvard.i2b2.crcxmljaxb.datavo.i2b2message.FacilityType;
import edu.harvard.i2b2.crcxmljaxb.datavo.i2b2message.InfoType;
import edu.harvard.i2b2.crcxmljaxb.datavo.i2b2message.MessageControlIdType;
import edu.harvard.i2b2.crcxmljaxb.datavo.i2b2message.MessageHeaderType;
import edu.harvard.i2b2.crcxmljaxb.datavo.i2b2message.MessageTypeType;
import edu.harvard.i2b2.crcxmljaxb.datavo.i2b2message.PasswordType;
import edu.harvard.i2b2.crcxmljaxb.datavo.i2b2message.ProcessingIdType;
import edu.harvard.i2b2.crcxmljaxb.datavo.i2b2message.RequestHeaderType;
import edu.harvard.i2b2.crcxmljaxb.datavo.i2b2message.RequestMessageType;
import edu.harvard.i2b2.crcxmljaxb.datavo.i2b2message.SecurityType;
import edu.harvard.i2b2.crcxmljaxb.datavo.psm.query.PsmQryHeaderType;
import edu.harvard.i2b2.crcxmljaxb.datavo.psm.query.PsmRequestTypeType;
import edu.harvard.i2b2.crcxmljaxb.datavo.psm.query.QueryDefinitionRequestType;
import edu.harvard.i2b2.crcxmljaxb.datavo.psm.query.QueryDefinitionType;
import edu.harvard.i2b2.crcxmljaxb.datavo.psm.query.QueryModeType;
import edu.harvard.i2b2.crcxmljaxb.datavo.psm.query.ResultOutputOptionListType;
import edu.harvard.i2b2.crcxmljaxb.datavo.psm.query.ResultOutputOptionType;
import edu.harvard.i2b2.crcxmljaxb.datavo.psm.query.UserType;
import edu.harvard.i2b2.eclipse.UserInfoBean;
import edu.harvard.i2b2.eclipse.plugins.query.utils.Messages;
import edu.harvard.i2b2.query.serviceClient.QueryRequestClient;

public class MessageEngine {
	private String xmlStr = null;
	private String response = null;
	public static final String TARGETURL="http://144.174.39.18/i2b2/services/FileMapperService/getDataTypes";
	public  void test01()
	{
		
		String XMLstr = getXmlString();
		Write("XMLstring:"+XMLstr);
		try{
		OMElement payload = getPayLoad(XMLstr);
		Options options = new Options();
		//EndpointReference targetEPR = new EndpointReference( UserInfoBean.getInstance().getCellDataUrl("CRC") + "request");
		EndpointReference targetEPR = new EndpointReference(TARGETURL);
		//options.setTo(targetEPR);
		options.setTo(targetEPR);
		options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
		options.setProperty(Constants.Configuration.ENABLE_REST,
				Constants.VALUE_TRUE);
		options.setProperty(HTTPConstants.SO_TIMEOUT, new Integer(200000));
		options.setProperty(HTTPConstants.CONNECTION_TIMEOUT, new Integer(
				200000));

		ServiceClient sender = new ServiceClient();
		sender.setOptions(options);

		OMElement result = sender.sendReceive(payload);
		Write(result.toString());
		}catch(Exception e){
			System.out.println("Oops "+ e.toString());
		}
		
		
	}
	public void GoodTest(OMElement payload)
	{
		try {
			Write("Testing");
			org.apache.axis2.client.ServiceClient sender = new ServiceClient();

			// RPCServiceClient serviceClient = new RPCServiceClient();

			EndpointReference targetEPR = new EndpointReference(TARGETURL);
			 Options options = getOptions();
			 options.setTo(targetEPR);
			 sender.setOptions(options);
			 
			 OMElement result = sender.sendReceive(payload);
			 Write(result.toString());
		} catch (Exception e) {
			Write("Oops: " + e.toString());
		}

	}
	public void test02(OMElement payload)
	{
		String XMLstr = getXmlString();
		try{
		
		Options options = getOptions();
		//EndpointReference targetEPR = new EndpointReference( UserInfoBean.getInstance().getCellDataUrl("CRC") + "request");
		EndpointReference targetEPR = new EndpointReference(TARGETURL);
		options.setTo(targetEPR);
		

		ServiceClient sender = new ServiceClient();
		sender.setOptions(options);

		OMElement result = sender.sendReceive(payload);
		Write(result.toString());
		}catch(Exception e){
			System.out.println("Oops "+ e.toString());
		}
		
	}
	public void sendQuery() {

		xmlStr = getXmlString();
		response = null;
		Thread queryThread = new Thread() {
			@Override
			public void run() {
				// setCursor(new Cursor(Cursor.WAIT_CURSOR));
				response = QueryRequestClient.sendQueryRequestREST(xmlStr);
				// parentPanel.lastResponseMessage(response);
				if (response != null) {

				}// if
			}// run
		};// thread
		try {
			queryThread.start();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public Options getOptions()
	{
		Options options = new Options();
		options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
		options.setProperty(Constants.Configuration.ENABLE_REST,
				Constants.VALUE_TRUE);
		options.setProperty(HTTPConstants.SO_TIMEOUT, new Integer(200000));
		options.setProperty(HTTPConstants.CONNECTION_TIMEOUT, new Integer(
				200000));
		return options;
	}
	public String getXmlString() {
		// create infotype
		String queryName = "Test_Query";
		// *
		QueryDefinitionType queryDefinitionType = new QueryDefinitionType();
		ResultOutputOptionListType resultOutputOptionListType = new ResultOutputOptionListType();
		// was a loop
		ResultOutputOptionType resultOutputOptionType = new ResultOutputOptionType();
		resultOutputOptionType.setName("patient_count_xml");
		// );
		resultOutputOptionType.setPriorityIndex(new Integer(1));
		resultOutputOptionListType.getResultOutput()
				.add(resultOutputOptionType);
		// end loop
		QueryDefinitionRequestType queryDefinitionRequestType = new QueryDefinitionRequestType();
		// *
		InfoType infoType = new InfoType();
		infoType.setValue("INFO");
		infoType.setUrl("http://www.ibm.com");

		// create header
		PsmQryHeaderType headerType = new PsmQryHeaderType();

		UserType userType = new UserType();
		userType.setLogin(UserInfoBean.getInstance().getUserName());
	//	userType.setLogin("@");
		userType.setGroup(System.getProperty("projectName"));
		//userType.setValue(UserInfoBean.getInstance().getUserName());
		userType.setValue("@");
		headerType.setUser(userType);
		headerType.setRequestType(PsmRequestTypeType.CRC_QRY_RUN_QUERY_INSTANCE_FROM_QUERY_DEFINITION);
	//	headerType.setRequestType(PsmRequestTypeType.CRC_QRY_GET_MAP_PLUGIN_REQUIRED_FIELDS);

		headerType.setQueryMode(QueryModeType.OPTIMIZE_WITHOUT_TEMP_TABLE);

		queryDefinitionType.setQueryName(queryName);
		queryDefinitionType.setQueryTiming("ANY");
		queryDefinitionRequestType.setQueryDefinition(queryDefinitionType);
		queryDefinitionRequestType
				.setResultOutputList(resultOutputOptionListType);

		RequestHeaderType requestHeader = new RequestHeaderType();

		if (System.getProperty("QueryToolMaxWaitingTime") != null) {
			requestHeader.setResultWaittimeMs((Integer.parseInt(System
					.getProperty("QueryToolMaxWaitingTime"))) * 1000);
		} else {
			requestHeader.setResultWaittimeMs(180000);
		}

		BodyType bodyType = new BodyType();
		edu.harvard.i2b2.crcxmljaxb.datavo.psm.query.ObjectFactory psmOf = new edu.harvard.i2b2.crcxmljaxb.datavo.psm.query.ObjectFactory();
		bodyType.getAny().add(psmOf.createPsmheader(headerType));
		bodyType.getAny().add(psmOf.createRequest(queryDefinitionRequestType));

		MessageHeaderType messageHeader = getMessageHeader();
		RequestMessageType requestMessageType = new RequestMessageType();
		requestMessageType.setMessageBody(bodyType);
		requestMessageType.setMessageHeader(messageHeader);
		requestMessageType.setRequestHeader(requestHeader);

		edu.harvard.i2b2.common.util.jaxb.JAXBUtil jaxbUtil = testJAXBUtil.getJAXBUtil();
		//edu.harvard.i2b2.common.util.jaxb.JAXBUtil jaxbUtil = new edu.harvard.i2b2.common.util.jaxb.JAXBUtil(				JAXBConstant.DEFAULT_PACKAGE_NAME);
		StringWriter strWriter = new StringWriter();
		try {
			edu.harvard.i2b2.crcxmljaxb.datavo.i2b2message.ObjectFactory of = new edu.harvard.i2b2.crcxmljaxb.datavo.i2b2message.ObjectFactory();
			JAXBElement<RequestMessageType> RMT = of
					.createRequest(requestMessageType);
			//jaxbUtil.marshallerWithCDATA(RMT, strWriter,	new String[] { "value_constraint" });
			jaxbUtil.marshaller(RMT, strWriter);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return strWriter.toString();

	}
	public static OMElement getPayLoad(String XMLstr) throws Exception {
		StringReader strReader = new StringReader(XMLstr);
		XMLInputFactory xif = XMLInputFactory.newInstance();
		XMLStreamReader reader = xif.createXMLStreamReader(strReader);

		StAXOMBuilder builder = new StAXOMBuilder(reader);
		OMElement lineItem = builder.getDocumentElement();
		// System.out.println("Line item string " + lineItem.toString());
		return lineItem;
	}

	protected MessageHeaderType getMessageHeader() {
		MessageHeaderType messageHeader = new MessageHeaderType();

		messageHeader.setI2B2VersionCompatible(new BigDecimal(Messages
				.getString("QueryData.i2b2VersionCompatible"))); //$NON-NLS-1$

		ApplicationType appType = new ApplicationType();
		appType.setApplicationName(Messages
				.getString("QueryData.SendingApplicationName")); //$NON-NLS-1$
		appType.setApplicationVersion(Messages
				.getString("QueryData.SendingApplicationVersion")); //$NON-NLS-1$
		messageHeader.setSendingApplication(appType);

		messageHeader.setAcceptAcknowledgementType(new String("messageId"));

		MessageTypeType messageTypeType = new MessageTypeType();
		messageTypeType.setEventType(Messages.getString("QueryData.EventType"));
		messageTypeType.setMessageCode(Messages
				.getString("QueryData.MessageCode"));
		messageHeader.setMessageType(messageTypeType);

		FacilityType facility = new FacilityType();
		facility.setFacilityName(Messages
				.getString("QueryData.SendingFacilityName")); //$NON-NLS-1$
		messageHeader.setSendingFacility(facility);

		ApplicationType appType2 = new ApplicationType();
		appType2.setApplicationVersion(Messages
				.getString("QueryData.ReceivingApplicationVersion")); //$NON-NLS-1$
		appType2.setApplicationName(Messages
				.getString("QueryData.ReceivingApplicationName")); //$NON-NLS-1$
		messageHeader.setReceivingApplication(appType2);

		FacilityType facility2 = new FacilityType();
		facility2.setFacilityName(Messages
				.getString("QueryData.ReceivingFacilityName")); //$NON-NLS-1$
		messageHeader.setReceivingFacility(facility2);

		// Date currentDate = new Date();
		// DTOFactory factory = new DTOFactory();
		// messageHeader.setDatetimeOfMessage(factory
		// .getXMLGregorianCalendar(currentDate.getTime()));

		SecurityType secType = new SecurityType();
		secType.setDomain(UserInfoBean.getInstance().getUserDomain());
		secType.setUsername(UserInfoBean.getInstance().getUserName());

		PasswordType ptype = new PasswordType();
		ptype.setIsToken(UserInfoBean.getInstance().getUserPasswordIsToken());
		ptype.setTokenMsTimeout(UserInfoBean.getInstance()
			.getUserPasswordTimeout());
		ptype.setValue(UserInfoBean.getInstance().getUserPassword());

		secType.setPassword(ptype);
		messageHeader.setSecurity(secType);

		MessageControlIdType mcIdType = new MessageControlIdType();
		mcIdType.setInstanceNum(0);
		mcIdType.setMessageNum(generateMessageId());
		messageHeader.setMessageControlId(mcIdType);

		ProcessingIdType proc = new ProcessingIdType();
		proc.setProcessingId(Messages.getString("QueryData.ProcessingId")); //$NON-NLS-1$
		proc.setProcessingMode(Messages.getString("QueryData.ProcessingMode")); //$NON-NLS-1$
		messageHeader.setProcessingId(proc);

		messageHeader.setAcceptAcknowledgementType(Messages
				.getString("QueryData.AcceptAcknowledgementType")); //$NON-NLS-1$
		messageHeader.setApplicationAcknowledgementType(Messages
				.getString("QueryData.ApplicationAcknowledgementType")); //$NON-NLS-1$
		messageHeader.setCountryCode(Messages
				.getString("QueryData.CountryCode")); //$NON-NLS-1$
		//messageHeader.setProjectId(UserInfoBean.getInstance().getProjectId());
		messageHeader.setProjectId("Demo");
		return messageHeader;
	}

	private String generateMessageId() {
		StringWriter strWriter = new StringWriter();
		for (int i = 0; i < 20; i++) {
			int num = getValidAcsiiValue();
			// System.out.println("Generated number: " + num + " char:
			// "+(char)num);
			strWriter.append((char) num);
		}
		return strWriter.toString();
	}

	private int getValidAcsiiValue() {
		int number = 48;
		while (true) {
			number = 48 + (int) Math.round(Math.random() * 74);
			if ((number > 47 && number < 58) || (number > 64 && number < 91)
					|| (number > 96 && number < 123)) {
				break;
			}
		}
		return number;
	}
	public void Write(String txt)
	{
		txt = "GNM: "+ txt;
		System.out.println(txt);
	}
	
	public void test04()
	{
		ResourceBundle RESOURCE_BUNDLE = ResourceBundle
				.getBundle("Test");
		Write(RESOURCE_BUNDLE.getString("TestValue.answer"));
	}

}
