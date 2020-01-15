 package Splash;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Logger;
import Splash.ServiceLocator;
import javafx.application.Platform;
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
    private boolean 			onSort;
    private String 				actualUser = null;
    private String 				usersOnline = "";
    private boolean 			booleanuseronline = false;
    private boolean 			threadrunning = true;
    private int 				chatmessagecounter = 0;
    
    

    //Message
    
    private ArrayList<String> 	recivedmessages = new ArrayList<String>();
    private SimpleStringProperty nachricht = new SimpleStringProperty(); //update gui on receipt of a new message
    

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
        
       // Load older ChatMessages
        
       
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
    

     
    //Socket is created here
    public void connectToServer() throws IOException {
		try { this.socket = new Socket("147.86.8.31", 50001);		
			} catch (UnknownHostException e) {
				Platform.runLater(new Runnable() {
					public void run() {
						System.out.println("Alert3");
						Alert alert = new Alert(Alert.AlertType.WARNING, "Unknow host, please try again");
						alert.showAndWait();
						Platform.exit();
						
					
					}
            		
            });
			} catch (IOException e) {
				Platform.runLater(new Runnable() {
					public void run() {
						System.out.println("Alert4");
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
      

   //BufferedReader includes also the possibity to show an alert, if we lost the server connection 
    public void createBufferedReader() {
        try {
            BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            serverCommunicationThread = new Thread(new Runnable() {
            @Override
            public void run() {
               while (threadrunning ) {
                  try {
                      	String serverMessage = socketIn.readLine();
                      	logger.info("Received: " + serverMessage);
                      	if(serverMessage != null)sortMessaged(serverMessage);
                      	else {
                      	
                      		Platform.runLater(new Runnable() {
								public void run() {
									//if the internet connection was interrupted for a long time 30-60sek
									Alert alert = new Alert(Alert.AlertType.WARNING, "internet connection lost, please reconnect");
									 alert.showAndWait();
									 Platform.exit();
									 System.exit(1);
									 alert.setOnCloseRequest(c->{Platform.exit();System.exit(1);});
								
								}
                        		
                        });
                      	}
                        } catch (IOException e) {
                        	if(threadrunning) {
                        		
                        		
                        		Platform.runLater(new Runnable() {
								public void run() {
									//if the internet connection was interrupted for a long time 30-60sek
								Alert alert = new Alert(Alert.AlertType.WARNING, "internet connection lost, please reconnect");
								 alert.showAndWait();  
								 Platform.exit();
								 System.exit(1);
								 alert.setOnCloseRequest(c->{Platform.exit();System.exit(1);});
							
								
								}
                        		
                        });
                        		
                        		logger.info("Lost Connection to Server");
                        		
                        	break;
                        	
                        	}else {
                        	logger.info("Thread closed");
                        	}
                        }
                    }
                }
                
               
             
          

private void sortMessaged(String serverMessages) {
                
                
       
                	String [] messages = serverMessages.split("\\|"); //Jede nachricht wird nach aufteilung in Message typ geloescht
                  
                	
                    
                    //Test
                    for(int i = 0; i < messages.length;i++) {
                    	
                    }
                    
                    
                    //only login has 3 characters
                    if (messages.length == 3 && messages[0].equals("Result") && messages[1].equals("true") && correctLogin ==false) {
                        correctLogin = true;
                        token = messages[2];
                    }
                    //Create Account Message --> account created is set to true in the login controller if you have already switched to the app so that later messages cannot be saved here
                  
                    if(messages.length == 2 && messages[0].equals("Result") && messages[1].equals("true")&& accountcreated==false) {
                    	accountcreated = true;
                    
                    }else {
                    	onSort = false;//<-- every time a 2 digit message comes in, others is first set to false to get a correct result for each request
                     //If account has already been created or the program is no longer in the login menu, later messages will be sent here
                    if(messages.length == 2 && messages[0].equals("Result") && messages[1].equals("true")) {
                    	onSort = true;
                        
                        }
                    	
                        }
                    
                    //Messages can only be posted 1 time because boolean done only at the 1. time false is only usable for loading chatrooms
                    if (messages.length > 2 && messages[0].equals("Result") && messages[1].equals("true")) {
                        String[] chatrooms = serverMessages.split("\\|");

                        chatroomArray = new ArrayList<>();
                        
                        for (int i = 2; i < chatrooms.length; i++) {
                        	//If the current user is in it, it is the list of current online users
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
                    //All messages from the chat are stored here
                    if(messages[0].equals("MessageText")) {
                    	
                    		String sentfrom = messages[1];
                    		String chat = messages[2];
                    		recivedmessages.add(sentfrom+"|"+chat+"|"+messages[3]);
                    	
                    		String message = sentfrom + ": " + messages[3];
                    		chatmessagecounter++;
                       
                    	setNachrichtProperty(chat+"|"+ message+"|"+chatmessagecounter); //is triggered in Controller setonAction
                    	
                        
                    }
                	
                }
            });
            serverCommunicationThread.setDaemon(true);
            serverCommunicationThread.start();

        } catch (IOException  e) {
            e.printStackTrace();
        }
    }  
    //old chats were loaded here
    public void loadoldChatrooms() {
    	 try {
         	BufferedReader reader = new BufferedReader(new FileReader("ChatRooms.txt"));
         	
         	String zeile = reader.readLine();
         	while(zeile!=null) {
         		
         		if(zeile != null&&zeile.length()>0)
         		setNachrichtProperty(zeile);
         		
         		zeile = reader.readLine();

         		
         	}
         	reader.close();
         }catch(Exception e ) {
         	e.getMessage();
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
