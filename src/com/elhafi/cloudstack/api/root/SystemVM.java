package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class SystemVM extends com.elhafi.cloudstack.CloudStack {

	public SystemVM(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String startSystemVm(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("startSystemVm",
				null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String rebootSystemVm(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("rebootSystemVm",
				null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String stopSystemVm(String id, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("stopSystemVm",
				optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String destroySystemVm(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("destroySystemVm",
				null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String listSystemVms(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listSystemVms",
				optional);
		return Request(arguments);
	}

	public String migrateSystemVm(String hostid, String virtualmachineid)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("migrateSystemVm",
				null);
		arguments.add(new NameValuePair("hostid", hostid));
		arguments.add(new NameValuePair("virtualmachineid", virtualmachineid));
		return Request(arguments);
	}

}
