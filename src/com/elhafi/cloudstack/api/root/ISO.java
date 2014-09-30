package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class ISO extends com.elhafi.cloudstack.CloudStack {

	public ISO(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String attachIso(String id, String virtualmachineid)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("attachIso", null);
		arguments.add(new NameValuePair("id", id));
		arguments.add(new NameValuePair("virtualmachineid", virtualmachineid));
		return Request(arguments);
	}

	public String detachIso(String virtualmachineid) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("detachIso", null);
		arguments.add(new NameValuePair("virtualmachineid", virtualmachineid));
		return Request(arguments);
	}

	public String listIsos(HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listIsos",
				optional);
		return Request(arguments);
	}

	public String registerIso(String displaytext, String name, String url,
			String zoneid, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("registerIso",
				optional);
		arguments.add(new NameValuePair("displaytext", displaytext));
		arguments.add(new NameValuePair("name", name));
		arguments.add(new NameValuePair("url", url));
		arguments.add(new NameValuePair("zoneid", zoneid));
		return Request(arguments);
	}

	public String updateIso(String id, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("updateIso",
				optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String deleteIso(String id, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("deleteIso",
				optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String copyIso(String id, String destzoneid, String sourcezoneid)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("copyIso", null);
		arguments.add(new NameValuePair("id", id));
		arguments.add(new NameValuePair("destzoneid", destzoneid));
		arguments.add(new NameValuePair("sourcezoneid", sourcezoneid));
		return Request(arguments);
	}

	public String updateIsoPermissions(String id,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"updateIsoPermissions", optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String listIsoPermissions(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"listIsoPermissions", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String extractIso(String id, String mode,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("extractIso",
				optional);
		arguments.add(new NameValuePair("id", id));
		arguments.add(new NameValuePair("mode", mode));
		return Request(arguments);
	}

}
