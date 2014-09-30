package hlipala.autoscaling;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.log4j.Logger;

public class FileHandler {

	private static final Logger log = Logger.getLogger( FileHandler.class );
	
	private static FileHandler singleton = null;
	private Properties prop = null;
	private InputStream input = null;
	private String filename = "loadBalancer.properties";
	
	public static FileHandler getInstance() {
		if (singleton == null) {
			singleton = new FileHandler();
		}
		return singleton;
	}
	
	/*
	 * TESTED
	 */
	public Map<String,String> listAutoScalePolicies() {
		
		prop = new Properties();
		input = null;
		
		Map<String,String> loadBalancer = new HashMap<String,String>();
	 
		try {
			// Load properties file
			FileInputStream in = new FileInputStream(filename);
	        prop.load(in);
	        in.close();
		 
			for(Entry<Object, Object> line : prop.entrySet()) {
				String key = line.getKey().toString();
				String value = line.getValue().toString();
				loadBalancer.put(key, value);
	        }
			
		} catch (IOException ex) {
			log.error("Error while loading properties file",ex);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					log.error("Error while closing properties file",e);
				}
			}
		}
		
		return loadBalancer;
	}
	
	/*
	 * TESTED
	 */
	public void addLoadBalancer(String loadBalancerId, String loadBalancerConfig) {

		prop = new Properties();
	 
		try {
			// Load properties file
			FileInputStream in = new FileInputStream(filename);
	        prop.load(in);
	        in.close();
			
			// Write to properties file
			FileOutputStream out = new FileOutputStream(filename);
			prop.setProperty(loadBalancerId, loadBalancerConfig);
	        prop.store(out, null);
	        out.close();

	        log.info("Policy for Load-Balancer "+loadBalancerId+" added to Properties.");
			
		} catch (IOException io) {
			log.error("Error while reading/writing properties file",io);
		}
	}
	
	/*
	 * TESTED
	 */
	public void removeLoadBalancer(String loadBalancerId) {
		
		prop = new Properties();
	 
		try {
			// Load properties file
			FileInputStream in = new FileInputStream(filename);
	        prop.load(in);
	        in.close();
			
			// Write to properties file
			FileOutputStream out = new FileOutputStream(filename);
			prop.remove(loadBalancerId);
	        prop.store(out, null);
	        out.close();
			
			log.info("Policy of Load-Balancer "+loadBalancerId+" removed in Properties.");
			
		} catch (IOException io) {
			log.error("Error while loading properties file",io);
		}
	}
	
}