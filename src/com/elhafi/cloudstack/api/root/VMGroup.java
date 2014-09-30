package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class VMGroup extends com.elhafi.cloudstack.CloudStack {

	public VMGroup(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	// Section: VM group
	public String createInstanceGroup(String name,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"createInstanceGroup", optional);
		arguments.add(new NameValuePair("name", name));
		return Request(arguments);
	}

	public String deleteInstanceGroup(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"deleteInstanceGroup", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String updateInstanceGroup(String id,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"updateInstanceGroup", optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String listInstanceGroups(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"listInstanceGroups", optional);
		return Request(arguments);
	}

}
