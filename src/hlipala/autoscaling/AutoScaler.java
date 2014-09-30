package hlipala.autoscaling;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import hlipala.autoscaling.cloud.CloudStackClient;

public class AutoScaler {
	
	// Initialize
	private static final Logger log = Logger.getLogger( AutoScaler.class );

	private static AutoScaler singleton = null;
	private CloudStackClient cloud = null;
	private FileHandler storage = null;
	
	private Map<String, Date> scaleUpCounter = null;
	private Map<String, Date> scaleDownCounter = null;
	
	private Timer timer = null;
	
	// Settings
	//private static int scaleDownDelay = 15;
	
	
	public AutoScaler() {
		this.cloud = CloudStackClient.getInstance();
		this.storage = FileHandler.getInstance();
		
		scaleUpCounter = new HashMap<String, Date>();
		scaleDownCounter = new HashMap<String, Date>();
		
		timer = new Timer();
	}

	public static AutoScaler getInstance() {
		if (singleton == null) {
			singleton = new AutoScaler();
		}
		return singleton;
	}
	
	/**
	 * Here happens the magic. This Method contains the algorithm of the scaling.
	 * 
	 * Policy array:
	 * 0:	loadBalancerId
	 * 1:	templateId
	 * 2:	offeringId 
	 * 3:	minInstances
	 * 4:	maxInstances
	 * 5	scaleUpValue
	 * 6:	scaleUpDuration
	 * 7:	scaleUpSleep
	 * 8:	scaleDownValue
	 * 9:	scaleDownDuration
	 */
	public void scale() {
		log.info("scaling...");
		
		Map<String, String> loadBalancer = storage.listAutoScalePolicies();
		log.debug(loadBalancer);
		
		//for each autoScaled LB
		for (String key : loadBalancer.keySet()) {
			
			// Decode Policy
			String[] policy = loadBalancer.get(key).split("\\|");
			String[] tmpPolicy = new String[policy.length+1];
			tmpPolicy[0] = key;
			int j = 1;
			for(String tmp : policy) {
				tmpPolicy[j] = tmp;
				j++;
			}
			policy = tmpPolicy;
			/* Policy array:
				0:	loadBalancerId
				1:	templateId
				2:	offeringId 
				3:	minInstances
				4:	maxInstances
				5	scaleUpValue
				6:	scaleUpDuration
				7:	scaleUpSleep
				8:	scaleDownValue
				9:	scaleDownDuration
			 */
			String policyString = "";
			for(String tmp : policy) {
				policyString += tmp+",";
			}
			log.debug("Policy: "+policyString);
			
			Map<String, Float> instances = cloud.listAllInstancesFromLB(key);
			log.debug("Instances: "+instances);
			
			int minVM = Integer.parseInt(policy[3]);
			int maxVM = Integer.parseInt(policy[4]);
			int runningInstances = instances.size();
			
			log.info("Actual Running: "+instances.size()+", Up-Queued: "+cloud.countOfScaleUpScheduledInstances()+", Down-Queued: "+cloud.countOfScaleDownScheduledInstances()+", MinVM: "+minVM+", MaxVM: "+maxVM);
			if(runningInstances+cloud.countOfScaleUpScheduledInstances() < minVM ) {
				
				int addInstances = Integer.parseInt(policy[3]) - runningInstances;
				log.info("Missing Instances: "+addInstances);
				for(int i=0; i<addInstances; i++) {
					scaleUp(policy);
				}
			}
			else if(runningInstances > maxVM ) {
				
				int removeInstances = instances.size() - maxVM;
				log.info("Remove instances:" +removeInstances);
				for(int i=0; i<removeInstances; i++) {
					scaleDown(policy);
				}
			}
			else {
				float averageCpuUsage = 0;
				
				for (String instance : instances.keySet()) {
					averageCpuUsage += instances.get(instance);
				}
				
				averageCpuUsage = averageCpuUsage/instances.size();
				
				if(averageCpuUsage > Float.parseFloat(policy[5])) {
					
					
					if(averageCpuUsage < Float.parseFloat(policy[5]) && scaleUpCounter.containsKey(policy[0])) {
						// Usage is smaller then autoScalePolicy -> remove from watch list
						scaleUpCounter.remove(policy[0]);
					}
					
					
					//start counting time (or check if one exist already)
					if(scaleUpCounter.containsKey(policy[0])) {

						Date now = new Date();
						long seconds = ((scaleUpCounter.get(policy[0]).getTime()-now.getTime())/1000)*-1;

						log.info("Up-Scale counter exists for Load-Balancer "+policy[0]+", its in list now for "+seconds+"sec., Waiting time: "+policy[6]);
						if(Integer.parseInt(policy[6]) <= seconds) {
							scaleUpCounter.remove(policy[0]);
							if(runningInstances < maxVM) {
								scaleUp(policy);
								log.info("Up-Scale succeded. CPU-Usage was high for "+seconds+"sec., Up-Scale-Policy: "+policy[6]);
							} else log.info("Up-Scale Abort. To much vms are running.");
						}
					} else {
						// Add to watch list
						if(runningInstances < maxVM) {
							scaleUpCounter.put(policy[0], new Date());
							log.info("Add Load-Balancer "+policy[0]+" to Up-Scale counter");
						} else log.info("Up-Scale counter not started. To much vms are running.");
					}
				}
				else if(averageCpuUsage < Float.parseFloat(policy[8])) {

					
					if(averageCpuUsage > Float.parseFloat(policy[8]) && scaleDownCounter.containsKey(policy[0])) {
						// Usage is bigger then autoScalePolicy -> remove from watch list
						scaleDownCounter.remove(policy[0]);
					}
					
					log.info("CPU-Usage is low. DownScale-Policy activated.");
					
					//start counting time (or check if one exist already)
					if(scaleDownCounter.containsKey(policy[0])) {

						Date now = new Date();
						long seconds = ((scaleDownCounter.get(policy[0]).getTime()-now.getTime())/1000)*-1;
						
						log.info("Down-Scale counter exists for Load-Balancer "+policy[0]+", its in list now for "+seconds+"sec., Waiting time: "+policy[6]);
						if(Integer.parseInt(policy[9]) <= seconds) {
							scaleDownCounter.remove(policy[0]);
							if(runningInstances > minVM) {
								scaleDown(policy);
								log.info("Down-Scale succeded. CPU-Usage was low for "+seconds+"sec., Down-Scale-Policy: "+policy[9]);
							} else log.info("Down-Scale Abort. Not enough vms are running.");
						}
					} else {
						// Add to watch list
						if(runningInstances > minVM) {
							scaleDownCounter.put(policy[0], new Date());
							log.info("Add Load-Balancer "+policy[0]+" to Down-Scale counter");
						} else log.info("DownScale counter not started. Not enough vms are running.");
					}
				}
			}
		}
	}
	
