package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class AutoScale extends com.elhafi.cloudstack.CloudStack {

	public AutoScale(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String createCounter(String name, String source, String value)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("createCounter",
				null);
		arguments.add(new NameValuePair("name", name));
		arguments.add(new NameValuePair("source", source));
		arguments.add(new NameValuePair("value", value));

		return Request(arguments);
	}

	public String createCondition(String counterid, String relationaloperator,
			String threshold, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("createCondition",
				optional);
		arguments.add(new NameValuePair("counterid", counterid));
		arguments.add(new NameValuePair("relationaloperator",
				relationaloperator));
		arguments.add(new NameValuePair("threshold", threshold));
		return Request(arguments);
	}

	public String createAutoScalePolicy(String action, String conditionids,
			String duration, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"createAutoScalePolicy", optional);
		arguments.add(new NameValuePair("action", action));
		arguments.add(new NameValuePair("conditionids", conditionids));
		arguments.add(new NameValuePair("duration", duration));
		return Request(arguments);
	}

	public String createAutoScaleVmProfile(String serviceofferingid,
			String templateid, String zoneid, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"createAutoScaleVmProfile", optional);
		arguments
				.add(new NameValuePair("serviceofferingid", serviceofferingid));
		arguments.add(new NameValuePair("templateid", templateid));
		arguments.add(new NameValuePair("zoneid", zoneid));
		return Request(arguments);
	}

	public String createAutoScaleVmGroup(String lbruleid, String maxmembers,
			String minmembers, String scaledownpolicyids,
			String scaleuppolicyids, String vmprofileid,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"createAutoScaleVmGroup", optional);
		arguments.add(new NameValuePair("lbruleid", lbruleid));
		arguments.add(new NameValuePair("maxmembers", maxmembers));
		arguments.add(new NameValuePair("minmembers", minmembers));
		arguments.add(new NameValuePair("scaledownpolicyids",
				scaledownpolicyids));
		arguments.add(new NameValuePair("scaleuppolicyids", scaleuppolicyids));
		arguments.add(new NameValuePair("vmprofileid", vmprofileid));
		return Request(arguments);
	}

	public String deleteCounter(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("deleteCounter",
				null);
		arguments.add(new NameValuePair("id", id));

		return Request(arguments);
	}

	public String deleteCondition(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("deleteCondition",
				null);
		arguments.add(new NameValuePair("id", id));

		return Request(arguments);
	}

	public String deleteAutoScalePolicy(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"deleteAutoScalePolicy", null);
		arguments.add(new NameValuePair("id", id));

		return Request(arguments);
	}

	public String deleteAutoScaleVmProfile(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"deleteAutoScaleVmProfile", null);
		arguments.add(new NameValuePair("id", id));

		return Request(arguments);
	}

	public String deleteAutoScaleVmGroup(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"deleteAutoScaleVmGroup", null);
		arguments.add(new NameValuePair("id", id));

		return Request(arguments);
	}

	public String listCounters(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listCounters",
				optional);
		return Request(arguments);
	}

	public String listConditions(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listConditions",
				optional);
		return Request(arguments);
	}

	public String listAutoScalePolicies(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"listAutoScalePolicies", optional);
		return Request(arguments);
	}

	public String listAutoScaleVmProfiles(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"listAutoScaleVmProfiles", optional);
		return Request(arguments);
	}

	public String listAutoScaleVmGroups(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"listAutoScaleVmGroups", optional);
		return Request(arguments);
	}

	public String enableAutoScaleVmGroup(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"enableAutoScaleVmGroup", null);
		arguments.add(new NameValuePair("id", id));

		return Request(arguments);
	}

	public String disableAutoScaleVmGroup(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"disableAutoScaleVmGroup", null);
		arguments.add(new NameValuePair("id", id));

		return Request(arguments);
	}

	public String updateAutoScalePolicy(String id,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"updateAutoScalePolicy", optional);
		arguments.add(new NameValuePair("id", id));

		return Request(arguments);
	}

	public String updateAutoScaleVmProfile(String id,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"updateAutoScaleVmProfile", optional);
		arguments.add(new NameValuePair("id", id));

		return Request(arguments);
	}

	public String updateAutoScaleVmGroup(String id,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"updateAutoScaleVmGroup", optional);
		arguments.add(new NameValuePair("id", id));

		return Request(arguments);
	}

}
