package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class VirtualMachine extends com.elhafi.cloudstack.CloudStack {

	public VirtualMachine(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String deployVirtualMachine(String serviceofferingid,
			String templateid, String zoneid, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("deployVirtualMachine", optional);
		arguments.add(new NameValuePair("serviceofferingid", serviceofferingid));
		arguments.add(new NameValuePair("templateid", templateid));
		arguments.add(new NameValuePair("zoneid", zoneid));
		return Request(arguments);
	}

	public String destroyVirtualMachine(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"destroyVirtualMachine", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String rebootVirtualMachine(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"rebootVirtualMachine", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String startVirtualMachine(String id,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"startVirtualMachine", optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String stopVirtualMachine(String id, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"stopVirtualMachine", optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String resetPasswordForVirtualMachine(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"resetPasswordForVirtualMachine", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String updateVirtualMachine(String id,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"updateVirtualMachine", optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String listVirtualMachines(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"listVirtualMachines", optional);
		return Request(arguments);
	}

	public String getVMPassword(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("getVMPassword",
				null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String restoreVirtualMachine(String virtualmachineid,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"restoreVirtualMachine", optional);
		arguments.add(new NameValuePair("virtualmachineid", virtualmachineid));
		return Request(arguments);
	}

	public String changeServiceForVirtualMachine(String id,
			String serviceofferingid) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"changeServiceForVirtualMachine", null);
		arguments.add(new NameValuePair("id", id));
		arguments
				.add(new NameValuePair("serviceofferingid", serviceofferingid));
		return Request(arguments);
	}

	public String scaleVirtualMachine(String id, String serviceofferingid)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"scaleVirtualMachine", null);
		arguments.add(new NameValuePair("id", id));
		arguments
				.add(new NameValuePair("serviceofferingid", serviceofferingid));
		return Request(arguments);
	}

	public String assignVirtualMachine(String account, String domainid,
			String virtualmachineid, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"assignVirtualMachine", optional);
		arguments.add(new NameValuePair("account", account));
		arguments.add(new NameValuePair("domainid", domainid));
		arguments.add(new NameValuePair("virtualmachineid", virtualmachineid));
		return Request(arguments);
	}

	public String migrateVirtualMachine(String virtualmachineid,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"migrateVirtualMachine", optional);
		arguments.add(new NameValuePair("virtualmachineid", virtualmachineid));
		return Request(arguments);
	}

	public String migrateVirtualMachineWithVolume(String hostid,
			String virtualmachineid, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"migrateVirtualMachineWithVolume", optional);
		arguments.add(new NameValuePair("hostid", hostid));
		arguments.add(new NameValuePair("virtualmachineid", virtualmachineid));
		return Request(arguments);
	}

	public String recoverVirtualMachine(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"recoverVirtualMachine", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String cleanVMReservations() throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"cleanVMReservations", null);
		return Request(arguments);
	}

	public String addNicToVirtualMachine(String networkid,
			String virtualmachineid, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"addNicToVirtualMachine", optional);
		arguments.add(new NameValuePair("networkid", networkid));
		arguments.add(new NameValuePair("virtualmachineid", virtualmachineid));
		return Request(arguments);
	}

	public String removeNicFromVirtualMachine(String nicid,
			String virtualmachineid) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"removeNicFromVirtualMachine", null);
		arguments.add(new NameValuePair("nicid", nicid));
		arguments.add(new NameValuePair("virtualmachineid", virtualmachineid));
		return Request(arguments);
	}

	public String updateDefaultNicForVirtualMachine(String nicid,
			String virtualmachineid) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"updateDefaultNicForVirtualMachine", null);
		arguments.add(new NameValuePair("nicid", nicid));
		arguments.add(new NameValuePair("virtualmachineid", virtualmachineid));
		return Request(arguments);
	}

}
