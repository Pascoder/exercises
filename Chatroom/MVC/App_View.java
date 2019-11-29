package MVC;

import java.util.Locale;
import java.util.logging.Logger;

import Splash.ServiceLocator;
import MVC.View;
import Splash.Translator;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * @author Brad Richards
 */
public class App_View extends View<App_Model> {
   //Menu
	
	Menu menuFile;
    Menu menuFileLanguage;
    Menu menuHelp;
    
    //Top Controlls
    Label lblIpAddress;
    TextField txtIpAddress;
	Label lblPort;
	TextField txtPort;
	Label lblName;
	TextField txtName;
	Button btnConnect;
    
    //Chat Area
	TextArea txtChatArea;
    
    //Bottom Controlls
	TextField txtChatMessage; 
    Button sendbutton;
    
  

	public App_View(Stage stage, App_Model model) {
        super(stage, model);
        ServiceLocator.getServiceLocator().getLogger().info("Application view initialized");
    }

	@Override
	protected Scene create_GUI() {
	    ServiceLocator sl = ServiceLocator.getServiceLocator();  
	    Logger logger = sl.getLogger();
	    
	    MenuBar menuBar = new MenuBar();
	    menuFile = new Menu();
	    menuFileLanguage = new Menu();
	    menuFile.getItems().add(menuFileLanguage);
	    
       for (Locale locale : sl.getLocales()) {
           MenuItem language = new MenuItem(locale.getLanguage());
           menuFileLanguage.getItems().add(language);
           language.setOnAction( event -> {
				sl.getConfiguration().setLocalOption("Language", locale.getLanguage());
                sl.setTranslator(new Translator(locale.getLanguage()));
                updateTexts();
            });
        }
	    
        menuHelp = new Menu();
	    menuBar.getMenus().addAll(menuFile, menuHelp);
		
	    
	   //Menu 
		GridPane root = new GridPane();
		root.add(menuBar, 0, 0);
		
		//Top
		
		lblIpAddress = new Label();
		txtIpAddress = new TextField();
		lblPort = new Label();
		txtPort = new TextField();
		lblName = new Label();
		txtName = new TextField();
		btnConnect = new Button();
        
		
		lblIpAddress.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
		lblPort.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
		lblName.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
		btnConnect.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
		
			// Set sizes for top TextFields
		txtIpAddress.setMinWidth(150); txtIpAddress.setPrefWidth(150);
		txtPort.setMinWidth(60); txtPort.setPrefWidth(60);
		txtName.setMinWidth(150); txtName.setPrefWidth(150);
        
		HBox top = new HBox(lblIpAddress, txtIpAddress, lblPort, txtPort, lblName, txtName, btnConnect);
		top.getStyleClass().add("hbox"); 
        root.add(top, 0, 1);
        
        //Message Box
        
        txtChatArea = new TextArea();
        root.add(txtChatArea, 0, 2);
        
        
        //Bottom Pane
       
        txtChatMessage = new TextField();
        HBox.setHgrow(txtChatMessage, Priority.ALWAYS);	
        
       
        sendbutton = new Button();
        sendbutton.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
        HBox bottom = new HBox(txtChatMessage, sendbutton);
        bottom.getStyleClass().add("hbox");
        root.add(bottom, 0, 3);
        
        
        
        
        updateTexts();
		
        Scene scene = new Scene(root);
        scene.getStylesheets().add(
                getClass().getResource("style.css").toExternalForm());
        return scene;
	}
	
	   protected void updateTexts() {
	       Translator t = ServiceLocator.getServiceLocator().getTranslator();
	        
	        // The menu entries
	       menuFile.setText(t.getString("program.menu.file"));
	       menuFileLanguage.setText(t.getString("program.menu.file.language"));
           menuHelp.setText(t.getString("program.menu.help"));
           
           // Top Controls
           lblIpAddress.setText(t.getString("label.lblIpAddress"));
           lblPort.setText(t.getString("label.lblPort"));
           lblName.setText(t.getString("label.lblname"));
           btnConnect.setText(t.getString("button.btnConnect"));
	        
	        // Bottom Controls
           sendbutton.setText(t.getString("button.sendbutton"));
           
           stage.setTitle(t.getString("program.name"));
	    }
}