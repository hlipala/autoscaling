package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class SecurityGroup extends com.elhafi.cloudstack.CloudStack{

	public SecurityGroup(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String createSecurityGroup(String name, HashMap<String,String> optional) throws Exception {
	    LinkedList<NameValuePair> arguments = newQueryValues("createSecurityGroup",optional);
	    arguments.add(new NameValuePair("name",name));
	    return Request(arguments);
	  }
	
	public String deleteSecurityGroup(HashMap<String,String> optional) throws Exception {
	    LinkedList<NameValuePair> arguments = newQueryValues("deleteSecurityGroup",optional);
	    return Request(arguments);
	  }
	
	public String authorizeSecurityGroupIngress(HashMap<String,String> optional) throws Exception {
	    LinkedList<NameValuePair> arguments = newQueryValues("authorizeSecurityGroupIngress",optional);
	    return Request(arguments);
	  }
	
	public String revokeSecurityGroupIngress(String id) throws Exception {
	    LinkedList<NameValuePair> arguments = newQueryValues("revokeSecurityGroupIngress",null);
	    arguments.add(new NameValuePair("id",id));
	    return Request(arguments);
	  }
	
	public String authorizeSecurityGroupEgress(HashMap<String,String> optional) throws Exception {
	    LinkedList<NameValuePair> arguments = newQueryValues("authorizeSecurityGroupEgress",optional);
	    return Request(arguments);
	  }
	
	public String revokeSecurityGroupEgress(String id) throws Exception {
	    LinkedList<NameValuePair> arguments = newQueryValues("revokeSecurityGroupEgress",null);
	    arguments.add(new NameValuePair("id",id));
	    return Request(arguments);
	  }
	
	public String listSecurityGroups( HashMap<String,String> optional) throws Exception {
	    LinkedList<NameValuePair> arguments = newQueryValues("listSecurityGroups",optional);
	    return Request(arguments);
	  }
	

}
