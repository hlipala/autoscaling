package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class ServiceOffering extends com.elhafi.cloudstack.CloudStack {

	public ServiceOffering(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String listServiceOfferings(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"listServiceOfferings", optional);
		return Request(arguments);
	}

	public String createServiceOffering(String cpunumber, String cpuspeed,
			String displaytext, String memory, String name,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"createServiceOffering", optional);
		arguments.add(new NameValuePair("cpunumber", cpunumber));
		arguments.add(new NameValuePair("cpuspeed", cpuspeed));
		arguments.add(new NameValuePair("displaytext", displaytext));
		arguments.add(new NameValuePair("memory", memory));
		arguments.add(new NameValuePair("name", name));
		return Request(arguments);
	}

	public String deleteServiceOffering(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"deleteServiceOffering", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String updateServiceOffering(String id,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"updateServiceOffering", optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}
}
