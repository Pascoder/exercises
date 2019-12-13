package MVC;

import Splash.ServiceLocator;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import MVC.Model;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * @author Brad Richards
 */
public class App_Controller extends Controller<App_Model, App_View> {
    ServiceLocator servicelocator;
    private String salt;
   
 

    public App_Controller(App_Model model, App_View view, String salt) throws IOException {
        super(model, view);
       
        this.salt = salt;
     
        servicelocator = ServiceLocator.getServiceLocator();
        
     // register ourselves to listen for button clicks
        view.password.setOnAction(this::changePassword);
        view.btnMulti.setOnAction(this::OptionStart);
        view.delete.setOnAction(this::deleteUser);
        view.addUser.setOnAction(this::addUser);
        view.createChatroom.setOnAction(this::createChatroom);
        
        view.txt1.setDisable(true);
		view.txt2.setDisable(true);
		view.lblMulti.setDisable(true);
		view.btnMulti.setDisable(true);

        view.sendbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buttonClick();
            }
        });
        
        
        

        // register ourselves to handle window-closing event
        view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
            
                
                try {
                	servicelocator.getConfiguration().getSocket().close();
					servicelocator.getLogger().info("Socket is closed");//Socket schliessen
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
                Platform.exit();
            }
        });    
        servicelocator.getLogger().info("Application controller initialized");
        
        //Chatrooms laden
        loadChatrooms();
        
//      //Thread starten um Nachrichten zu empfangen
//        
        System.out.println("before thread");
        try (	BufferedReader socketIn = ServiceLocator.getServiceLocator().getConfiguration().getReader();
        		BufferedWriter socketOut = ServiceLocator.getServiceLocator().getConfiguration().getWriter()) {
			// Create thread to read incoming messages
			Runnable r = new Runnable() {
				@Override
				public void run() {
					System.out.println("within thread");
					while (true) {
						System.out.println("thread execution");
						String msg;
						try {
							msg = socketIn.readLine();
							System.out.println("Message Received: " + msg);
						} catch (IOException e) {
							break;
						}
						if (msg == null) break; // In case the server closes the socket
					}
				}
			};
			Thread t = new Thread(r);
			System.out.println("thread start");
			t.start();
			
        }
		
    }
    
   

	public void buttonClick() {     
    }
	
	
	
	
	//Sprache aendern
	public void changePassword(Event password) {
		view.btnMulti.setDisable(false);
		
		if(view.password.getText().equals("change password")) {
			view.lblMulti.setText("Enter password: ");
			view.btnMulti.setText("change");
			
		}else {
			view.lblMulti.setText("Passwort eingeben: ");
			view.btnMulti.setText("wechseln");
		}
		
	}
	
	//User loeschen
	public void deleteUser(Event delete) {
		view.btnMulti.setDisable(false);
		
		if(view.password.getText().equals("change password")) {
			view.lblMulti.setText("Delete User: ");
			view.btnMulti.setText("delete");
			
		}else {
			view.lblMulti.setText("Benutzer loeschen: ");
			view.btnMulti.setText("loeschen");
		
		}
	}
	
public void createChatroom(Event e) {
		
		model.setMenuOption(1);
		view.btnMulti.setText("Erstellen");
		view.btnMulti.setDisable(false);
		
		view.lblMulti.setText("Neuen Chatroom erstellen");
		view.lblMulti.setDisable(false);
		
		view.txt1.setText("(Chatroom)");
		view.txt1.setDisable(false);
		
		
		view.txt2.setDisable(true);
	}
	
	
	public void addUser(Event e) {
		
		model.setMenuOption(2);
		view.btnMulti.setText("Hinzufuegen");
		view.btnMulti.setDisable(false);
		
		view.lblMulti.setText("User hinzufuegen");
		view.lblMulti.setDisable(false);
		
		view.txt1.setText("(Username)");
		view.txt1.setDisable(false);
		
		view.txt2.setText("(Chatroom)");
		view.txt2.setDisable(false);
	}
	
	
	
	public void OptionStart(Event option) {
		
		String senden = null;
		String text1 = view.txt1.getText();
		String text2 = view.txt2.getText();
		
		//0=kein Menu ausgewählt, 1= Chatroom erstellen, 2= Chatroom beitreten
		switch(model.getMenuOption()){
		
		case 1:
			senden = "CreateChatroom|" + salt + "|" + text1 + "|" + "true";
			break;
			
		case 2:
			senden = "JoinChatroom|" + salt +"|"+ text2 +"|" + text1;
			break;
		
		
		
			
		}
		
		
		try {
			
		
		servicelocator.getConfiguration().getWriter().write(senden);
		servicelocator.getConfiguration().getWriter().write("\n");
		servicelocator.getConfiguration().getWriter().flush();
		
		view.txt1.setText(servicelocator.getConfiguration().getReader().readLine());
		
		servicelocator.getLogger().info("Erfolgreich");
		
		} catch(IOException exception) {
			this.servicelocator.getLogger().info(exception.getMessage());}
		
		
		//wenn auf Button change oder wechseln steht dann diese Methode 
		
		if(view.btnMulti.getText().equals("change")||view.btnMulti.getText().equals("wechseln")) {
		String servermessage = null;
		String pw = view.txt1.getText();
		
			try{
				//Senden eines neuen Passwort
				System.out.println("SocketTest: "+servicelocator.getConfiguration().getSocket());
				String sende = "ChangePassword|"+salt+"|"+pw; 
				
				
				servicelocator.getConfiguration().getWriter().write(sende);
				servicelocator.getConfiguration().getWriter().write("\n");
				servicelocator.getConfiguration().getWriter().flush();
				
				
				//Empfangen der Antwort des Servers
				servermessage = servicelocator.getConfiguration().getReader().readLine();
				
				view.txt1.setText(servermessage);
				servicelocator.getLogger().info("Password changed");
				
				}catch(IOException e) {
					this.servicelocator.getLogger().info("Something goes wrong by changing password");
					e.getMessage();
				}
		}
		
		
		//Wenn auf Button delete oder loeschen steht dann diese Methode
		
		if(view.btnMulti.getText().equals("delete")||view.btnMulti.getText().equals("loeschen")) {
			this.servicelocator.getLogger().info("Button delete clicked");
		}
		
		//Methode User hinzufuegen, Wenn auf Button Hinzufuegen geklickt wird
			
		
			
			
		}
		
	
    
	
	private void loadChatrooms() {
		String msg;
		
		
		try{
			//Senden eines neuen Passwort
			System.out.println("SocketTest: "+servicelocator.getConfiguration().getSocket());
			String loadChats = "ListChatrooms|"+salt; 
			
			
			servicelocator.getConfiguration().getWriter().write(loadChats);
			servicelocator.getConfiguration().getWriter().write("\n");
			servicelocator.getConfiguration().getWriter().flush();
			
			
			//Empfangen der Antwort des Servers
			msg = servicelocator.getConfiguration().getReader().readLine();
			
			
			System.out.println(msg);
			
			String [] chatrooms = msg.split("\\|");
			
			
			for (String s : chatrooms) System.out.println(s);
			
			for(int i= 2; i<chatrooms.length; i++) {
				view.addChatbox(chatrooms[i]);
				
			}
			
			servicelocator.getLogger().info("Chatrooms loaded");
			
			}catch(IOException exception) {
				this.servicelocator.getLogger().info("Something goes wrong by loading chatrooms");
				exception.getMessage();
			}
			}
		
		
	}
    

    

