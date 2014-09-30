package hlipala.autoscaling;

import java.io.PrintStream;

import org.apache.log4j.Logger;
import org.simpleframework.http.Path;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.core.Container;

import hlipala.autoscaling.cloud.CloudStackClient;


public class ApiServer implements Container {
	
	private static final Logger log = Logger.getLogger( ApiServer.class );
	private FileHandler storage = null;
	private CloudStackClient cloud = null;
	
	public ApiServer() {
		this.cloud = CloudStackClient.getInstance();
		this.storage = FileHandler.getInstance();
	}
	
	public void handle(Request request, Response response) {
		
		Path path = request.getPath();
		String action = path.getDirectory()+path.getName();
		String result = "";
		
		log.debug("Request: "+action);
		
		// Handle request
		if(action.equals("/listLoadBalancer")) result = listLoadBalancer();
		else if(action.equals("/addAutoScalePolicy")) result = addAutoScalePolicy(request);
		else if(action.equals("/removeAutoScalePolicy")) result = removeAutoScalePolicy(request);
		else result = "Not Supported";
		
		// Handle response
		try {
			PrintStream body = response.getPrintStream();
			long time = System.currentTimeMillis();
			
			response.setValue("Content-Type", "text/javascript");
			response.setValue("Server", "AutoScaleAPI/1.0");
			response.setDate("Date", time);
			response.setDate("Last-Modified", time);
			
			body.println(result);
			body.close();
			
		} catch(Exception e) {
			log.error("Could not connect to cloudstack api",e);
		}
	} 
	
	/*
	 * TESTED
	 */
	private String listLoadBalancer() {
		return cloud.listAllLoadBalancer();
	}
	
	/*
	 * TESTED
	 */
	private String addAutoScalePolicy(Request request) {
		
		String result = "";
		Query query = request.getQuery();
		
		// Check all parameters
		String loadBalancerId = query.get("loadBalancerId"); 
		String templateId = query.get("templateId"); 
		String offeringId = query.get("offeringId"); 
		int minInstances = query.getInteger("minInstances"); 
		int maxInstances = query.getInteger("maxInstances"); 
		int scaleUpValue = query.getInteger("scaleUpValue"); 
		int scaleUpDuration = query.getInteger("scaleUpDuration"); 
		int scaleUpSleep = query.getInteger("scaleUpSleep"); 
		int scaleDownValue = query.getInteger("scaleDownValue"); 
		int scaleDownDuration = query.getInteger("scaleDownDuration");
		
		if(loadBalancerId != null && templateId != null && offeringId != null 
				&& minInstances != 0 && maxInstances != 0 && scaleUpDuration != 0 
				&& scaleUpSleep != 0 && scaleDownDuration != 0 && scaleUpValue != 0 && scaleDownValue != 0) {
			
			// Encode Policy
			String configString = templateId+"|"+offeringId+"|"+minInstances
					+"|"+maxInstances+"|"+scaleUpValue+"|"+scaleUpDuration+"|"
					+scaleUpSleep+"|"+scaleDownValue+"|"+scaleDownDuration;
			
			log.debug("Config parameters: "+configString);
			
			// Add Policy
			storage.addLoadBalancer(loadBalancerId, configString);
			
			log.debug("AutoScaling Policy to Load-Balancer "+loadBalancerId+" added");
			result = listLoadBalancer();
		}
		else {
			result = "Could not add AutoScaling Policy to LoadBalancer "+loadBalancerId+" - Missing Parameter";
			log.error(result);
		}
		
		return result;
	}
	
	/*
	 * TESTED
	 */
	private String removeAutoScalePolicy(Request request) {
		
		String result = "";
		Query query = request.getQuery();
		
		String loadBalancerId = query.get("loadBalancerId"); 
		
		if(loadBalancerId != null) {
			
			// Remove Policy
			storage.removeLoadBalancer(loadBalancerId);
			
			log.debug("AutoScaling Policy to LoadBalancer "+loadBalancerId+" removed");
			result = listLoadBalancer();
		}
		else {
			result = "Could not remove AutoScaling Policy to LoadBalancer "+loadBalancerId+" - Missing Parameter";
			log.error(result);
		}
		
		return result;
	}
}