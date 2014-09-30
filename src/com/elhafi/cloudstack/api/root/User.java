package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class User extends com.elhafi.cloudstack.CloudStack {

	public User(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String createUser(String account, String email, String firstname,
			String lastname, String password, String username,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("createUser",
				optional);
		arguments.add(new NameValuePair("account", account));
		arguments.add(new NameValuePair("email", email));
		arguments.add(new NameValuePair("firstname", firstname));
		arguments.add(new NameValuePair("lastname", lastname));
		arguments.add(new NameValuePair("password", password));
		arguments.add(new NameValuePair("username", username));
		return Request(arguments);
	}

	public String deleteUser(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("deleteUser", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String updateUser(String id, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("updateUser",
				optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String addVpnUser(String password, String username,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("addVpnUser",
				optional);
		arguments.add(new NameValuePair("password", password));
		arguments.add(new NameValuePair("username", username));
		return Request(arguments);
	}

	public String removeVpnUser(String username,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("removeVpnUser",
				optional);
		arguments.add(new NameValuePair("username", username));
		return Request(arguments);
	}

	public String listVpnUsers(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listVpnUsers",
				optional);
		return Request(arguments);
	}

	public String listUsers(HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listUsers",
				optional);
		return Request(arguments);
	}

	public String disableUser(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("disableUser",
				null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String enableUser(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("enableUser", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

}
