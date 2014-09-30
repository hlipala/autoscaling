package com.elhafi.cloudstack.api.root;

import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class StoragePool extends com.elhafi.cloudstack.CloudStack {

	public StoragePool(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String enableStorageMaintenance(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"enableStorageMaintenance", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String cancelStorageMaintenance(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"cancelStorageMaintenance", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

}
