package MVC;

import Splash.ServiceLocator;


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
    
    
    //0=kein Menu ausgewählt, 1= Chatroom erstellen, 2= Chatroom beitreten, 3= User hinzufügen
    private int menuOption = 0;
 
    
    
    public int getMenuOption() {
		return menuOption;
	}



	public void setMenuOption(int menuOption) {
		this.menuOption = menuOption;
	}



	public App_Model() {
        
        
        serviceLocator = ServiceLocator.getServiceLocator();        
        serviceLocator.getLogger().info("Application model initialized");
    }



	public String getUsersOnline(String chatraum) {
		String senden = "ListChatroomUsers|"+serviceLocator.getConfiguration().getSalt()+"|"+chatraum;
		
		
		try {
			serviceLocator.getConfiguration().getWriter().write(senden);
			serviceLocator.getConfiguration().getWriter().write("\n");
			serviceLocator.getConfiguration().getWriter().flush();
			Integer a = 0;
	        while (a <= 200000000) { //<--muss mit einer Property ersetzt werden
	            
	        	a++;
	        }
		} catch (IOException e) {
			serviceLocator.getLogger().info("ListChatroomUsers nicht möglich");
		}
		
		return null;
	}


	

  
  
    
    
}
