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
        view.changepw.setOnAction(this::btnchangePW);
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
	
	public void changePassword(Event password) {
		if(view.password.getText().equals("change password")) {
			view.lblName.setText("Enter password: ");
	
		}else {
			view.lblName.setText("Passwort eingeben: ");
		
		}
		
	}
	
	public void btnchangePW(Event btnchangePW) {
		String servermessage = null;
		String pw = view.txtName.getText();
		if(pw.length() < 3) {
			view.txtName.setText("password to small");
		}else {
			
			try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(servicelocator.getConfiguration().getSocket().getOutputStream()));
					BufferedReader reader = new BufferedReader(new InputStreamReader(servicelocator.getConfiguration().getSocket().getInputStream()));
				){
				//Senden eines neuen Passwort
				System.out.println("SocketTest: "+servicelocator.getConfiguration().getSocket());
				String senden = "ChangePassword|"+salt+"|"+pw; //<--@TODO Fehler salt verändert sich
				System.out.println(senden);
				
				writer.write(senden);
				writer.write("\n");
				writer.flush();
				
				//Empfangen der Antwort des Servers
				servermessage = reader.readLine();
				
				view.txtName.setText(servermessage);
				
				}catch(Exception exeption) {
					this.servicelocator.getLogger().info("Something goes wrong by changing password");
					
				}
		
			
		
		}
	}
    
    

    
}
