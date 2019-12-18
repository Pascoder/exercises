package Splash;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;


import Splash.ServiceLocator;
import javafx.beans.property.BooleanProperty;

/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * This class provides basic functionality for loading and saving program
 * options. Default options may be delivered with the application; local options
 * (changed by the user) are saved to a file. Program constants can be defined
 * by defining options that the user has no way to change.
 * 
 * @author Brad Richards
 */
public class Configuration {
    ServiceLocator sl = ServiceLocator.getServiceLocator();  // for easy reference
    Logger logger = sl.getLogger();                          // for easy reference

    private Properties defaultOptions;
    private Properties localOptions;
    private Socket socket = null;
    private BufferedWriter writer;
    private BufferedReader reader;
    private String token;
    private boolean correctLogin;
    private ArrayList<String> chatroomArray;
    private boolean accountcreated;
    Thread serverCommunicationThread;
    JavaFX_App_Template template;
 
    

    public Configuration() {
        // Load default properties from wherever the code is
        defaultOptions = new Properties();
        String defaultFilename = sl.getAPP_NAME() + "_defaults.cfg";
        InputStream inStream = sl.getAPP_CLASS().getResourceAsStream(defaultFilename);
        try {
            defaultOptions.load(inStream);
            logger.config("Default configuration file found");
        } catch (Exception e) {
            logger.warning("No default configuration file found: " + defaultFilename);
        } finally {
            try {
                inStream.close();
            } catch (Exception ignore) {
            }
        }

        // Define locally-saved properties; link to the default values
        localOptions = new Properties(defaultOptions);

        // Load the local configuration file, if it exists.
        try {
            inStream = new FileInputStream(sl.getAPP_NAME() + ".cfg");
            localOptions.load(inStream);
        } catch (FileNotFoundException e) { // from opening the properties file
            logger.config("No local configuration file found");
        } catch (IOException e) { // from loading the properties
            logger.warning("Error reading local options file: " + e.toString());
        } finally {
            try {
                inStream.close();
            } catch (Exception ignore) {
            }
        }
        
        for (Enumeration<Object> i = localOptions.keys(); i.hasMoreElements();) {
            String key = (String) i.nextElement();
            logger.config("Option: " + key + " = " + localOptions.getProperty(key));
        }
    }
    
    public void save() {
        FileOutputStream propFile = null;
        try {
            propFile = new FileOutputStream(sl.getAPP_NAME() + ".cfg");
            localOptions.store(propFile, null);
            logger.config("Local configuration file saved");
        } catch (Exception e) {
            logger.warning("Unable to save local options: " + e.toString());
        } finally {
            if (propFile != null) {
                try {
                    propFile.close();
                } catch (Exception e) {
                }
            }
        }
    }
    
    public String getOption(String name) {
        return localOptions.getProperty(name);
    }
    
    public void setLocalOption(String name, String value) {
        localOptions.setProperty(name, value);
    }
    
    
    
    
    //Hier wird Socket erstellt
    public void connectToServer() throws IOException {
		try { this.socket = new Socket("147.86.8.31", 50001);		
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();	
			}	
		}
    
    
    
      public void createBufferedWriter() {
    	try{
    		this.writer = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream())); 
    	}catch(Exception ex) {
    		ex.getMessage();
    	}	
    }
      
      private int messagecounter = 0;
    
    public void createBufferedReader() {
        try {
            BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            serverCommunicationThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            String serverMessage = socketIn.readLine();
                            logger.info("Received: " + serverMessage);
                            sortMessaged(serverMessage);
                        } catch (IOException e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                }
                
               
                
                private void sortMessaged(String serverMessages) {
                	messagecounter++;
                  String [] messages = serverMessages.split("\\|"); //Jede nachricht wird nach aufteilung in Message typ geloescht
                  
                    
                    //Test
                    for(int i = 0; i < messages.length;i++) {
                    	System.out.println(messages[i]);
                    }
                    
                    
                    //nur Login hat 3 Zeichen hat Result|true|4FA4563A5C2FFD1E703B49190DC348BD also ist create login ausgeschlossen
                    if (messages.length == 3 && messages[0].equals("Result") && messages[1].equals("true") && correctLogin ==false) {
                        correctLogin = true;
                        token = messages[2];
                    }
                    //Create Account Message --> account created wird im Login Controller auf true gesetzt falls schon in die app gewechselt wurde somit k�nnen sp�tere nachrichten nicht mehr hier gespeichert werden
                    System.out.println(accountcreated);
                    if(messages.length == 2 && messages[0].equals("Result") && messages[1].equals("true")&&accountcreated==false) {
                    	accountcreated = true;
                    
                    }
                    //Nachrichten koennen nur 1 mal hier rein weil boolean done nur beim 1. mal false ist also nur fuer Chatrooms laden nutzbar
                    if (messages.length > 3 && messages[0].equals("Result") && messages[1].equals("true")) {
                        String[] chatrooms = serverMessages.split("\\|");

                        chatroomArray = new ArrayList<>();
                        
                        for (int i = 2; i < chatrooms.length; i++) {
                            chatroomArray.add(chatrooms[i]);
                            
                        }
                       
                    }
                    //Hier alle anderen nachrichten
                    
                    
                    //Hier Messages
                
                }
            });
            serverCommunicationThread.setDaemon(true);
            serverCommunicationThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }  
      
    
    public Socket getSocket() {
    	return this.socket;
    }
    public BufferedWriter getWriter() {
    	return this.writer;
    }
 
    public BufferedReader getReader() {
    	return this.reader;
    }
    
    public boolean getCorrectLogin() {
    	return this.correctLogin;
    }
    
    public String getSalt() {
    	return this.token;
    }
    public ArrayList<String> getChatrooms(){
    	return this.chatroomArray;
    }
    public void setAccountCreated(boolean created) {
    	this.accountcreated=created;
    }
    public boolean accountCreated() {
    	return this.accountcreated;
    }
   
    
    
}
