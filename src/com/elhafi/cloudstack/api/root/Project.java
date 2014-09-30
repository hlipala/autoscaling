package com.elhafi.cloudstack.api.root;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;

public class Project extends com.elhafi.cloudstack.CloudStack {

	  public Project(String ip, String new_apikey, String new_secret) {
		super(ip, new_apikey, new_secret);
		// TODO Auto-generated constructor stub
	}
	public String createProject(String displaytext,String name, HashMap<String,String> optional) throws Exception {
		    LinkedList<NameValuePair> arguments = newQueryValues("createProject",optional);
		    arguments.add(new NameValuePair("displaytext",displaytext));
		    arguments.add(new NameValuePair("name",name));
		    return Request(arguments);
		  }
	  public String deleteProject(String id) throws Exception {
		    LinkedList<NameValuePair> arguments = newQueryValues("deleteProject",null);
		    arguments.add(new NameValuePair("id",id));
		    return Request(arguments);
		  }
	  
	  public String updateProject(String id, HashMap<String,String> optional) throws Exception {
		    LinkedList<NameValuePair> arguments = newQueryValues("updateProject",optional);
		    arguments.add(new NameValuePair("id",id));
		    return Request(arguments);
		  }
	  
	  public String activateProject(String id) throws Exception {
		    LinkedList<NameValuePair> arguments = newQueryValues("activateProject",null);
		    arguments.add(new NameValuePair("id",id));
		    return Request(arguments);
		  }
	  
	  public String suspendProject(String id) throws Exception {
		    LinkedList<NameValuePair> arguments = newQueryValues("suspendProject",null);
		    arguments.add(new NameValuePair("id",id));
		    return Request(arguments);
		  }
	  
	  public String listProjects( HashMap<String,String> optional) throws Exception {
		    LinkedList<NameValuePair> arguments = newQueryValues("listProjects",optional);
		    return Request(arguments);
		  }
	  
	  public String listProjectInvitations( HashMap<String,String> optional) throws Exception {
		    LinkedList<NameValuePair> arguments = newQueryValues("listProjectInvitations",optional);
		    return Request(arguments);
		  }
	  
	  public String updateProjectInvitation(String projectid, HashMap<String,String> optional) throws Exception {
		    LinkedList<NameValuePair> arguments = newQueryValues("updateProjectInvitation",optional);
		    arguments.add(new NameValuePair("projectid",projectid));
		    return Request(arguments);
		  }
	  
	  public String deleteProjectInvitation(String id) throws Exception {
		    LinkedList<NameValuePair> arguments = newQueryValues("deleteProjectInvitation",null);
		    arguments.add(new NameValuePair("id",id));
		    return Request(arguments);
		  }
	

}