	private void scaleUp(String[] policy) {
		log.info("ScaleUp");
		
		String zoneId = cloud.getZoneOfLB(policy[0]);
		String instanceId = cloud.createInstance(policy[1], policy[2], zoneId);

		cloud.addInstanceToScaleUpSchedule(instanceId);
		log.info("Instance "+instanceId+" created.");
		
		String loadBalancerId = policy[0];

		int delay = Integer.parseInt(policy[7]);
		log.info("Schedule addtoLB, with Delay: "+delay);
		
		timer.schedule(new ScaleUp(cloud, instanceId, loadBalancerId), 1000 * delay);
	}
	
	private void scaleDown(String[] policy) {
		log.info("ScaleDown");
		
		String loadBalancerId = policy[0];
		String instanceId = cloud.getLeastUsedInstance(loadBalancerId);
		
		cloud.addInstanceToScaleDownSchedule(instanceId);
		
		// Remove Instance from LB
		String response = cloud.removeInstanceFromLoadBalancer(instanceId, loadBalancerId);
		log.info("Remove Instance from LB: "+response);
		JSONObject jobJson;
		try {
			jobJson = (JSONObject)new JSONParser().parse(response);
			
			JSONObject j1 = (JSONObject) jobJson.get("removefromloadbalancerruleresponse");
			String jobId = j1.get("jobid").toString();

			// Check if removed
			boolean done = false;
	    	while(!done) {
				if(cloud.getJobStatus(jobId).equals("1")) {
					// Delete Instance
			    	cloud.removeInstanceFromScaleDownSchedule(instanceId, loadBalancerId);
					cloud.deleteInstance(instanceId);
			    	done = true;
				};
	    	}
	    	log.info("Instance "+instanceId+" removed from Load-Balancer "+loadBalancerId);
		} catch (ParseException e) {
			log.error("Could not parse response.",e);
		}
	}
	
}

class ScaleUp extends TimerTask {

	private static final Logger log = Logger.getLogger( ScaleUp.class );
	
    private final CloudStackClient cloud;
    private final String instanceId;
    private final String loadBalancerId;

    ScaleUp (CloudStackClient cloud, String instanceId, String loadBalancerId)
    {
    	this.cloud = cloud;
        this.instanceId = instanceId;
        this.loadBalancerId = loadBalancerId;
    }

    public void run() {
    	log.info("ScaleUpTimer - Add Instance "+instanceId+" to Load-Balancer "+loadBalancerId);
    	
    	String response = cloud.addInstaceToLoadBalancer(instanceId, loadBalancerId);
    	
    	JSONObject jobJson;
		try {
			jobJson = (JSONObject)new JSONParser().parse(response);
		
			JSONObject j1 = (JSONObject) jobJson.get("assigntoloadbalancerruleresponse");
			String jobId = j1.get("jobid").toString();
	
			boolean done = false;
	    	while(!done) {
				if(cloud.getJobStatus(jobId).equals("1")) {
			    	cloud.removeInstanceFromScaleUpSchedule(instanceId, loadBalancerId);
			    	done = true;
				};
	    	}
		} catch (ParseException e) {
			log.error("Could not parse response.",e);
		}
    	
    	log.debug("Timer: Instance "+instanceId+" now added to LoadBalancer "+loadBalancerId);
    	log.info(response);
    }
}
