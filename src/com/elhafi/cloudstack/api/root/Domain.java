package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class Domain extends com.elhafi.cloudstack.CloudStack {

	public Domain(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String listDomains(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listDomains",
				optional);
		return Request(arguments);
	}

	public String listDomainChildren(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"listDomainChildren", optional);
		return Request(arguments);
	}

	public String createDomain(String name, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("createDomain",
				optional);

		arguments.add(new NameValuePair("name", name));
		return Request(arguments);
	}

	public String updateDomain(String id, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("updateDomain",
				optional);

		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String deleteDomain(String id, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("deleteDomain",
				optional);

		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}
}
