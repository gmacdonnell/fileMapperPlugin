package edu.fsu.idiginfo.i2b2.fileMapper.serviceClient;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Calendar;

import javax.swing.JOptionPane;

import javax.xml.bind.JAXBElement;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import edu.harvard.i2b2.common.util.jaxb.JAXBUtilException;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.i2b2message.MessageHeaderType;
import edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.i2b2message.ResponseMessageType;
import edu.harvard.i2b2.eclipse.UserInfoBean;
//import edu.harvard.i2b2.eclipse.plugins.query.utils.MessageUtil;
import edu.fsu.idiginfo.i2b2.fileMapper.fileMapperUtil.FileMapperJAXBUtil;
import edu.fsu.idiginfo.i2b2.fileMapper.fileMapperUtil.messaging.MessageUtil;

public class FileMapperRequestClient {

	private static final Log log = LogFactory
			.getLog(FileMapperRequestClient.class);
	private static EndpointReference targetEPR;

	public static OMElement getQueryPayLoad(String XMLstr) throws Exception {
		StringReader strReader = new StringReader(XMLstr);
		XMLInputFactory xif = XMLInputFactory.newInstance();
		XMLStreamReader reader = xif.createXMLStreamReader(strReader);

		StAXOMBuilder builder = new StAXOMBuilder(reader);
		OMElement lineItem = builder.getDocumentElement();
		// System.out.println("Line item string " + lineItem.toString());
		return lineItem;
	}

	private static String getMAPServiceName() {

		return UserInfoBean.getInstance().getCellDataUrl("MAP") ;
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
			 * log.error("Error reported by Ont web Service " + procMessage); }
			 * else if (procStatus.equals("WARNING")) {
			 * log.error("Warning reported by Ont web Service" + procMessage); }
			 */

		} catch (JAXBUtilException e) {
			log.error(e.getMessage());
		}
		return timeout;
	}

	public static String sendQueryRequestREST(String XMLstr) {
		try {
			SAXBuilder parser = new SAXBuilder();
			String xmlContent = XMLstr;
			java.io.StringReader xmlStringReader = new java.io.StringReader(
					xmlContent);
			org.jdom.Document tableDoc = parser.build(xmlStringReader);
			XMLOutputter o = new XMLOutputter();
			o.setFormat(Format.getPrettyFormat());
			StringWriter str = new StringWriter();
			o.output(tableDoc, str);

			MessageUtil.getInstance().setRequest(
					"URL: " + getMAPServiceName() + "\n"
							+ str);

			OMElement payload = getQueryPayLoad(XMLstr);
			Options options = new Options();
			targetEPR = new EndpointReference(
					getMAPServiceName());
			options.setTo(targetEPR);
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
			// System.out.println(result.toString());

			xmlStringReader = new java.io.StringReader(result.toString());
			tableDoc = parser.build(xmlStringReader);
			o = new XMLOutputter();
			o.setFormat(Format.getPrettyFormat());
			str = new StringWriter();
			o.output(tableDoc, str);
			MessageUtil.getInstance().setResponse(
					"URL: " + getMAPServiceName() + "\n"
							+ str);// result.toString());
			int timeout = processSecurityResult(result.toString());
			log.info("get timeout from server: " + timeout + " at: "
					+ Calendar.getInstance().getTime());
			if (timeout != -1) {
				UserInfoBean.setLastActivityTime(Calendar.getInstance()
						.getTime());
				UserInfoBean.getInstance().setUserPasswordTimeout(timeout);
				// log.info("get timeout from server: "+ timeout +
				// " at: "+Calendar.getInstance().getTime());
			}

			return result.toString();
		} catch (AxisFault axisFault) {
			axisFault.printStackTrace();
			java.awt.EventQueue.invokeLater(new Runnable() {
				public void run() {
					JOptionPane
							.showMessageDialog(
									null,
									"Trouble with connection to the remote server, "
											+ "this is often a network error, please try again",
									"Network Error",
									JOptionPane.INFORMATION_MESSAGE);
				}
			});

			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
