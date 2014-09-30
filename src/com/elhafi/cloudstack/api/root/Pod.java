package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class Pod extends com.elhafi.cloudstack.CloudStack {

	public Pod(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String createPod(String gateway, String name, String netmask,
			String startip, String zoneid, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("createPod",
				optional);
		arguments.add(new NameValuePair("gateway", gateway));
		arguments.add(new NameValuePair("name", name));
		arguments.add(new NameValuePair("netmask", netmask));
		arguments.add(new NameValuePair("startip", startip));
		arguments.add(new NameValuePair("zoneid", zoneid));
		return Request(arguments);
	}

	public String updatePod(String id, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("updatePod",
				optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String deletePod(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("deletePod", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String listPods(HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listPods",
				optional);
		return Request(arguments);
	}

}
