package MVC;

import Splash.ServiceLocator;
import chatroom.server.Account;

import java.io.IOException;
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
    ServiceLocator serviceLocator;

    public App_Controller(App_Model model, App_View view) {
        super(model, view);
        
        //Verbinden mit dem Server
        view.btnConnect.setOnAction(event -> {
			try {
				buttonConnect(event);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
        
     // register ourselves to listen for button clicks
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
            	Account.saveAccounts();//Accounts vor dem schliessen speichern auf dem Server
                Platform.exit();
            }
        });
        
        serviceLocator = ServiceLocator.getServiceLocator();        
        serviceLocator.getLogger().info("Application controller initialized");
    }
    
    public void buttonClick() {
          

             
    }
    //Methode die Eingaben ausliest und eine Verbindung zum Server herstellten soll
    public void buttonConnect(Event connect) throws UnknownHostException, IOException {
    	String ip = view.txtIpAddress.getText();
    	String port = view.txtPort.getText();
    	
    	boolean connected = model.Connect(ip, port);
    	if(connected == false) {
    		view.txtChatArea.setText("Wrong IP or Port");
    	}else {
    		
    		view.txtChatArea.setText("Conncected");
    	}
    }
}
