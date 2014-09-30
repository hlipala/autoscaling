package test;

import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import org.simpleframework.http.Path;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.core.Container;
import org.simpleframework.http.core.ContainerServer;
import org.simpleframework.transport.Server;
import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;

public class ApiServerTest implements Container {

	public void handle(Request request, Response response) {
		
		//System.out.println(request);
		String result = "";
		
		Path path = request.getPath(); 
		String action = path.getDirectory()+path.getName();
		
		if(action.equals("/listLoadBalancer")) {
			result = "list";
		}
		else if(action.equals("/addAutoScalePolicy")) {
			
			Query query = request.getQuery();
			String id = query.get("id"); 
			String hello = query.get("hello"); 
			
			if(id != null && hello != null) {
				System.out.println("Found Params:");
				System.out.println(id+" : "+hello);
				
				result = "Found Params: id="+id+" hello="+hello;
			}
			else result = "Missing parameters";
		}
		else if(action.equals("/removeAutoScalePolicy")) {
			
			result = "AutoScale Policy removed";
		}
		else {

			result = "Not Supported";
		}
		
		try {
			PrintStream body = response.getPrintStream();
			long time = System.currentTimeMillis();
			
			response.setValue("Content-Type", "text/plain");
			response.setValue("Server", "AutoScaleAPI/1.0");
			response.setDate("Date", time);
			response.setDate("Last-Modified", time);
			
			body.println(result);
			body.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	} 

	public static void main(String[] list) throws Exception {
		Container container = new ApiServerTest();
		Server server = new ContainerServer(container);
		Connection connection = new SocketConnection(server);
		SocketAddress address = new InetSocketAddress(8080);

		connection.connect(address);
	}
}