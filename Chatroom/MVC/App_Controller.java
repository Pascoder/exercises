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
   
 

    public App_Controller(App_Model model, App_View view, String salt) {
        super(model, view);
       
        this.salt = salt;
     
        servicelocator = ServiceLocator.getServiceLocator();
        
     // register ourselves to listen for button clicks
        view.password.setOnAction(this::changePassword);
        view.changepw.setOnAction(this::OptionStart);
        view.delete.setOnAction(this::deleteUser);
        view.changepw.setDisable(true);
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
    }
    
   

	public void buttonClick() {     
    }
	
	
	
	
	//Sprache Ändern
	public void changePassword(Event password) {
		view.changepw.setDisable(false);
		
		if(view.password.getText().equals("change password")) {
			view.lblName.setText("Enter password: ");
			view.changepw.setText("change");
			
		}else {
			view.lblName.setText("Passwort eingeben: ");
			view.changepw.setText("wechseln");
		}
		
	}
	
	//Sprache Ändern 
	public void deleteUser(Event delete) {
		view.changepw.setDisable(false);
		
		if(view.password.getText().equals("change password")) {
			view.lblName.setText("Delete User: ");
			view.changepw.setText("delete");
			
		}else {
			view.lblName.setText("Benutzer löschen: ");
			view.changepw.setText("löschen");
		
		}
	}
	
	
	
	
	
	
	public void OptionStart(Event option) {
		
		//wenn auf Button change oder wechseln steht dann diese Methode 
		
		if(view.changepw.getText().equals("change")||view.changepw.getText().equals("wechseln")) {
		String servermessage = null;
		String pw = view.txtName.getText();
		
			try{
				//Senden eines neuen Passwort
				System.out.println("SocketTest: "+servicelocator.getConfiguration().getSocket());
				String senden = "ChangePassword|"+salt+"|"+pw; 
				
				
				servicelocator.getConfiguration().getWriter().write(senden);
				servicelocator.getConfiguration().getWriter().write("\n");
				servicelocator.getConfiguration().getWriter().flush();
				
				
				//Empfangen der Antwort des Servers
				servermessage = servicelocator.getConfiguration().getReader().readLine();
				
				view.txtName.setText(servermessage);
				servicelocator.getLogger().info("Password changed");
				
				}catch(IOException exception) {
					this.servicelocator.getLogger().info("Something goes wrong by changing password");
					exception.getMessage();
				}
		}
		
		
		//Wenn auf Button delete oder löschen steht dann diese Methode
		
		if(view.changepw.getText().equals("delete")||view.changepw.getText().equals("löschen")) {
			this.servicelocator.getLogger().info("Button delete clicked");
		}
			
		
		
	}
    
    

    
}
