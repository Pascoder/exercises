package Splash;

import Splash.JavaFX_App_Template;

import java.io.IOException;

import MVC.Controller;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;


/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * @author Brad Richards
 */
public class Splash_Controller extends Controller<Splash_Model, Splash_View> {
	private Login_View viewlogin;
	private JavaFX_App_Template javaFX_App_Template;
	

    public Splash_Controller(final JavaFX_App_Template javaFX_App_Template, Splash_Model model, Splash_View view) {
        super(model, view);
        
     
        view.progress.progressProperty().bind(model.initializer.progressProperty());
        
        

        
        model.initializer.stateProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue == Worker.State.SUCCEEDED)
                    	
                    	javaFX_App_Template.startLoginMenu();
                    
                    	
                        
                });
        
       
        
        
        }
    
}
