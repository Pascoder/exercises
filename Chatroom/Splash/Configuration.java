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
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Logger;


import Splash.ServiceLocator;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

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

    private Properties 			defaultOptions;
    private Properties 			localOptions;
    private Socket 				socket = null;
    private BufferedWriter 		writer;
    private BufferedReader 		reader;
    private String 				token;
    private boolean 			correctLogin;
    private ArrayList<String> 	chatroomArray;
    private boolean 			accountcreated;
    private Thread 				serverCommunicationThread;
    private JavaFX_App_Template template;
    private boolean 			onSort;
    private String 				actualUser = null;
    private String 				usersOnline = "";
    private boolean 			booleanuseronline = false;
    private boolean 			threadrunning = true;
    private int 				messagecounter = 0;
    private int 				chatmessagecounter = 0;

    //Message
    
    private ArrayList<String> 	recivedmessages = new ArrayList<String>();
    private SimpleStringProperty nachricht = new SimpleStringProperty(); //wenn neue Nachricht gui updaten
    

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
    

     
    //Hier wird Socket erstellt
    public void connectToServer() throws IOException {
		try { this.socket = new Socket("147.86.8.31", 50001);		
			} catch (UnknownHostException e) {
				Platform.runLater(new Runnable() {
					public void run() {
					
						Alert alert = new Alert(Alert.AlertType.WARNING, "Unknow host, please try again");
						alert.showAndWait();
						Platform.exit();
						
					
					}
            		
            });
			} catch (IOException e) {
				Platform.runLater(new Runnable() {
					public void run() {
					
						Alert alert = new Alert(Alert.AlertType.WARNING, "No internet connection, please reconnect");
						alert.showAndWait();
						logger.info("Program terminated");
						Platform.exit();
						
						
					
					}
            		
            });
				
			}	
		}
    
 
    public void createBufferedWriter() {
    	try{
    		this.writer = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream())); 
    	}catch(Exception ex) {
    		ex.getMessage();
    	}	
    }
      

    
    public void createBufferedReader() {
        try {
            BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            serverCommunicationThread = new Thread(new Runnable() {
            @Override
            public void run() {
               while (threadrunning) {
                  try {
                      	String serverMessage = socketIn.readLine();
                      	logger.info("Received: " + serverMessage);
                      	sortMessaged(serverMessage);
                          
                        } catch (IOException e) {
                        	if(threadrunning) {
                        		
                        		
                        		Platform.runLater(new Runnable() {
								public void run() {
								
								showAlert();
								
								}
                        		
                        });
                        		
                        		logger.info("Lost Connection to Server");
                        		
                        	
                        	
                        	}else {
                        	logger.info("Thread closed");
                        	}
                        }
                    }
                }
                
               
                
          

private void sortMessaged(String serverMessages) {
                	messagecounter++;
                
                	
                	String [] messages = serverMessages.split("\\|"); //Jede nachricht wird nach aufteilung in Message typ geloescht
                  
                   
                    
                    //Test
                    for(int i = 0; i < messages.length;i++) {
                    	System.out.println("Server Nachricht"+messagecounter+" :"+messages[i]);
                    }
                    
                    
                    //nur Login hat 3 Zeichen hat Result|true|4FA4563A5C2FFD1E703B49190DC348BD also ist create login ausgeschlossen
                    if (messages.length == 3 && messages[0].equals("Result") && messages[1].equals("true") && correctLogin ==false) {
                        correctLogin = true;
                        token = messages[2];
                    }
                    //Create Account Message --> account created wird im Login Controller auf true gesetzt falls schon in die app gewechselt wurde somit koennen spaetere nachrichten nicht mehr hier gespeichert werden
                  
                    if(messages.length == 2 && messages[0].equals("Result") && messages[1].equals("true")&& accountcreated==false) {
                    	accountcreated = true;
                    
                    }else {
                    	onSort = false;//<-- jedes mal wenn eine 2 Stellige nachricht rein kommt wird zuerst others auf false gesetzt um ein korrektes ergebnis zu haben fuer jeweilige Anfrage
                     //Wenn Account schon erstellt wurde oder sich das Programm nicht mehr im Login Menu befindet gehen spaetere nachrichten hier ein
                    if(messages.length == 2 && messages[0].equals("Result") && messages[1].equals("true")) {
                    	onSort = true;
                        
                        }
                    	
                        }
                    
                    //Nachrichten koennen nur 1 mal hier rein weil boolean done nur beim 1. mal false ist also nur fuer Chatrooms laden nutzbar
                    if (messages.length > 2 && messages[0].equals("Result") && messages[1].equals("true")) {
                        String[] chatrooms = serverMessages.split("\\|");

                        chatroomArray = new ArrayList<>();
                        
                        for (int i = 2; i < chatrooms.length; i++) {
                        	//Wenn der aktuelle User drin ist, ist es die Liste der aktuell online Users
                        	if(chatrooms[i].equals(actualUser) ) {
                        		booleanuseronline = true;
                        		
                        }
                        }
                        if(booleanuseronline == true) {
                        	for(int b = 2; b<messages.length;b++) {
                        		usersOnline += chatrooms[b] + ", ";
                        	}
                        	booleanuseronline=false;
                        }else {
                        	for(int c = 2; c<chatrooms.length;c++) {
                        		chatroomArray.add(chatrooms[c]);
                        	}
                        	
                        }
                        	
                        	
                        
                       
                    }
                    //Hier werden alle Nachrichten aus dem Chat gespeichert
                    if(messages[0].equals("MessageText")) {
                    	
                    		String sentfrom = messages[1];
                    		String chat = messages[2];
                    		recivedmessages.add(sentfrom+"|"+chat+"|"+messages[3]);
                    		//Message wird mit Username konkateniert, könnte besser gelöst werden mit Message Objekt!
                    		String message = sentfrom + ": " + messages[3];
                    		chatmessagecounter++;
                       
                    	setNachrichtProperty(chat+"|"+ message+"|"+chatmessagecounter); //wird in Controller setonAction ausgel�st
                    	
                        
                    }
                
                }
            });
            serverCommunicationThread.setDaemon(true);
            serverCommunicationThread.start();

        } catch (IOException  e) {
            e.printStackTrace();
        }
    }  
    
    
    private void showAlert() {
    
    Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Warning!");
    	alert.setHeaderText("Lost Server Connection");
    	alert.setContentText("Exit Programm?");
    	Optional<ButtonType> result = alert.showAndWait();
    	    if (result.get() == ButtonType.OK) {
    	    	template.stop();
    	    } else {
    	    	//Login Fenster oeffnen
    	    	template.stop();
    	    	}
    
    	    }					

    
    
    //Getter & Setter
    
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
    public boolean getOthers() {
    	return this.onSort;
    }
   public void setNachrichtProperty(String newValue) {
    	this.nachricht.set(newValue);
    }
    public SimpleStringProperty getNachrichtProperty() {
    	return this.nachricht;
    }
    
    public ArrayList<String> getRecivedMessages(){
    	return this.recivedmessages;
    }
    public void setAcutalUser(String user) {
    	this.actualUser = user;
    }
    public String getActualUser() {
    	return this.actualUser;
    }

	public String getUsersOnline() {
		return usersOnline;
	}

	public void setUsersOnline(String usersOnline) {
		this.usersOnline = usersOnline;
	}
	public void closeThread() {
		this.threadrunning = false;
	}
	
    public String getOption(String name) {
        return localOptions.getProperty(name);
    }
    
    public void setLocalOption(String name, String value) {
        localOptions.setProperty(name, value);
    }
	
	
}
