package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class Cluster extends com.elhafi.cloudstack.CloudStack {

	public Cluster(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String addCluster(String clustername, String clustertype,
			String hypervisor, String zoneid, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("addCluster",
				optional);
		arguments.add(new NameValuePair("clustername", clustername));
		arguments.add(new NameValuePair("clustertype", clustertype));
		arguments.add(new NameValuePair("hypervisor", hypervisor));
		arguments.add(new NameValuePair("zoneid", zoneid));
		return Request(arguments);
	}

	public String deleteCluster(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("deleteCluster",
				null);
		arguments.add(new NameValuePair("id", id));

		return Request(arguments);
	}

	public String updateCluster(String id, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("updateCluster",
				optional);
		arguments.add(new NameValuePair("id", id));

		return Request(arguments);
	}

	public String listClusters(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listClusters",
				optional);

		return Request(arguments);
	}

}
