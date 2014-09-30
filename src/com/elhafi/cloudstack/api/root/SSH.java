package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class SSH extends com.elhafi.cloudstack.CloudStack {

	public SSH(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String createSSHKeyPair(String name, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"createSSHKeyPair", optional);
		arguments.add(new NameValuePair("name", name));
		return Request(arguments);
	}

	public String deleteSSHKeyPair(String name, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"deleteSSHKeyPair", optional);
		arguments.add(new NameValuePair("name", name));
		return Request(arguments);
	}

	public String listSSHKeyPairs(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listSSHKeyPairs",
				optional);
		return Request(arguments);
	}

}
