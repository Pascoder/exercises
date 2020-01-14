package MVC;

import Splash.ServiceLocator;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
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
        view.createChatroom.setOnAction(this::createChatroom); 
        view.deletechatroomhistory.setOnAction(this::deleteChatroomHistory);
        view.sendbutton.setOnAction(this::senden);
        view.leavechatroom.setOnAction(this::leavechatroom);
        //hidden by default
        view.txt1.setDisable(true);
		view.txt2.setDisable(true);
		view.lblMulti.setDisable(true);
		view.btnMulti.setDisable(true);
		//Propertys (actual user, updateGUI)
		view.sendbutton.disableProperty().bind(view.txtChatMessage.textProperty().isEmpty());
        serviceLocator.getConfiguration().getNachrichtProperty().
        addListener((observable, old, neu) -> updateGUI(neu));
		view.lblUser.setText(serviceLocator.getConfiguration().getActualUser());
	
		
		
        // register ourselves to handle window-closing event
        view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
            	model.saveFile();
            	
                try {
                	//Leave all Chatrooms
                	for(int b = 0; b< model.getChatraumArray().size(); b++) {
                	String senden = "LeaveChatroom|"+serviceLocator.getConfiguration().getSalt()+"|"+model.getChatraumArray().get(b).getName()+"|"+ model.getActualUser();
                	
            		
            		model.sendMessagetoServer(senden);
            		serviceLocator.getLogger().info("LeaveChatroom: "+model.getChatraumArray().get(b).getName());
                	}
                	
                	//Logout automatically
                	model.sendMessagetoServer("Logout");
					
					serviceLocator.getLogger().info("Logged out");
					
                	//terminate Thread and Socket
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
        
        //load chatrooms
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


	//#THIS METHOD IS TO SPLIT THE CHAT FLOW INTO THE CHATROOMS AND UPDATE THE GUI
    //process: sorted message --> addListener --> updateGUI
   private Object updateGUI(String neu) {
	   serviceLocator.getLogger().info("GUI wurde aktualisiert nachricht empfangen");
	   String[] msg =  neu.split("\\|");
	   if(!msg[2].equals("OLD")) {
		for(int i = 0; i<model.getChatraumArray().size();i++) {
			if(model.getChatraumArray().get(i).getName().equals(msg[0])) {
				
				//!!Here messages are added to the appropriate chat room
				model.getChatraumArray().get(i).addChatMessage(msg[1], serviceLocator.getConfiguration().getActualUser(), true); 
				if(model.getAcutalchatroom()!=model.getChatraumArray().get(i).getName()) {
				model.getChatraumArray().get(i).increaseCounter();
				}
				int counter = i;
					//count up message counters only when I am not in the chatroom
					if(model.getAcutalchatroom()!= model.getChatraumArray().get(i).getName()) {
						Platform.runLater(()->{model.getChatraumArray().get(counter).
							getLabel().setText(model.getChatraumArray().get(counter).getMsgCounter()+"");});
					}
						
						if(model.getAcutalchatroom().equals(msg[0])) {
							view.textArea.appendText(msg[1]+"\n"); //if the message for the current chatroom is Update TextArea
							
				}
			}
		}
		//For old chat messages
	   }else {
		
		for(int i = 0; i<model.getChatraumArray().size();i++) {
			if(model.getChatraumArray().get(i).getName().equals(msg[0])) {
				
				//!!Here messages are added to the appropriate chat room
				model.getChatraumArray().get(i).addChatMessage(msg[1], serviceLocator.getConfiguration().getActualUser(),false); 
				if(model.getAcutalchatroom()!=model.getChatraumArray().get(i).getName()) {
				model.getChatraumArray().get(i).increaseCounter();
				}
				
			}
		}
	   }
		
		return null;
	}
   
   
   	
	
	//#THIS METHOD IS FOR THE 1ST CLICK ON A CHAT BUTTON
	private Object updateChatArea(Chatraum chatraum) {
	view.textArea.clear();
	view.lblUsersOnline.setText("");
	serviceLocator.getConfiguration().setUsersOnline("");
	model.getUsersOnline(chatraum.getName());
	view.lblUsersOnline.setText("Users online: " + serviceLocator.getConfiguration().getUsersOnline());
	view.lblActualChatroom.setText("Actual Chatroom: " + model.getAcutalchatroom() + "                 ");
	
	
		
		
		for(int i =0; i<chatraum.messageList.size();i++) {
			view.textArea.appendText(chatraum.messageList.get(i)+"\n");
		}
		return null;
	}



	//Change language
	public void changePassword(Event password) {
		model.setMenuOption(3);
		view.btnMulti.setDisable(false);
		view.lblMulti.setDisable(false);
		view.txt1.setDisable(false);
		
		//Current language is compared with Local and so it is decided which language is the correct one
		if(serviceLocator.getTranslator().getCurrentLocale().toString().equals("de")) {
		view.lblMulti.setText("Passwort einfuegen: ");
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
	
	
	
	//delete user
	public void deleteUser(Event delete) {
		model.setMenuOption(4);
		view.btnMulti.setDisable(false);
		view.txt1.setDisable(false);
		view.lblMulti.setDisable(false);
		//Current language is compared with Local and so it is decided which language is the correct one
		if(serviceLocator.getTranslator().getCurrentLocale().toString().equals("de")) {
		view.lblMulti.setText("Benutzer loeschen: ");
		view.btnMulti.setText("loeschen");
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
		//Current language is compared with Local and so it is decided which language is the correct one
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
		//Current language is compared with Local and so it is decided which language is the correct one
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
		//Current language is compared with Local and so it is decided which language is the correct one
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
	
	
	
	
	//Option and Chatroom Menu functions
	public void OptionStart(Event option) {
		
		try {
		boolean sendtoserver = false;
		String senden = null;
		String text1 = view.txt1.getText();
		String text2 = view.txt2.getText();
		if(text1.length()>2) {
		
		switch(model.getMenuOption()){
		//CreateChatroom function
		case 1:
			senden = "CreateChatroom|"+serviceLocator.getConfiguration().getSalt()+"|"+text1 +"|"+"true";
			sendtoserver = true;
			break;
		//JoinChatroom function
		case 2:
			if(text2.length()>2) {
			senden = "JoinChatroom|"+serviceLocator.getConfiguration().getSalt()+"|"+text2+"|"+ text1;
			model.setAcutalchatroom(text2);
			sendtoserver = true;
			}else {
			if(serviceLocator.getTranslator().getCurrentLocale().toString().equals("de")) {
			view.txt2.setText("!mindestends 3 Zeichen!");
			view.txt1.clear();
			}else {
			view.txt2.setText("!at least 3 letters!");
			view.txt1.clear();
			}
			sendtoserver = false;
			}
			
			break;
		//ChangePassword function
		case 3:
			senden = "ChangePassword|"+serviceLocator.getConfiguration().getSalt()+"|"+text1;
			sendtoserver = true;
			break;
		//Deletelogin function
		case 4:	
			
			if(serviceLocator.getConfiguration().getActualUser().equals(text1)&&(serviceLocator.getTranslator().getCurrentLocale().toString().equals("de"))) { 
			view.txtChatMessage.setVisible(false);
			view.txt1.setText("mit neuem User einloggen");
			}else {
			view.txtChatMessage.setVisible(false);
			view.txt1.setText("login with new user");
			}
			
			senden = "DeleteLogin|"+serviceLocator.getConfiguration().getSalt()+"|"+text1;
			sendtoserver = true;
			break;
		case 5:
		//LeaveChatroom function
			if(text2.length()>2) {
			senden = "LeaveChatroom|"+serviceLocator.getConfiguration().getSalt()+"|"+text2+"|"+ text1;
			model.setAcutalchatroom(null);
			sendtoserver = true;
			}else {
				if(serviceLocator.getTranslator().getCurrentLocale().toString().equals("de")) {
					view.txt2.setText("!mindestends 3 Zeichen!");
					view.txt1.clear();
					}else {
					view.txt2.setText("!at least 3 letters!");
					view.txt1.clear();
					}
			sendtoserver = false;
			}
			break;
		
		}
		
		if(sendtoserver == true)
		model.sendMessagetoServer(senden);
		
		
        
		if(serviceLocator.getConfiguration().getOthers()==true) {
		serviceLocator.getLogger().info("Erfolgreich");
		view.btnMulti.setDisable(true);
		view.txt1.setDisable(true);
		view.txt2.setDisable(true);
		
		}else {
		serviceLocator.getLogger().info("OptionStart: "+model.getMenuOption()+" ist gescheitert");
		}
		}else {
			if(serviceLocator.getTranslator().getCurrentLocale().toString().equals("de")) {
				view.txt1.setText("!mindestends 3 Zeichen!");
				
				}else {
				view.txt1.setText("!at least 3 letters!");
				
				}	
			
		}
		
		} catch(Exception exception) {
			this.serviceLocator.getLogger().info(exception.getMessage());
			}
		
		}
		

    
	
	
	
	//send chatmessage to server
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
	
	
	//delete chat history
	public void deleteChatroomHistory(Event history) {
		model.deleteChatroomHistory();
		view.textArea.setText("");
	}
	
	
	}
    

    

