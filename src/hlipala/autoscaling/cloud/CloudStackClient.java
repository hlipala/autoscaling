package hlipala.autoscaling.cloud;

import hlipala.autoscaling.FileHandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.elhafi.cloudstack.api.root.Asyncjob;
import com.elhafi.cloudstack.api.root.LoadBalancer;
import com.elhafi.cloudstack.api.root.Project;
import com.elhafi.cloudstack.api.root.VirtualMachine;


public class CloudStackClient {

	private static final Logger log = Logger.getLogger( CloudStackClient.class );
	
	private static CloudStackClient singleton = null;
	
    private Pattern pattern = null;
	private Matcher matcher = null;
	private HashMap<String,String> config = null;
	
	private static Project project = null;
    private static LoadBalancer lb = null;
    private static VirtualMachine instance = null;
    private static Asyncjob asyncJob = null;
    
    private String lbPattern = "count\":(\\d.*) ,\"loadbalancerrule\" : \\[(.*)] } }";
	private String idPattern = "\"id\":\"(.+?)\"";
	private String zonePattern = "\"zoneid\":\"(.+?)\"";
	private String instancePattern = "\"id\":\"(.{36}?)\",\"name\"";

	List<String> scheduledScaleUpInstances = null;
	List<String> scheduledScaleDownInstances = null;
	
	public CloudStackClient() {
		Properties prop = new Properties();
		InputStream input = null;
		
		// Load CloudStack Properties File
		try {
			input = new FileInputStream("cloudStack.properties");
			prop.load(input);
			
		} catch (IOException e) {
			log.error("Could not load cloudStack.properties file", e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					log.error("Could not close cloudStack.properties file", e);
				}
			}
		}
		
		String hostIp = prop.getProperty("host");
		String apiKey = prop.getProperty("apiKey");
		String secretKey = prop.getProperty("secretKey");
		
		// Init Api
		project = new Project(hostIp, apiKey, secretKey);
		lb = new LoadBalancer(hostIp, apiKey, secretKey);
		instance = new VirtualMachine(hostIp, apiKey, secretKey);
		asyncJob = new Asyncjob(hostIp, apiKey, secretKey);
		
