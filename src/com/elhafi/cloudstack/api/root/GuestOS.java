package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class GuestOS extends com.elhafi.cloudstack.CloudStack {

	  public GuestOS(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String listOsTypes(HashMap<String,String> optional) throws Exception {
	    LinkedList<NameValuePair> arguments = newQueryValues("listOsTypes",optional);
	    return Request(arguments);
	  }

	  public String listOsCategories(HashMap<String,String> optional) throws Exception {
	    LinkedList<NameValuePair> arguments = newQueryValues("listOsCategories",optional);
	    return Request(arguments);
	  }

}
