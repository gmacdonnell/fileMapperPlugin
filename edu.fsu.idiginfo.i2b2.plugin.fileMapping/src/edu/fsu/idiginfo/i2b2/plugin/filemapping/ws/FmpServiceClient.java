package edu.fsu.idiginfo.i2b2.plugin.filemapping.ws;

import org.apache.axis2.AxisFault;
import org.apache.axis2.client.ServiceClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class FmpServiceClient {

	public static final String THIS_CLASS_NAME = FmpServiceClient.class.getName();
    private static Log log = LogFactory.getLog(THIS_CLASS_NAME);	
	
    private static ServiceClient sender = null;
	private FmpServiceClient() { 
	}
	
	public static  ServiceClient getServiceClient() throws AxisFault{
		if (sender == null) {
			try {
				sender = new ServiceClient();
			} catch (AxisFault e) {
				log.error(e.getMessage());
				throw e;
			}

		}
		return sender;
	}


}
