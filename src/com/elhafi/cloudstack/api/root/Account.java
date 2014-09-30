package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class Account extends com.elhafi.cloudstack.CloudStack {

	public Account(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String createAccount(String accounttype, String email,
			String firstname, String lastname, String password,
			String username, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("createAccount",
				optional);
		arguments.add(new NameValuePair("accounttype", accounttype));
		arguments.add(new NameValuePair("email", email));
		arguments.add(new NameValuePair("firstname", firstname));
		arguments.add(new NameValuePair("lastname", lastname));
		arguments.add(new NameValuePair("password", password));
		arguments.add(new NameValuePair("username", username));
		return Request(arguments);
	}

	public String deleteAccount(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("deleteAccount",
				null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String updateAccount(String newname, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("updateAccount",
				optional);
		arguments.add(new NameValuePair("newname", newname));
		return Request(arguments);
	}

	public String disableAccount(String lock, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("disableAccount",
				optional);
		arguments.add(new NameValuePair("lock", lock));
		return Request(arguments);
	}

	public String enableAccount(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("enableAccount",
				optional);
		return Request(arguments);
	}

	public String listAccounts(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listAccounts",
				optional);
		return Request(arguments);
	}

	public String addAccountToProject(String projectid,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"addAccountToProject", optional);
		arguments.add(new NameValuePair("projectid", projectid));
		return Request(arguments);
	}

	public String deleteAccountFromProject(String account, String projectid)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"deleteAccountFromProject", null);
		arguments.add(new NameValuePair("account", account));
		arguments.add(new NameValuePair("projectid", projectid));
		return Request(arguments);
	}

	public String listProjectAccounts(String projectid,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"listProjectAccounts", optional);
		arguments.add(new NameValuePair("projectid", projectid));
		return Request(arguments);
	}

}
