package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class Network extends com.elhafi.cloudstack.CloudStack {

	public Network(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String dedicatePublicIpRange(String id, String account,
			String domainid, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"dedicatePublicIpRange", optional);
		arguments.add(new NameValuePair("id", id));
		arguments.add(new NameValuePair("account", account));
		arguments.add(new NameValuePair("domainid", domainid));

		return Request(arguments);
	}

	public String releasePublicIpRange(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"releasePublicIpRange", null);
		arguments.add(new NameValuePair("id", id));

		return Request(arguments);
	}

	public String createNetwork(String displaytext, String name,
			String networkofferingid, String zoneid,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("createNetwork",
				optional);
		arguments.add(new NameValuePair("displaytext", displaytext));
		arguments.add(new NameValuePair("name", name));
		arguments
				.add(new NameValuePair("networkofferingid", networkofferingid));
		arguments.add(new NameValuePair("zoneid", zoneid));
		return Request(arguments);
	}

	public String deleteNetwork(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("deleteNetwork",
				null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String listNetworks(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listNetworks",
				optional);
		return Request(arguments);
	}

	public String restartNetwork(String id, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("restartNetwork",
				optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String updateNetwork(String id, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("updateNetwork",
				optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String createPhysicalNetwork(String name, String zoneid,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"createPhysicalNetwork", optional);
		arguments.add(new NameValuePair("name", name));
		arguments.add(new NameValuePair("zoneid", zoneid));
		return Request(arguments);
	}

	public String deletePhysicalNetwork(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"deletePhysicalNetwork", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String listPhysicalNetworks(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"listPhysicalNetworks", optional);
		return Request(arguments);
	}

	public String updatePhysicalNetwork(String id,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"updatePhysicalNetwork", optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String listSupportedNetworkServices(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"listSupportedNetworkServices", optional);
		return Request(arguments);
	}

	public String addNetworkServiceProvider(String name,
			String physicalnetworkid, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"addNetworkServiceProvider", optional);
		arguments.add(new NameValuePair("name", name));
		arguments
				.add(new NameValuePair("physicalnetworkid", physicalnetworkid));
		return Request(arguments);
	}

	public String deleteNetworkServiceProvider(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"deleteNetworkServiceProvider", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String listNetworkServiceProviders(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"listNetworkServiceProviders", optional);
		return Request(arguments);
	}

	public String updateNetworkServiceProvider(String id,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"updateNetworkServiceProvider", optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String createStorageNetworkIpRange(String gateway, String netmask,
			String podid, String startip, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"createStorageNetworkIpRange", optional);
		arguments.add(new NameValuePair("gateway", gateway));
		arguments.add(new NameValuePair("netmask", netmask));
		arguments.add(new NameValuePair("podid", podid));
		arguments.add(new NameValuePair("startip", startip));
		return Request(arguments);
	}

	public String deleteStorageNetworkIpRange(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"deleteStorageNetworkIpRange", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String listStorageNetworkIpRange(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"listStorageNetworkIpRange", optional);
		return Request(arguments);
	}

	public String updateStorageNetworkIpRange(String id,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"updateStorageNetworkIpRange", optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String listNiciraNvpDeviceNetworks(String nvpdeviceid,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"listNiciraNvpDeviceNetworks", optional);
		arguments.add(new NameValuePair("nvpdeviceid", nvpdeviceid));
		return Request(arguments);
	}

	public String listNetworkIsolationMethods(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"listNetworkIsolationMethods", optional);
		return Request(arguments);
	}

}
