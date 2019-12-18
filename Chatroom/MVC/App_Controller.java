package MVC;

import Splash.ServiceLocator;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

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
    
   private  String acutalchatroom = null;
 

    public App_Controller(App_Model model, App_View view) throws IOException {
        super(model, view);
       
       
     
        servicelocator = ServiceLocator.getServiceLocator();
        
     // register ourselves to listen for button clicks
        view.password.setOnAction(this::changePassword);
        view.btnMulti.setOnAction(this::OptionStart);
        view.delete.setOnAction(this::deleteUser);
        view.addUser.setOnAction(this::addUser); 
        view.createChatroom.setOnAction(this::createChatroom); //Menu Create Chatroom
        view.sendbutton.setOnAction(this::senden);
        view.leavechatroom.setOnAction(this::leavechatroom);
        view.sendbutton.setOnAction(this::openChatBox);
        
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
        empfangenChatrooms();
		
    }
    
    
    
    
   private void openChatBox(Event e) {
	   
	   
   }

   
   
   
	private void empfangenChatrooms() {
		ArrayList <String> msg;
		msg = servicelocator.getConfiguration().getChatrooms();
		
		
		
		for(int i= 0; i<msg.size(); i++) {
			view.addChatbox(msg.get(i));
			
		}
		
	}



	public void buttonClick() {     
    }
	
	
	
	
	//Sprache aendern
	public void changePassword(Event password) {
		model.setMenuOption(3);
		view.btnMulti.setDisable(false);
		view.lblMulti.setDisable(false);
		view.txt1.setDisable(false);
		view.lblMulti.setText("Enter password: ");
		view.btnMulti.setText("change");
		view.txt1.setText("new password");
		view.txt2.setDisable(true);
	}
	
	//User loeschen
	public void deleteUser(Event delete) {
		model.setMenuOption(4);
		view.btnMulti.setDisable(false);
		view.txt1.setDisable(false);
		view.lblMulti.setDisable(false);
		view.lblMulti.setText("Delete User: ");
		view.btnMulti.setText("delete");
		view.txt1.setText("(name of User)");
		view.txt2.setDisable(true);
		
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
	public void leavechatroom(Event e) {
		model.setMenuOption(5);
		view.btnMulti.setText("Verlassen");
		view.btnMulti.setDisable(false);
		
		view.lblMulti.setText("Chatroom verlassen");
		view.lblMulti.setDisable(false);
		
		view.txt1.setText("(Username)");
		view.txt1.setDisable(false);
		
		view.txt2.setText("(Chatroom)");
		view.txt2.setDisable(false);
	}
	
	
	
	public void OptionStart(Event option) {
		
		try {
		String senden = null;
		String text1 = view.txt1.getText();
		String text2 = view.txt2.getText();
		//0=kein Menu ausgewaehlt, 1= Chatroom erstellen, 2= Chatroom beitreten
		switch(model.getMenuOption()){
		case 1:
			senden = "CreateChatroom|"+servicelocator.getConfiguration().getSalt()+"|"+text1 +"|"+"true";
			break;
		case 2:
			senden = "JoinChatroom|"+servicelocator.getConfiguration().getSalt()+"|"+text2+"|"+ text1;
			this.acutalchatroom = text2;
			break;
		case 3:
			senden = "ChangePassword|"+servicelocator.getConfiguration().getSalt()+"|"+text1;
			break;
		case 4:	
			senden = "DeleteLogin|"+servicelocator.getConfiguration().getSalt()+"|"+text1;
			break;
		case 5:
			senden = "LeaveChatroom|"+servicelocator.getConfiguration().getSalt()+"|"+text2+"|"+ text1;
			this.acutalchatroom =null;
			break;
		}
		servicelocator.getConfiguration().getWriter().write(senden);
		servicelocator.getConfiguration().getWriter().write("\n");
		servicelocator.getConfiguration().getWriter().flush();
		
		Integer i = 0;
        while (i <= 15000000) { //<--muss mit einer Property ersetzt werden
            i++;
        }
        
		view.txt1.setText(servicelocator.getConfiguration().getReader().readLine());
		servicelocator.getLogger().info("Erfolgreich");
		} catch(IOException exception) {
			this.servicelocator.getLogger().info(exception.getMessage());
			}
		}
		
	
    
	
	private void loadChatrooms()  {
		
		try{
			
			
			String loadChats = "ListChatrooms|"+servicelocator.getConfiguration().getSalt(); 
			servicelocator.getConfiguration().getWriter().write(loadChats);
			servicelocator.getConfiguration().getWriter().write("\n");
			servicelocator.getConfiguration().getWriter().flush();
			//Empfangen der Antwort des Servers
			
			
			servicelocator.getLogger().info("Chatrooms loaded");
			}catch(IOException exception) {
				this.servicelocator.getLogger().info("Something goes wrong by loading chatrooms");
				exception.getMessage();
			}
		
		Integer i = 0;
        while (i <= 15000000) { //<--muss mit einer Property ersetzt werden
            i++;
        }
			
			
		
			}
	
	
	private void senden(Event ev) {
		
		if(acutalchatroom==null) {
		servicelocator.getLogger().info("zuerst Chatroom waehlen");
		}else {
		String message = view.txtChatMessage.getText();
		
		try {
		String send = "SendMessage|"+this.servicelocator.getConfiguration().getSalt()+"|"+this.acutalchatroom+"|"+message;
		servicelocator.getConfiguration().getWriter().write(send);
		servicelocator.getConfiguration().getWriter().write("\n");
		servicelocator.getConfiguration().getWriter().flush();
		servicelocator.getLogger().info("gesendet");
		
		}catch(Exception ex) {
			ex.printStackTrace();
			servicelocator.getLogger().info("problem beim senden");
		}
		}
	}
	}
    

    

