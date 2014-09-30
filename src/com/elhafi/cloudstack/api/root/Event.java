package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class Event extends com.elhafi.cloudstack.CloudStack {

	public Event(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String listEvents(HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listEvents",
				optional);
		return Request(arguments);
	}

	public String listEventTypes() throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listEventTypes",
				null);
		return Request(arguments);
	}

}
