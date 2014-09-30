package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class Swift extends com.elhafi.cloudstack.CloudStack {

	 public Swift(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String addSwift(String url, HashMap<String,String> optional) throws Exception {
		    LinkedList<NameValuePair> arguments = newQueryValues("addSwift",optional);
		    arguments.add(new NameValuePair("url",url));
		    return Request(arguments);
		  }
	 
	 public String listSwifts(HashMap<String,String> optional) throws Exception {
		    LinkedList<NameValuePair> arguments = newQueryValues("listSwifts",optional);
		    return Request(arguments);
		  }
	

}
