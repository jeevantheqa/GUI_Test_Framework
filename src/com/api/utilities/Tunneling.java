/*Description : This Class is menat for taking up all the tunneling activity
Author:Meenak
Project :IoT*/

package com.api.utilities;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.api.base.Base_API;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
public class Tunneling extends Base_API{
	
	public static int allocatedport;
	final static Logger logger = Logger.getLogger(Tunneling.class);

	
	//Config cnObj=new Config();
	public static boolean dbserverTunnel() throws IOException{
		logger.info("$$$$ Before Test Configurations - Started $$$$");
		logger.info("Tunneling The DB server in the remote location");
		
		try {
	        JSch schobj = new JSch(); 
	        logger.info("Initiating SSH Session");
	        Session session = schobj.getSession(prop_api.getProperty("tunnellogin"),prop_api.getProperty("tunnelhost"), 22);
	        session.setPassword(prop_api.getProperty("tunnelpassword"));
	        java.util.Properties config = new java.util.Properties();
	        config.put("StrictHostKeyChecking", "no");
	        config.put("Compression", "yes");
	        config.put("ConnectionAttempts","2");
	        session.setConfig(config);
	        logger.info("SSH Session Connecting......");
	        session.connect();  
	        boolean sshconnect = session.isConnected();
	        if (sshconnect == true){
	        logger.info("SSH Session Connected Successfully");

	        }
	        
	        else
	        {
	        	logger.info("SSH Connection Failed");
	        }
	        
	        
	        logger.info("Port Forwarding to local host is intiated");
	        	allocatedport=session.setPortForwardingL("0.0.0.0",Integer.parseInt(prop_api.getProperty("tunneldbsourceport")), 
	        			prop_api.getProperty("tunnelhost"),Integer.parseInt(prop_api.getProperty("tunneldbremoteport")));
	        		
	        		if(allocatedport != 0){
	        
	        		logger.info("Source Port in the Local Machine:\t"+allocatedport);
	        		logger.info("Port Fowarding Done Successfully");
	        		return true;
	        		}
	        		else{
	        			
	        			logger.info("Port Forwarding Failed");
	        			return false;
	        		}
	        		
	        		
	        		

	    } catch (JSchException e) {            
	        logger.error("Tunneling Unsussessful:\t" + e.getMessage());
	        return false;
	    }
	}
	


}
