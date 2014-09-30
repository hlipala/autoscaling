package com.elhafi.cloudstack.api.root;

import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class Logout extends com.elhafi.cloudstack.CloudStack {

	public Logout(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String logout() throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("logout", null);
		return Request(arguments);
	}

}
