package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class Pool extends com.elhafi.cloudstack.CloudStack {

	public Pool(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String createPool(String algorithm, String name) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("createPool", null);
		arguments.add(new NameValuePair("algorithm", algorithm));
		arguments.add(new NameValuePair("name", name));
		return Request(arguments);
	}

	public String deletePool(String poolname) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("deletePool", null);
		arguments.add(new NameValuePair("poolname", poolname));
		return Request(arguments);
	}

	public String modifyPool(String algorithm, String poolname)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("modifyPool", null);
		arguments.add(new NameValuePair("algorithm", algorithm));
		arguments.add(new NameValuePair("poolname", poolname));
		return Request(arguments);
	}

	public String listPools() throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listPools", null);
		return Request(arguments);
	}

	public String createStoragePool(String name, String url, String zoneid,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"createStoragePool", optional);
		arguments.add(new NameValuePair("name", name));
		arguments.add(new NameValuePair("url", url));
		arguments.add(new NameValuePair("zoneid", zoneid));
		return Request(arguments);
	}

	public String updateStoragePool(String id, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"updateStoragePool", optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String deleteStoragePool(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"deleteStoragePool", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String listStoragePools(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"listStoragePools", optional);
		return Request(arguments);
	}

}
