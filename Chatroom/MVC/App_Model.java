package MVC;

import Splash.ServiceLocator;
import chatroom.server.Client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import MVC.Model;

/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * @author Brad Richards
 */
public class App_Model extends Model {
    ServiceLocator serviceLocator;
 
    
    
    public App_Model() {
        
        
        serviceLocator = ServiceLocator.getServiceLocator();        
        serviceLocator.getLogger().info("Application model initialized");
    }


	public boolean Connect(String ip, String port) throws UnknownHostException, IOException {
		boolean connected = false;
		if(validateIpAddress(ip)== true && validatePortNumber(port) == true) {
			int portInt = Integer.parseInt(port);
			Socket socket = new Socket(ip, portInt);
			Client client = new Client(socket);
			Client.add(client);
			
			connected = true;
		}
		
		return connected;
		
		
		
	}
	//Prüfen der IP
	private static boolean validateIpAddress(String ipAddress) {
		boolean formatOK = false;
		// Check for validity (not complete, but not bad)
		String ipPieces[] = ipAddress.split("\\."); // Must escape (see
													// documentation)
		// Must have 4 parts
		if (ipPieces.length == 4) {
			// Each part must be an integer 0 to 255
			formatOK = true; // set to false on the first error
			int byteValue = -1;
			for (String s : ipPieces) {
				byteValue = Integer.parseInt(s); // may throw
													// NumberFormatException
				if (byteValue < 0 | byteValue > 255) formatOK = false;
			}
		}
		return formatOK;
	}
	//Prüfen der Portnumber
	private static boolean validatePortNumber(String portText) {
		boolean formatOK = false;
		try {
			int portNumber = Integer.parseInt(portText);
			if (portNumber >= 1024 & portNumber <= 65535) {
				formatOK = true;
			}
		} catch (NumberFormatException e) {
		}
		return formatOK;
	}
    
  
    
    
}
