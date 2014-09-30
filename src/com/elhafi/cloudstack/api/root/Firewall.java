package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class Firewall extends com.elhafi.cloudstack.CloudStack {

	public Firewall(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String listPortForwardingRules(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"listPortForwardingRules", optional);
		return Request(arguments);
	}

	public String createPortForwardingRule(String ipaddressid,
			String privateport, String protocol, String publicport,
			String virtualmachineid, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"createPortForwardingRule", optional);
		arguments.add(new NameValuePair("ipaddressid", ipaddressid));
		arguments.add(new NameValuePair("privateport", privateport));
		arguments.add(new NameValuePair("protocol", protocol));
		arguments.add(new NameValuePair("publicport", publicport));
		arguments.add(new NameValuePair("virtualmachineid", virtualmachineid));
		return Request(arguments);
	}

	public String deletePortForwardingRule(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"deletePortForwardingRule", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String createFirewallRule(String protocol,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"createFirewallRule", optional);
		arguments.add(new NameValuePair("protocol", protocol));
		return Request(arguments);
	}

	public String deleteFirewallRule(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"deleteFirewallRule", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String listFirewallRules(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"listFirewallRules", optional);
		return Request(arguments);
	}

	public String addSrxFirewall(String networkdevicetype, String password,
			String physicalnetworkid, String url, String username)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("addSrxFirewall",
				null);
		arguments
				.add(new NameValuePair("networkdevicetype", networkdevicetype));
		arguments.add(new NameValuePair("password", password));
		arguments
				.add(new NameValuePair("physicalnetworkid", physicalnetworkid));
		arguments.add(new NameValuePair("url", url));
		arguments.add(new NameValuePair("username", username));
		return Request(arguments);
	}

	public String deleteSrxFirewall(String fwdeviceid) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"deleteSrxFirewall", null);
		arguments.add(new NameValuePair("fwdeviceid", fwdeviceid));
		return Request(arguments);
	}

	public String configureSrxFirewall(String fwdeviceid,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"configureSrxFirewall", optional);
		arguments.add(new NameValuePair("fwdeviceid", fwdeviceid));
		return Request(arguments);
	}

	public String listSrxFirewalls(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"listSrxFirewalls", optional);
		return Request(arguments);
	}

}
