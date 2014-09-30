package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class Login extends com.elhafi.cloudstack.CloudStack{

	  public Login(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String login(String username, String password, HashMap<String,String> optional) throws Exception {
		    LinkedList<NameValuePair> arguments = newQueryValues("login",optional);
		    arguments.add(new NameValuePair("username",username));
		    arguments.add(new NameValuePair("password",password));
		    return Request(arguments);
		  }

}
