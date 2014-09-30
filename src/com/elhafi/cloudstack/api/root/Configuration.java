package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class Configuration extends com.elhafi.cloudstack.CloudStack {

	public Configuration(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String listCapabilities() throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"listCapabilities", null);
		return Request(arguments);
	}

	public String updateConfiguration(String name,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"updateConfiguration", optional);
		arguments.add(new NameValuePair("name", name));
		return Request(arguments);
	}

	public String listConfigurations(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"listConfigurations", optional);
		return Request(arguments);
	}

}
