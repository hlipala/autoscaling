package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class NAT extends com.elhafi.cloudstack.CloudStack {

	public NAT(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String enableStaticNat(String ipaddressid, String virtualmachineid)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("enableStaticNat",
				null);
		arguments.add(new NameValuePair("ipaddressid", ipaddressid));
		arguments.add(new NameValuePair("virtualmachineid", virtualmachineid));
		return Request(arguments);
	}

	public String createIpForwardingRule(String ipaddressid, String protocol,
			String startport, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"createIpForwardingRule", optional);
		arguments.add(new NameValuePair("ipaddressid", ipaddressid));
		arguments.add(new NameValuePair("protocol", protocol));
		arguments.add(new NameValuePair("startport", startport));
		return Request(arguments);
	}

	public String deleteIpForwardingRule(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"deleteIpForwardingRule", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String listIpForwardingRules(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"listIpForwardingRules", optional);
		return Request(arguments);
	}

	public String disableStaticNat(String ipaddressid) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"disableStaticNat", null);
		arguments.add(new NameValuePair("ipaddressid", ipaddressid));
		return Request(arguments);
	}

}
