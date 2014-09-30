package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class VLAN extends com.elhafi.cloudstack.CloudStack {

	  
	  public VLAN(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String createVlanIpRange(String startip, HashMap<String,String> optional) throws Exception {
		    LinkedList<NameValuePair> arguments = newQueryValues("createVlanIpRange",optional);
		    arguments.add(new NameValuePair("startip",startip));
		    return Request(arguments);
		  }
	  
	  public String deleteVlanIpRange(String id) throws Exception {
		    LinkedList<NameValuePair> arguments = newQueryValues("deleteVlanIpRange",null);
		    arguments.add(new NameValuePair("id",id));
		    return Request(arguments);
		  }
	  
	  public String listVlanIpRanges(HashMap<String,String> optional) throws Exception {
		    LinkedList<NameValuePair> arguments = newQueryValues("listVlanIpRanges",optional);
		    return Request(arguments);
		  }
}
