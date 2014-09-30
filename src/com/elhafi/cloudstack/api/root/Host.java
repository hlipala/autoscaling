package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class Host extends com.elhafi.cloudstack.CloudStack {

	public Host(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String addHost(String hypervisor, String password, String url,
			String username, String zoneid, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("addHost",
				optional);
		arguments.add(new NameValuePair("hypervisor", hypervisor));
		arguments.add(new NameValuePair("password", password));
		arguments.add(new NameValuePair("url", url));
		arguments.add(new NameValuePair("username", username));
		arguments.add(new NameValuePair("zoneid", zoneid));
		return Request(arguments);
	}

	public String reconnectHost(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("reconnectHost",
				null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String updateHost(String id, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("updateHost",
				optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String deleteHost(String id, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("deleteHost",
				optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String prepareHostForMaintenance(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"prepareHostForMaintenance", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String cancelHostMaintenance(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"cancelHostMaintenance", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String listHosts(HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listHosts",
				optional);
		return Request(arguments);
	}

	public String addSecondaryStorage(String url,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"addSecondaryStorage", optional);
		arguments.add(new NameValuePair("url", url));
		return Request(arguments);
	}

	public String updateHostPassword(String password, String username,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"updateHostPassword", optional);
		arguments.add(new NameValuePair("password", password));
		arguments.add(new NameValuePair("username", username));
		return Request(arguments);
	}

}
