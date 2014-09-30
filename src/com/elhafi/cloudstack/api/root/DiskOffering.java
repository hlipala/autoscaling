package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class DiskOffering extends com.elhafi.cloudstack.CloudStack {

	public DiskOffering(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String listDiskOfferings(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"listDiskOfferings", optional);
		return Request(arguments);
	}

	public String createDiskOffering(String displaytext, String name,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"createDiskOffering", optional);
		arguments.add(new NameValuePair("displaytext", displaytext));
		arguments.add(new NameValuePair("name", name));
		return Request(arguments);
	}

	public String updateDiskOffering(String id, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"updateDiskOffering", optional);

		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String deleteDiskOffering(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"deleteDiskOffering", null);

		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

}
