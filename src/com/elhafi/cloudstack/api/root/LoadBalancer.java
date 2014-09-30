package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class LoadBalancer extends com.elhafi.cloudstack.CloudStack {

	public LoadBalancer(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String createLoadBalancerRule(String algorithm, String name,
			String privateport, String publicport,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"createLoadBalancerRule", optional);
		arguments.add(new NameValuePair("algorithm", algorithm));
		arguments.add(new NameValuePair("name", name));
		arguments.add(new NameValuePair("privateport", privateport));
		arguments.add(new NameValuePair("publicport", publicport));
		return Request(arguments);
	}

	public String deleteLoadBalancerRule(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"deleteLoadBalancerRule", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String removeFromLoadBalancerRule(String id, String virtualmachineids)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"removeFromLoadBalancerRule", null);
		arguments.add(new NameValuePair("id", id));
		arguments
				.add(new NameValuePair("virtualmachineids", virtualmachineids));
		return Request(arguments);
	}

	public String assignToLoadBalancerRule(String id, String virtualmachineids)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"assignToLoadBalancerRule", null);
		arguments.add(new NameValuePair("id", id));
		arguments
				.add(new NameValuePair("virtualmachineids", virtualmachineids));
		return Request(arguments);
	}

	public String createLBStickinessPolicy(String lbruleid, String methodname,
			String name, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"createLBStickinessPolicy", optional);
		arguments.add(new NameValuePair("lbruleid", lbruleid));
		arguments.add(new NameValuePair("methodname", lbruleid));
		arguments.add(new NameValuePair("name", lbruleid));
		return Request(arguments);
	}

	public String deleteLBStickinessPolicy(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"deleteLBStickinessPolicy", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String listLoadBalancerRules(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"listLoadBalancerRules", optional);
		return Request(arguments);
	}

	public String listLBStickinessPolicies(String lbruleid,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"listLBStickinessPolicies", optional);
		arguments.add(new NameValuePair("lbruleid", lbruleid));
		return Request(arguments);
	}

	public String listLBHealthCheckPolicies(String lbruleid,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"listLBHealthCheckPolicies", optional);
		arguments.add(new NameValuePair("lbruleid", lbruleid));
		return Request(arguments);
	}

	public String createLBHealthCheckPolicy(String lbruleid,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"createLBHealthCheckPolicy", optional);
		arguments.add(new NameValuePair("lbruleid", lbruleid));
		return Request(arguments);
	}

	public String deleteLBHealthCheckPolicy(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"deleteLBHealthCheckPolicy", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String listLoadBalancerRuleInstances(String id,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"listLoadBalancerRuleInstances", optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String updateLoadBalancerRule(String id,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"updateLoadBalancerRule", optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String createGlobalLoadBalancerRule(String gslbdomainname,
			String gslbservicetype, String name, String regionid,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"createGlobalLoadBalancerRule", optional);
		arguments.add(new NameValuePair("gslbdomainname", gslbdomainname));
		arguments.add(new NameValuePair("gslbservicetype", gslbservicetype));
		arguments.add(new NameValuePair("name", name));
		arguments.add(new NameValuePair("regionid", regionid));
		return Request(arguments);
	}

	public String deleteGlobalLoadBalancerRule(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"deleteGlobalLoadBalancerRule", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String updateGlobalLoadBalancerRule(String id,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"updateGlobalLoadBalancerRule", optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String listGlobalLoadBalancerRules(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"listGlobalLoadBalancerRules", optional);
		return Request(arguments);
	}

	public String assignToGlobalLoadBalancerRule(String id,
			String loadbalancerrulelist, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"assignToGlobalLoadBalancerRule", optional);
		arguments.add(new NameValuePair("id", id));
		arguments.add(new NameValuePair("loadbalancerrulelist",
				loadbalancerrulelist));
		return Request(arguments);
	}

	public String removeFromGlobalLoadBalancerRule(String id,
			String loadbalancerrulelist) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"removeFromGlobalLoadBalancerRule", null);
		arguments.add(new NameValuePair("id", id));
		arguments.add(new NameValuePair("loadbalancerrulelist",
				loadbalancerrulelist));
		return Request(arguments);
	}

	public String createLoadBalancer(String algorithm, String instanceport,
			String name, String networkid, String scheme,
			String sourceipaddressnetworkid, String sourceport,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"createLoadBalancer", optional);
		arguments.add(new NameValuePair("algorithm", algorithm));
		arguments.add(new NameValuePair("instanceport", instanceport));
		arguments.add(new NameValuePair("name", name));
		arguments.add(new NameValuePair("networkid", networkid));
		arguments.add(new NameValuePair("scheme", scheme));
		arguments.add(new NameValuePair("sourceipaddressnetworkid",
				sourceipaddressnetworkid));
		arguments.add(new NameValuePair("sourceport", sourceport));
		return Request(arguments);
	}

	public String listLoadBalancers(HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"listLoadBalancers", optional);
		return Request(arguments);
	}

	public String deleteLoadBalancer(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"deleteLoadBalancer", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

}
