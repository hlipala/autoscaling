package hlipala.autoscaling;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.daemon.*;
import org.apache.log4j.Logger;
import org.simpleframework.http.core.Container;
import org.simpleframework.http.core.ContainerServer;
import org.simpleframework.transport.Server;
import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;


public class Main implements Daemon {
    
	private static final Logger log = Logger.getLogger( Main.class );
	
    // ApiServer
    private Container container = null;
    private Server server = null;
    private Connection connection = null;
    private SocketAddress address = null;
    
    // Timer
    private static Timer timer = null;
    
    // Settings
    private static int refreshTimeInSec = 20;
    

    @Override
    public void init(DaemonContext dc) throws DaemonInitException, Exception {
        log.info("initializing ...");
        
        // Init ApiServer
        container = new ApiServer();
        server = new ContainerServer(container);
        connection = new SocketConnection(server);
        address = new InetSocketAddress(8082);
    }

    @Override
    public void start() throws Exception {
        log.info("starting ...");
        main(null);
        
        // Start ApiServer
        connection.connect(address);
    }

    @Override
    public void stop() throws Exception {
        log.info("stopping ...");
        
        // Stop ApiServer
        if (connection != null) {
        	connection.close();
        }
        
        // Stop timer
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    public void destroy() {
        log.debug("done.");
    }
    
    public static void main(String[] args) {
        timer = new Timer();
        
        log.debug("Main");
        // Check for AutoScale updates every x sec
        timer.scheduleAtFixedRate(new TimerTask() {
        	@Override
        	public void run() {
                
        		// Start Scaling
                AutoScaler.getInstance().scale();
        		
            }
        }, 0, 1000 * refreshTimeInSec);
    }
    
 }