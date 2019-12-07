package Splash;


import MVC.App_Model;
import MVC.App_View;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import MVC.App_Controller;
import Splash.Splash_Model;
import Splash.Splash_View;
import chatroom.server.Account;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * @author Brad Richards
 */
public class JavaFX_App_Template extends Application {
    private static JavaFX_App_Template mainProgram; // singleton
    private Splash_View splashView;
    private App_View view;
    protected Stage primary;
    private Login_View login_view;
    private Socket socket; //Damit socket in allen Klassen genutzt werden kann ist nötig für writer
   
    
    

    private ServiceLocator serviceLocator; // resources, after initialization

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Note: This method is called on the main thread, not the JavaFX
     * Application Thread. This means that we cannot display anything to the
     * user at this point. Since we want to show a splash screen, this means
     * that we cannot do any real initialization here.
     * 
     * This implementation ensures that the application is a singleton; only one
     * per JVM-instance. On client installations this is not necessary (each
     * application runs in its own JVM). However, it can be important on server
     * installations.
     * 
     * Why is it important that only one instance run in the JVM? Because our
     * initialized resources are a singleton - if two programs instances were
     * running, they would use (and overwrite) each other's resources!
     */
    @Override
    public void init() {
        if (mainProgram == null) {
            mainProgram = this;
        } else {
            Platform.exit();
        }
    }

    /**
     * This method is called after init(), and is called on the JavaFX
     * Application Thread, so we can display a GUI. We have two GUIs: a splash
     * screen and the application. Both of these follow the MVC model.
     * 
     * We first display the splash screen. The model is where all initialization
     * for the application takes place. The controller updates a progress-bar in
     * the view, and (after initialization is finished) calls the startApp()
     * method in this class.
     */
    @Override
    public void start(Stage primaryStage) {
        // Create and display the splash screen and model
        Splash_Model splashModel = new Splash_Model();
        splashView = new Splash_View(primaryStage, splashModel);
        new Splash_Controller(this, splashModel, splashView);
        splashView.start();

        // Display the splash screen and begin the initialization
        splashModel.initialize();
        
        
    }

    /**
     * This method is called when the splash screen has finished initializing
     * the application. The initialized resources are in a ServiceLocator
     * singleton. Our task is to now create the application MVC components, to
     * hide the splash screen, and to display the application GUI.
     * 
     * Multitasking note: This method is called from an event-handler in the
     * Splash_Controller, which means that it is on the JavaFX Application
     * Thread, which means that it is allowed to work with GUI components.
     * http://docs.oracle.com/javafx/2/threads/jfxpub-threads.htm
     */
    //Login Menu wird gestartet
    public void startLoginMenu() {
    	createSocket();
    	splashView.stop();
    	
    	primary = new Stage();
    	Login_Model model = new Login_Model();
		login_view = new Login_View(primary, model);
		Login_Controller controller = new Login_Controller(model, login_view, mainProgram, socket);
		
		
		
		/* Mit dem Loggin Button ist es nun möglich in den Messenger zu kommen, muss angepasst werden
		 * wenn dies so gemacht wird wird im controller setOnAction von btnlogin übersprungen
		*/
		
		
		
    }
    
    
   

	public void startApp(String salt) {
        Stage appStage = new Stage();
        
        // Initialize the application MVC components. Note that these components
        // can only be initialized now, because they may depend on the
        // resources initialized by the splash screen
        App_Model model = new App_Model();
        view = new App_View(appStage, model);
        new App_Controller(model, view, socket, salt);

        // Resources are now initialized
        serviceLocator = ServiceLocator.getServiceLocator();

        // Close the splash screen, and set the reference to null, so that all
        // Splash_XXX objects can be garbage collected
        splashView.stop();
        splashView = null;

        view.start();
    }
    

    /**
     * The stop method is the opposite of the start method. It provides an
     * opportunity to close down the program, including GUI components. If the
     * start method has never been called, the stop method may or may not be
     * called.
     * 
     * Make the GUI invisible first. This prevents the user from taking any
     * actions while the program is ending.
     */
    @Override
    public void stop() {
        serviceLocator.getConfiguration().save();
        if (view != null) {
            // Make the view invisible
            view.stop();
        }

        // More cleanup code as needed

        serviceLocator.getLogger().info("Application terminated");
    }

    // Static getter for a reference to the main program object
    protected static JavaFX_App_Template getMainProgram() {
        return mainProgram;
    }
    private void createSocket() {
		//Hier wird Socket erstellt
			try {
				this.socket = new Socket("147.86.8.31", 50001);
			} catch (UnknownHostException e) {
				serviceLocator.getLogger().warning("Problem with Socket");
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				serviceLocator.getLogger().warning("Problem with Socket");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
    

	
}
