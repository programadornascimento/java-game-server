package org.menacheri.jetserver;

import org.apache.log4j.PropertyConfigurator;
import org.menacheri.jetserver.server.ServerManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;


/**
 * This class starts the server running at the designated port on the localhost.
 * It defaults to port 8090 but can use any port specified at command prompt.
 * 
 * @author Abraham Menacherry
 * 
 */
public class SpringNettyServer
{
	private static final Logger LOG = LoggerFactory.getLogger(SpringNettyServer.class);
	public static void main(String[] args)
	{
		PropertyConfigurator.configure(System
				.getProperty("log4j.configuration"));
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(JetServerSpringConfig.class);
		// For the destroy method to work.
		context.registerShutdownHook();
		
		// Start tcp and flash servers
		ServerManager manager = (ServerManager)context.getBean("serverManager");
		try
		{
			manager.startServers(8090,843,8081);
		}
		catch (Exception e)
		{
			LOG.error("Could not start servers cleanly: {}",e);
		}
	}
	
	
}

