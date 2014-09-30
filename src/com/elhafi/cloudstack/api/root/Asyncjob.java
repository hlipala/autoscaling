package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class Asyncjob extends com.elhafi.cloudstack.CloudStack {

	public Asyncjob(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String queryAsyncJobResult(String jobid) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"queryAsyncJobResult", null);
		arguments.add(new NameValuePair("jobid", jobid));
		return Request(arguments);
	}

	public String listAsyncJobs(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listAsyncJobs",
				optional);
		return Request(arguments);
	}

}
