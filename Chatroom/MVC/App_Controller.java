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
	
			but.setOnAction(c -> {
				String senden = "JoinChatroom|"+serviceLocator.getConfiguration().
						getSalt()+"|"+chatraum.getName()+"|"+serviceLocator.getConfiguration().getActualUser();
				model.setAcutalchatroom(chatraum.getName());
			
				chatraum.getLabel().setText("-");
				chatraum.setMsgCounter();
				try {
					serviceLocator.getConfiguration().getWriter().write(senden);
					serviceLocator.getConfiguration().getWriter().write("\n");
					serviceLocator.getConfiguration().getWriter().flush();
					Integer a = 0;
			        while (a <= 15000000) {
			            
			        	a++;
			        }
				} catch (IOException e) {
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
				
				System.out.println("chat gefunden");
				model.getChatraumArray().get(i).addChatMessage(msg[1]); //!!Hier werden nachrichten am passenden Chatraum hinzugefuegt
				System.out.println("Message hinzugefuegt zu Chatraum: "+msg[0]);
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
		view.lblMulti.setText("Enter password: ");
		view.btnMulti.setText("change");
		view.txt1.setText("new password");
		view.txt2.setDisable(true);
		
		view.menuHelp.hide();
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
		
		view.menuHelp.hide();

		
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
		
		view.chatroom.hide();
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
		
		view.chatroom.hide();
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
		serviceLocator.getConfiguration().getWriter().write(senden);
		serviceLocator.getConfiguration().getWriter().write("\n");
		serviceLocator.getConfiguration().getWriter().flush();
		
		Integer i = 0;
        while (i <= 15000000) { //<--muss mit einer Property ersetzt werden
            i++;
        }
        
		if(serviceLocator.getConfiguration().getOthers()==true) {
		serviceLocator.getLogger().info("Erfolgreich");
		}else {
		serviceLocator.getLogger().info("OptionStart: "+model.getMenuOption()+" ist gescheitert");
		}
		} catch(IOException exception) {
			this.serviceLocator.getLogger().info(exception.getMessage());
			}
		}
		
	
    
	
	
	
	
	private void senden(Event ev) {
		
		if(model.getAcutalchatroom()==null) {
		view.txtChatMessage.setText("Zuerst einem Chatroom beitreten");
		}else {
		String message = view.txtChatMessage.getText();
		
		try {
		String send = "SendMessage|"+this.serviceLocator.getConfiguration().getSalt()+"|" + model.getAcutalchatroom()+"|"+message;
		serviceLocator.getConfiguration().getWriter().write(send);
		serviceLocator.getConfiguration().getWriter().write("\n");
		serviceLocator.getConfiguration().getWriter().flush();
		serviceLocator.getLogger().info("gesendet");
		//view.textArea.appendText(message+"\n");
		view.txtChatMessage.clear();
		
		
		}catch(Exception ex) {
			ex.printStackTrace();
			serviceLocator.getLogger().info("problem beim senden");
		}
		}
	}
	
	
	}
    

    

