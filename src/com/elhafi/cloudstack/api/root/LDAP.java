package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class LDAP extends com.elhafi.cloudstack.CloudStack {

	public LDAP(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String ldapConfig(String hostname, String queryfilter,
			String searchbase, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("ldapConfig",
				optional);
		arguments.add(new NameValuePair("hostname", hostname));
		arguments.add(new NameValuePair("queryfilter", queryfilter));
		arguments.add(new NameValuePair("searchbase", searchbase));
		return Request(arguments);
	}

}
