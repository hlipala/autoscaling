package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class Hypervisor extends com.elhafi.cloudstack.CloudStack {

	public Hypervisor(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String listHypervisors(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listHypervisors",
				optional);
		return Request(arguments);
	}

	public String updateHypervisorCapabilities(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"updateHypervisorCapabilities", optional);
		return Request(arguments);
	}

	public String listHypervisorCapabilities(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"listHypervisorCapabilities", optional);
		return Request(arguments);
	}

}
