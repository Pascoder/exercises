package MVC;

import Splash.ServiceLocator;


import java.io.BufferedWriter;
import java.io.FileWriter;
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
   
    
    //0=no menu selected, 1= create cahtroom, 2= join chatroom, 3= add user
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
			serviceLocator.getLogger().info("ListChatroomUsers nicht moeglich");
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

	
	
	// Receiving the chat rooms from the server
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



public void saveFile() {
	String file = "ChatRooms.txt";
	

	try(BufferedWriter writer = new BufferedWriter(new FileWriter(file));){
		for(Chatraum c : chatraumArray) {
			String chatname = c.getName();
			for(int b = 0; b< c.messageList.size();b++) {
				writer.write(chatname+"|"+c.messageList.get(b)+"|"+"OLD"+"\n");
			}
			
		}
		
		writer.flush();
	}catch(Exception e) {
		e.getMessage();
	}
	
}


//delete all old chatroom messages
public void deleteChatroomHistory() {
	for(Chatraum c : chatraumArray) {
		String chatname = c.getName();
		
		c.messageList.clear();
		
	}
	
}




	
}
    
    

