package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class VPN extends com.elhafi.cloudstack.CloudStack {

	public VPN(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	// Section: VPN
	  public String createRemoteAccessVpn(String publicipid, HashMap<String,String> optional) throws Exception {
	    LinkedList<NameValuePair> arguments = newQueryValues("createRemoteAccessVpn",optional);
	    arguments.add(new NameValuePair("publicipid",publicipid));
	    return Request(arguments);
	  }

	  public String deleteRemoteAccessVpn(String publicipid) throws Exception {
	    LinkedList<NameValuePair> arguments = newQueryValues("deleteRemoteAccessVpn",null);
	    arguments.add(new NameValuePair("publicipid",publicipid));
	    return Request(arguments);
	  }

	  public String listRemoteAccessVpns(String publicipid, HashMap<String,String> optional) throws Exception {
	    LinkedList<NameValuePair> arguments = newQueryValues("listRemoteAccessVpns",optional);
	    arguments.add(new NameValuePair("publicipid",publicipid));
	    return Request(arguments);
	  }

	
}
