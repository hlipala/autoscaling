package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class ExtFirewall extends com.elhafi.cloudstack.CloudStack {

	public ExtFirewall(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String addExternalFirewall(String password, String url,
			String username, String zoneid) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"addExternalFirewall", null);
		arguments.add(new NameValuePair("displaytext", password));
		arguments.add(new NameValuePair("name", url));
		arguments.add(new NameValuePair("url", username));
		arguments.add(new NameValuePair("zoneid", zoneid));
		return Request(arguments);
	}

	public String deleteExternalFirewall(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"deleteExternalFirewall", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String listExternalFirewalls(String zoneid,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"listExternalLoadBalancers", optional);

		arguments.add(new NameValuePair("zoneid", zoneid));
		return Request(arguments);
	}

}
