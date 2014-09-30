package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class Template extends com.elhafi.cloudstack.CloudStack {

	public Template(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}

	public String createTemplate(String displaytext, String name,
			String ostypeid, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("createTemplate",
				optional);
		arguments.add(new NameValuePair("displaytext", displaytext));
		arguments.add(new NameValuePair("name", name));
		arguments.add(new NameValuePair("ostypeid", ostypeid));
		return Request(arguments);
	}

	public String registerTemplate(String displaytext, String format,
			String hypervisor, String name, String ostypeid, String url,
			String zoneid, HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"registerTemplate", optional);
		arguments.add(new NameValuePair("displaytext", displaytext));
		arguments.add(new NameValuePair("format", format));
		arguments.add(new NameValuePair("hypervisor", hypervisor));
		arguments.add(new NameValuePair("name", name));
		arguments.add(new NameValuePair("ostypeid", ostypeid));
		arguments.add(new NameValuePair("url", url));
		arguments.add(new NameValuePair("zoneid", zoneid));
		return Request(arguments);
	}

	public String updateTemplate(String id, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("updateTemplate",
				optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String copyTemplate(String id, String destzoneid, String sourcezoneid)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("copyTemplate",
				null);
		arguments.add(new NameValuePair("id", id));
		arguments.add(new NameValuePair("destzoneid", destzoneid));
		arguments.add(new NameValuePair("sourcezoneid", sourcezoneid));
		return Request(arguments);
	}

	public String deleteTemplate(String id, HashMap<String, String> optional)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("deleteTemplate",
				optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String listTemplates(String templatefilter,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("listTemplates",
				optional);
		arguments.add(new NameValuePair("templatefilter", templatefilter));
		return Request(arguments);
	}

	public String updateTemplatePermissions(String id,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"updateTemplatePermissions", optional);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String listTemplatePermissions(String id) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues(
				"listTemplatePermissions", null);
		arguments.add(new NameValuePair("id", id));
		return Request(arguments);
	}

	public String extractTemplate(String id, String mode,
			HashMap<String, String> optional) throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("extractTemplate",
				optional);
		arguments.add(new NameValuePair("id", id));
		arguments.add(new NameValuePair("mode", mode));
		return Request(arguments);
	}

	public String prepareTemplate(String templateid, String zoneid)
			throws Exception {
		LinkedList<NameValuePair> arguments = newQueryValues("copyTemplate",
				null);
		arguments.add(new NameValuePair("templateid", templateid));
		arguments.add(new NameValuePair("zoneid", zoneid));
		return Request(arguments);
	}

}