		// Init Queues
		scheduledScaleUpInstances = new LinkedList<String>();
		scheduledScaleDownInstances = new LinkedList<String>();
	}
	
	public static CloudStackClient getInstance() {
		if (singleton == null) {
			singleton = new CloudStackClient();
		}
		return singleton;
	}
	
	/**
	 * TESTED
	 */
	public String listAllLoadBalancer() {
		
		String entities = "";
		int count = 0;
		Map<String, String> policies = FileHandler.getInstance().listAutoScalePolicies();

		try {
			// List LoadBalancer from ROOT account
			pattern = Pattern.compile(lbPattern);
			matcher = pattern.matcher(lb.listLoadBalancerRules(null));

			while(matcher.find()) {
			    count += Integer.parseInt(matcher.group(1));
			    
			    // Check if autoscale already exist
			    Pattern lbpattern = Pattern.compile(instancePattern);
				Matcher lbmatcher = lbpattern.matcher(matcher.group(2));

				LinkedList<String> list = new LinkedList<String>();
				while(lbmatcher.find()) {
				    list.add(lbmatcher.group(1));
				}

				String result = matcher.group(2);
				for(String tmp : list) {
					if(policies.containsKey(tmp)) {
						 result = result.replace(tmp, tmp+"\",\"autoscaler\":\"enabled");
					} else {
						 result = result.replace(tmp, tmp+"\",\"autoscaler\":\"disabled");
					}
				}

				log.debug(result);
				
			    entities += result;
			    log.debug("LB: "+result);
			}
			
	        // List LoadBalancer from Projects
			List<String> projectIds = new LinkedList<String>();
			String projectResult = project.listProjects(null);
			
			// Extract Project IDs
			pattern = Pattern.compile(idPattern);
			matcher = pattern.matcher(projectResult);

			while(matcher.find()) {
				projectIds.add(matcher.group(1));
			}
			
			for(String projectId : projectIds) {
				config = new HashMap<String,String>();
		        config.put("projectid", projectId);

		        // Extract LoadBalancer from Projects
		        pattern = Pattern.compile(lbPattern);
		        matcher = pattern.matcher(lb.listLoadBalancerRules(config));

				while(matcher.find()) {
				    count += Integer.parseInt(matcher.group(1));
				    if(!entities.equals("")) {
				    	entities += ", ";
				    }
				    // Check if autoscale already exist
				    Pattern lbpattern = Pattern.compile(instancePattern);
					Matcher lbmatcher = lbpattern.matcher(matcher.group(2));

					LinkedList<String> list = new LinkedList<String>();
					while(lbmatcher.find()) {
						    list.add(lbmatcher.group(1));
					}

					String result = matcher.group(2);
					for(String tmp : list) {
						if(policies.containsKey(tmp)) {
							 result = result.replace(tmp, tmp+"\",\"autoscaler\":\"enabled");
						} else {
							 result = result.replace(tmp, tmp+"\",\"autoscaler\":\"disabled");
						}
					}

					log.debug(result);
					
				    entities += result;
				    log.debug("LB: "+result);
				}
			}
		} catch(Exception e) {
			log.error("Could not load all LoadBalancer", e);
		}
		
		return "jsonCallback({ \"listloadbalancerrulesresponse\" : { \"count\":"+count+" ,\"loadbalancerrule\" : [ "+entities+" ] } });";
	}
	
	/**
	 * TESTED
	 */
	public Map<String, Float> listAllInstancesFromLB(String loadBalancerId) {
		
		Map<String, Float> instances = new HashMap<String, Float>();
		
		try {
			String lbResult = lb.listLoadBalancerRuleInstances(loadBalancerId, null);
			
			JSONObject lbResponse = (JSONObject)new JSONParser().parse(lbResult);
			JSONObject j1 = (JSONObject) lbResponse.get("listloadbalancerruleinstancesresponse");
			JSONArray j2 = (JSONArray) j1.get("loadbalancerruleinstance");
			
			for(Object obj : j2) {
				JSONObject json = (JSONObject) obj;
				
				String id = json.get("id").toString();
				Float cpuUsed =  Float.parseFloat(json.get("cpuused") != null ? json.get("cpuused").toString().replace("%", "") : "0.00");
				instances.put(id, cpuUsed);
			}
		} catch (Exception e) {
			log.error("Could not list all Instances from LoadBalancer", e);
		}
		
		return instances;
	}
	
	/**
	 * TESTED
	 */
	public String createInstance(String templateId, String offeringId, String zoneId) {
		
		log.debug("templateId: "+templateId);
		log.debug("offeringId: "+offeringId);
		log.debug("zoneId: "+zoneId);
		String instanceId = "";
		
		try {
			String instanceResult = instance.deployVirtualMachine(offeringId, templateId, zoneId, null);
			pattern = Pattern.compile(idPattern);
			matcher = pattern.matcher(instanceResult);

			while(matcher.find()) {
				instanceId = matcher.group(1);
			}
		} catch (Exception e) {
			log.error("Could not create a new Instance", e);
		}
		
		return instanceId;
	}
	
	/**
	 * TESTED
	 */
	public String deleteInstance(String instanceId) {
		String result = "";
		try {
			result = instance.destroyVirtualMachine(instanceId);
		} catch (Exception e) {
			log.error("Could not destroy instance "+instanceId, e);
		}
		
		log.debug("deleteInstance");
		return result;
	}
	
	/**
	 * TESTED
	 */
	public String addInstaceToLoadBalancer(String instanceId, String loadBalancerId) {
		String result = "";
		try {
			result = lb.assignToLoadBalancerRule(loadBalancerId, instanceId);
		} catch (Exception e) {
			log.error("Could not add Instance "+instanceId+" to LoadBalancer", e);
		}
		
		log.debug("addInstance");
		return result;
	}
	
	/**
	 * TESTED
	 */
	public String removeInstanceFromLoadBalancer(String instanceId, String loadBalancerId) {
		String result = "";
		try {
			result = lb.removeFromLoadBalancerRule(loadBalancerId, instanceId);
		} catch (Exception e) {
			log.error("Could not remove Instance "+instanceId+" from LoadBalancer", e);
		}
		
		log.debug("removeInstanceFromLoadBalancer");
		return result;
	}
	
	/**
	 * TESTED
	 */
	public String getLeastUsedInstance(String loadBalancerId) {

		Map<String, Float> instances = listAllInstancesFromLB(loadBalancerId);
		
		String result = "";
		float cpuUsage = 100.0f;
		
		for (String instance : instances.keySet()) {
			if(instances.get(instance) < cpuUsage) {
				result = instance;
				cpuUsage = instances.get(instance);
			}
		}
		log.debug("getLeastUsedInstance: "+ result);
		return result;
	}
	
	/**
	 * TESTED
	 */
	public String getZoneOfLB(String loadBalancer) {
		log.debug("getZoneOfLB");
		
		config = new HashMap<String,String>();
        config.put("id", loadBalancer);
        
        String zoneId = "";
        
		try {
			String lbResult = lb.listLoadBalancerRules(config);
			
			pattern = Pattern.compile(zonePattern);
			matcher = pattern.matcher(lbResult);

			while(matcher.find()) {
				zoneId = matcher.group(1);
			}
			
			log.debug("ZoneID: "+zoneId);
			
		} catch (Exception e) {
			log.error("Can not get Zone id of LoadBalancer", e);
		}
		
		return zoneId;
	}
	
	public String getJobStatus(String jobId) {
		String result = "0";
		try {
			String response = asyncJob.queryAsyncJobResult(jobId);
			JSONObject responseJson = (JSONObject)new JSONParser().parse(response);
			JSONObject j1 = (JSONObject) responseJson.get("queryasyncjobresultresponse");
    		result = j1.get("jobstatus").toString();
		} catch (Exception e) {
			log.error("Could not get Job Status",e);
		}
		return result;
	}
	
	public void addInstanceToScaleUpSchedule(String instanceId) {
		scheduledScaleUpInstances.add(instanceId);
	}
	
	public void removeInstanceFromScaleUpSchedule(String instanceId, String loadBalancerId) {
		scheduledScaleUpInstances.remove(instanceId);
	}
	
	public int countOfScaleUpScheduledInstances() {
		return scheduledScaleUpInstances.size();
	}
	
	public void addInstanceToScaleDownSchedule(String instanceId) {
		scheduledScaleDownInstances.add(instanceId);
	}
	
	public void removeInstanceFromScaleDownSchedule(String instanceId, String loadBalancerId) {
		scheduledScaleDownInstances.remove(instanceId);
	}
	
	public int countOfScaleDownScheduledInstances() {
		return scheduledScaleDownInstances.size();
	}
	
}