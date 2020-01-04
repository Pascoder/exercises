package MVC;

import Splash.ServiceLocator;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

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
    private ArrayList<String> msg;
    private ArrayList <Chatraum> chatraumArray = new ArrayList<>();
    private String acutalchatroom = null;
    private String actualUser = null;
    
    //0=kein Menu ausgewählt, 1= Chatroom erstellen, 2= Chatroom beitreten, 3= User hinzufügen
    private int menuOption = 0;
 

	public App_Model() {
        
        msg = new ArrayList<String>();
        serviceLocator = ServiceLocator.getServiceLocator();        
        serviceLocator.getLogger().info("Application model initialized");
    }



	public String getUsersOnline(String chatraum) {
		String senden = "ListChatroomUsers|"+serviceLocator.getConfiguration().getSalt()+"|"+chatraum;
		try {
			sendMessagetoServer(senden);
		} catch (Exception e) {
			serviceLocator.getLogger().info("ListChatroomUsers nicht möglich");
		}
		
		return null;
	}
	
	
	public void loadChatrooms() {
		try{
			String loadChats = "ListChatrooms|"+serviceLocator.getConfiguration().getSalt(); 
				sendMessagetoServer(loadChats);
			serviceLocator.getLogger().info("Chatrooms loaded");
		}catch(Exception exception) {
				this.serviceLocator.getLogger().info("Something goes wrong by loading chatrooms");
				exception.getMessage();
		}
		
	}

	
	
	// Empfangen der Chatrooms vom Server
		public void receiveChatrooms() {
			
			msg = serviceLocator.getConfiguration().getChatrooms();
			
		}



		public ArrayList<String> getMsg() {
			return msg;
		}



		public void setMsg(ArrayList<String> msg) {
			this.msg = msg;
		}



		public ArrayList<Chatraum> getChatraumArray() {
			return chatraumArray;
		}



		public void setChatraumArray(ArrayList<Chatraum> chatraumArray) {
			this.chatraumArray = chatraumArray;
		}



		public String getAcutalchatroom() {
			return acutalchatroom;
		}



		public void setAcutalchatroom(String acutalchatroom) {
			this.acutalchatroom = acutalchatroom;
		}



		public String getActualUser() {
			return actualUser;
		}



		public void setActualUser(String actualUser) {
			this.actualUser = actualUser;
		}
	

		 public int getMenuOption() {
				return menuOption;
			}



			public void setMenuOption(int menuOption) {
				this.menuOption = menuOption;
			}
			
//Alle Nachrichten sollen von hier aus gesendet werden damit man 1 mal Thread.Sleep() verwendet werden kann			
  public void sendMessagetoServer(String message) {
	  try {
	  serviceLocator.getConfiguration().getWriter().write(message);
		serviceLocator.getConfiguration().getWriter().write("\n");
		serviceLocator.getConfiguration().getWriter().flush();
		Thread.sleep(100);
	
	  }catch (Exception msg){
		  msg.printStackTrace();
	  }
  }
    
    
}
