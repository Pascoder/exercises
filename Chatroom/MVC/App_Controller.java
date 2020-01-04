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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;

/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * @author Brad Richards
 */
public class App_Controller extends Controller<App_Model, App_View> {
   ServiceLocator serviceLocator;
   

    public App_Controller(App_Model model, App_View view) throws IOException {
        super(model, view);
       
   
        serviceLocator = ServiceLocator.getServiceLocator();
        
     // register ourselves to listen for button clicks
        view.password.setOnAction(this::changePassword);
        view.btnMulti.setOnAction(this::OptionStart);
        view.delete.setOnAction(this::deleteUser);
        view.addUser.setOnAction(this::addUser); 
        view.createChatroom.setOnAction(this::createChatroom); //Menu Create Chatroom
        view.sendbutton.setOnAction(this::senden);
        view.leavechatroom.setOnAction(this::leavechatroom);
        view.txt1.setDisable(true);
		view.txt2.setDisable(true);
		view.lblMulti.setDisable(true);
		view.btnMulti.setDisable(true);
		
        serviceLocator.getConfiguration().getNachrichtProperty().
        addListener((observable, old, neu) -> updateGUI(neu));
 
        //Aktuell eingeloggter User anzeigen
		view.lblUser.setText(serviceLocator.getConfiguration().getActualUser());
	
		
		
        // register ourselves to handle window-closing event
        view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
    
                try {
                	//LeaveChatrooms
                	for(int b = 0; b< model.getChatraumArray().size(); b++) {
                	String senden = "LeaveChatroom|"+serviceLocator.getConfiguration().getSalt()+"|"+model.getChatraumArray().get(b).getName()+"|"+ model.getActualUser();
                	
            		
            		model.sendMessagetoServer(senden);
            		serviceLocator.getLogger().info("LeaveChatroom: "+model.getChatraumArray().get(b).getName());
                	}
                	
                	//Logout
                	model.sendMessagetoServer("Logout");
					
					serviceLocator.getLogger().info("Logged out");
					
                	
                	serviceLocator.getConfiguration().closeThread();
                	serviceLocator.getConfiguration().getSocket().close();
					serviceLocator.getLogger().info("Socket is closed");//Socket schliessen
				} catch (IOException e) {
					
					e.printStackTrace();
				}
                
                Platform.exit();
            }
        });    
   
        
        
        serviceLocator.getLogger().info("Application controller initialized");
        
        //Chatrooms laden
        model.loadChatrooms();
        model.receiveChatrooms();
        addChatboxes();
		
    }
    
    
    
    
    private void addChatboxes() {
    	for(int i= 0; i<model.getMsg().size(); i++) {
			
			//Chatroom name
			String g = model.getMsg().get(i);
			//Add Available Chatrooms to the view
			Chatraum chatraum = new Chatraum(g);
			model.getChatraumArray().add(chatraum);
			String f = chatraum.getBtn().getText();

		
			view.addChatbox(f, chatraum);
			
			final Button but = view.btnArray.get(i);
			//Button Styling from http://fxexperience.com/2011/12/styling-fx-buttons-with-css/
			but.setId("record-sales");
			but.setOnMouseEntered(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					 	but.setScaleX(1.2);
				        but.setScaleY(1.2);
				}
	
			});
			but.setOnMouseExited(new EventHandler<MouseEvent>() {
				    public void handle(MouseEvent e) {
				        but.setScaleX(1);
				        but.setScaleY(1);
				      
				    }
				});
			but.setOnAction(c -> {
				String senden = "JoinChatroom|"+serviceLocator.getConfiguration().
						getSalt()+"|"+chatraum.getName()+"|"+serviceLocator.getConfiguration().getActualUser();
				model.setAcutalchatroom(chatraum.getName());
			
				chatraum.getLabel().setText("");
				chatraum.setMsgCounter();
				try {
				model.sendMessagetoServer(senden);
				} catch (Exception e) {
					serviceLocator.getLogger().info("Konnte nicht ge Joint werden");
				}
		
			    updateChatArea(chatraum);
			});	
		}
	}


	//#DIESE METHODE IST UM DEN CHAT VERLAUF IN DIE CHATRAUME AUFZUTEILEN UND DIE GUI ZU AKTUALISEREN
    //sorted message --> addListener --> updateGUI
   private Object updateGUI(String neu) {
	   serviceLocator.getLogger().info("GUI wurde aktualisiert nachricht empfangen");
	   String[] msg =  neu.split("\\|");//Aufteilen von chatroom|nachricht
		for(int i = 0; i<model.getChatraumArray().size();i++) {
			if(model.getChatraumArray().get(i).getName().equals(msg[0])) {
				
				//!!Hier werden nachrichten am passenden Chatraum hinzugefuegt
				model.getChatraumArray().get(i).addChatMessage(msg[1]); 
				
				int counter = i;
					//Wenn ich im Chat bin dann muss ich counter nicht hochzaehlen da ich ja die nachricht dann schon gelesen habe
					if(model.getAcutalchatroom()!= model.getChatraumArray().get(i).getName()) {
						Platform.runLater(()->{model.getChatraumArray().get(counter).
							getLabel().setText(model.getChatraumArray().get(counter).getMsgCounter()+"");});
					}
				
						if(model.getAcutalchatroom().equals(msg[0])) {
							view.textArea.appendText(msg[1]+"\n"); //wenn die Nachricht fuer den Aktuellen Chatroom ist TextArea updaten
							
				}
			}
		}
		
		
		return null;
	}
   
   
   	
	
	//#DIESE METHODE IST FUER DEN 1. KLICK AUF EINEN CHAT BUTTON
	private Object updateChatArea(Chatraum chatraum) {
	view.textArea.clear();
	view.lblUsersOnline.setText("");
	serviceLocator.getConfiguration().setUsersOnline("");
	model.getUsersOnline(chatraum.getName());
	view.lblUsersOnline.setText("Users online: " + serviceLocator.getConfiguration().getUsersOnline());
	view.lblActualChatroom.setText("Actual Chatroom: " + model.getAcutalchatroom() + "                 ");
	
	
		
		//Am Anfang noch leer muss von File lesen wenn wir alte chats laden wollen
		for(int i =0; i<chatraum.messageList.size();i++) {
			view.textArea.appendText(chatraum.messageList.get(i)+"\n");
		}
		return null;
	}



	//Sprache aendern
	public void changePassword(Event password) {
		model.setMenuOption(3);
		view.btnMulti.setDisable(false);
		view.lblMulti.setDisable(false);
		view.txt1.setDisable(false);
		
		//Aktuelle sprache wird mit Local verglichen und so entschieden welche sprache die korrekte ist
		if(serviceLocator.getTranslator().getCurrentLocale().toString().equals("de")) {
		view.lblMulti.setText("Passwort eif�gen: ");
		view.btnMulti.setText("wechseln");
		view.txt1.setText("neues Passwort");
		}else {
		view.lblMulti.setText("Enter password: ");
		view.btnMulti.setText("change");
		view.txt1.setText("new password");
		}
		view.txt2.setDisable(true);
		
		view.menuHelp.hide();
	}
	
	
	
	//User loeschen
	public void deleteUser(Event delete) {
		model.setMenuOption(4);
		view.btnMulti.setDisable(false);
		view.txt1.setDisable(false);
		view.lblMulti.setDisable(false);
		//Aktuelle sprache wird mit Local verglichen und so entschieden welche sprache die korrekte ist
		if(serviceLocator.getTranslator().getCurrentLocale().toString().equals("de")) {
		view.lblMulti.setText("Benutzer l�schen: ");
		view.btnMulti.setText("l�schen");
		view.txt1.setText("(Benutzername)");
		}else {
		view.lblMulti.setText("Delete User: ");
		view.btnMulti.setText("delete");
		view.txt1.setText("(name of User)");
		}
		
		view.txt2.setDisable(true);
		
		view.menuHelp.hide();

		
	}
	
	
	
	public void createChatroom(Event e) {
		
		model.setMenuOption(1);
		view.btnMulti.setDisable(false);
		view.lblMulti.setDisable(false);
		view.txt1.setDisable(false);
		//Aktuelle sprache wird mit Local verglichen und so entschieden welche sprache die korrekte ist
		if(serviceLocator.getTranslator().getCurrentLocale().toString().equals("de")) {
		view.btnMulti.setText("Erstellen");
		view.lblMulti.setText("Neuen Chatroom erstellen");
		view.txt1.setText("(Chatraum)");
		}else {
		view.btnMulti.setText("Create");
		view.lblMulti.setText("create new chatroom");
		view.txt1.setText("(Chatroom)");	
		}
		
		
		view.txt2.setDisable(true);
		
		view.chatroom.hide();
	}
	
	
	
	public void addUser(Event e) {
		
		model.setMenuOption(2);
		//Aktuelle sprache wird mit Local verglichen und so entschieden welche sprache die korrekte ist
		if(serviceLocator.getTranslator().getCurrentLocale().toString().equals("de")) {
		view.btnMulti.setText("Hinzufuegen");
		view.lblMulti.setText("User hinzufuegen");
		view.txt1.setText("(Benutzername)");
		view.txt2.setText("(Chatraum)");
		}else {
		view.btnMulti.setText("Add");
		view.lblMulti.setText("Add User");
		view.txt1.setText("(Username)");
		view.txt2.setText("(Chatroom)");	
		}
		view.txt2.setDisable(false);
		view.btnMulti.setDisable(false);
		view.txt1.setDisable(false);
		view.lblMulti.setDisable(false);
		
		view.chatroom.hide();
	}
	
	
	
	public void leavechatroom(Event e) {
		model.setMenuOption(5);
		//Aktuelle sprache wird mit Local verglichen und so entschieden welche sprache die korrekte ist
		if(serviceLocator.getTranslator().getCurrentLocale().toString().equals("de")) {
		view.btnMulti.setText("Verlassen");
		view.lblMulti.setText("Chatroom verlassen");
		view.txt1.setText("(Benutzername)");
		view.txt2.setText("(Chatraum)");
		}else {
		view.btnMulti.setText("Leave");
		view.lblMulti.setText("Leave chatroom");
		view.txt1.setText("(Username)");
		view.txt2.setText("(Chatroom)");
		}
		view.lblMulti.setDisable(false);
		view.txt1.setDisable(false);
		view.btnMulti.setDisable(false);
		view.txt2.setDisable(false);
		
		view.chatroom.hide();
	}
	
	
	
	
	//btnMulti gedrueckt, je nach case -> switch Option
	public void OptionStart(Event option) {
		
		try {
		String senden = null;
		String text1 = view.txt1.getText();
		String text2 = view.txt2.getText();
		//0=kein Menu ausgewaehlt, 1= Chatroom erstellen, 2= Chatroom beitreten
		switch(model.getMenuOption()){
		case 1:
			senden = "CreateChatroom|"+serviceLocator.getConfiguration().getSalt()+"|"+text1 +"|"+"true";
			break;
		case 2:
			senden = "JoinChatroom|"+serviceLocator.getConfiguration().getSalt()+"|"+text2+"|"+ text1;
			model.setAcutalchatroom(text2);
			break;
		case 3:
			senden = "ChangePassword|"+serviceLocator.getConfiguration().getSalt()+"|"+text1;
			break;
		case 4:	
			senden = "DeleteLogin|"+serviceLocator.getConfiguration().getSalt()+"|"+text1;
			break;
		case 5:
			senden = "LeaveChatroom|"+serviceLocator.getConfiguration().getSalt()+"|"+text2+"|"+ text1;
			model.setAcutalchatroom(null);
			break;
		}
		model.sendMessagetoServer(senden);
        
		if(serviceLocator.getConfiguration().getOthers()==true) {
		serviceLocator.getLogger().info("Erfolgreich");
		}else {
		serviceLocator.getLogger().info("OptionStart: "+model.getMenuOption()+" ist gescheitert");
		}
		} catch(Exception exception) {
			this.serviceLocator.getLogger().info(exception.getMessage());
			}
		}
		
	
    
	
	
	
	
	private void senden(Event ev) {
		
		if(model.getAcutalchatroom()==null) {
		view.lblInfo.setText("Zuerst einem Chatroom beitreten");
		}else {
		String message = view.txtChatMessage.getText();
		
		try {
		String send = "SendMessage|"+this.serviceLocator.getConfiguration().getSalt()+"|" + model.getAcutalchatroom()+"|"+message;
		model.sendMessagetoServer(send);
		serviceLocator.getLogger().info("gesendet");
		view.txtChatMessage.clear();
		
		
		}catch(Exception ex) {
			ex.printStackTrace();
			serviceLocator.getLogger().info("problem beim senden");
		}
		}
	}
	
	
	}
    

    

