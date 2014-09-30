package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class Volume extends com.elhafi.cloudstack.CloudStack {

	public Volume(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	// Section: Volume
	public String attachVolume(String id, String virtualmachineid,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("attachVolume",
				optional);
		arguments.add(new NameValuePair("id", id));
		arguments.add(new NameValuePair("virtualmachineid", virtualmachineid));
		return Request(arguments);
	}

	public String detachVolume(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("detachVolume",
				optional);
		return Request(arguments);
	}

	public String createVolume(String name, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("createVolume",
				optional);
		arguments.add(new NameValuePair("name", name));
		return Request(arguments);
	}

	public String deleteVolume(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("deleteVolume",
				null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String listVolumes(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listVolumes",
				optional);
		return Request(arguments);
	}

	public String extractVolume(String id, String mode, String zoneid,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("extractVolume",
				optional);
		arguments.add(new NameValuePair("id", id));
		arguments.add(new NameValuePair("mode", mode));
		arguments.add(new NameValuePair("zoneid", zoneid));
		return Request(arguments);
	}

	public String createVolumeOnFiler(String aggregatename, String ipaddress,
			String password, String poolname, String size, String username,
			String volumename, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"createVolumeOnFiler", optional);
		arguments.add(new NameValuePair("aggregatename", aggregatename));
		arguments.add(new NameValuePair("ipaddress", ipaddress));
		arguments.add(new NameValuePair("password", password));
		arguments.add(new NameValuePair("poolname", poolname));
		arguments.add(new NameValuePair("size", size));
		arguments.add(new NameValuePair("username", username));
		arguments.add(new NameValuePair("volumename", volumename));
		return Request(arguments);
	}

	public String destroyVolumeOnFiler(String aggregatename, String ipaddress,
			String volumename) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"destroyVolumeOnFiler", null);
		arguments.add(new NameValuePair("aggregatename", aggregatename));
		arguments.add(new NameValuePair("ipaddress", ipaddress));
		arguments.add(new NameValuePair("volumename", volumename));
		return Request(arguments);
	}

	public String listVolumesOnFiler(String poolname) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"listVolumesOnFiler", null);
		arguments.add(new NameValuePair("poolname", poolname));
		return Request(arguments);
	}

	public String migrateVolume(String storageid, String volumeid)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("migrateVolume",
				null);
		arguments.add(new NameValuePair("storageid", storageid));
		arguments.add(new NameValuePair("volumeid", volumeid));
		return Request(arguments);
	}

}
