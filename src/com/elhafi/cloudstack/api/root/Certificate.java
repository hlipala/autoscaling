package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class Certificate extends com.elhafi.cloudstack.CloudStack {

	public Certificate(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String uploadCustomCertificate(String certificate,
			String domainsuffix, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"uploadCustomCertificate", optional);
		arguments.add(new NameValuePair("certificate", certificate));
		arguments.add(new NameValuePair("domainsuffix", domainsuffix));
		return Request(arguments);
	}

}
