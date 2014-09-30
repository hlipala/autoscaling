package com.elhafi.cloudstack.api.root;

import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class CloudIdentifier extends com.elhafi.cloudstack.CloudStack {

	public CloudIdentifier(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String getCloudIdentifier(String userid) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"getCloudIdentifier", null);
		arguments.add(new NameValuePair("userid", userid));
		return Request(arguments);
	}

}
