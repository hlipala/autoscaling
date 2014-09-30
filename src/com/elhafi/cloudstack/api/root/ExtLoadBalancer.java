package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class ExtLoadBalancer extends com.elhafi.cloudstack.CloudStack {

	public ExtLoadBalancer(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String addExternalLoadBalancer(String password, String url,
			String username, String zoneid) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"addExternalLoadBalancer", null);
		arguments.add(new NameValuePair("displaytext", password));
		arguments.add(new NameValuePair("name", url));
		arguments.add(new NameValuePair("url", username));
		arguments.add(new NameValuePair("zoneid", zoneid));
		return Request(arguments);
	}

	public String deleteExternalLoadBalancer(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"deleteExternalLoadBalancer", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String listExternalLoadBalancers(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"listExternalLoadBalancers", optional);
		return Request(arguments);
	}

}
