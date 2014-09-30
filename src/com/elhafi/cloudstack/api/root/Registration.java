package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class Registration extends com.elhafi.cloudstack.CloudStack {

	public Registration(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String registerSSHKeyPair(String name, String publickey,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"registerSSHKeyPair", optional);
		arguments.add(new NameValuePair("name", name));
		arguments.add(new NameValuePair("publickey", publickey));
		return Request(arguments);
	}

}
