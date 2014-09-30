package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class Alert extends com.elhafi.cloudstack.CloudStack {

	public Alert(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String listAlerts(HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listAlerts",
				optional);
		return Request(arguments);
	}

}
