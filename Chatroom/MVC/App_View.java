package MVC;

import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Logger;
import Splash.ServiceLocator;
import MVC.View;
import Splash.Translator;
import chatroom.testClient.Chatroom;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
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
    Menu password;
    Menu delete;
    Menu chatroom;
    Menu createChatroom;
    Menu addUser;
    Menu leavechatroom;
    
    //Top Controlls
	Label lblMulti;
	TextField txt1;
	TextField txt2;
	Button btnMulti;
	
	Label lblUserInfo, lblUser;
    
    //Chat Area
	HBox infoBox;
	Label lblInfo, lblUsersOnline, lblActualChatroom, lblNewMsg;
	VBox chatRoomBox;
	ScrollPane scroll;
	HBox middleBox;
	ArrayList<Button> btnArray = new ArrayList<Button>();
	
	
	TextArea textArea;
	
	
    
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
        password = new Menu();
        delete = new Menu();
        chatroom = new Menu();
        createChatroom = new Menu();
        addUser = new Menu();
        leavechatroom = new Menu();
        menuHelp.getItems().add(password);
        menuHelp.getItems().add(delete);
        chatroom.getItems().add(createChatroom);
        chatroom.getItems().add(addUser);
        chatroom.getItems().add(leavechatroom);
	    menuBar.getMenus().addAll(menuFile, menuHelp,chatroom);
		
	    
	   //Menu 
		GridPane root = new GridPane();
		root.add(menuBar, 0, 0,3,1);
		
		//Top
	
		lblMulti = new Label();
		txt1 = new TextField();
		txt2 = new TextField();
		btnMulti = new Button();
		
		lblUserInfo = new Label();
		lblUser = new Label();
		
       
		lblMulti.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
	
		
			// Set sizes for top TextFields
	
		
		txt1.setMinWidth(150); txt1.setPrefWidth(150);
        
		HBox top = new HBox(lblMulti, txt1,txt2, btnMulti,lblUserInfo, lblUser);
		top.getStyleClass().add("hbox"); 
        root.add(top, 0, 1,3,1);
        
        //Message Box
        infoBox = new HBox();
        lblInfo = new Label();
        middleBox = new HBox();
        chatRoomBox = new VBox();
        scroll = new ScrollPane(chatRoomBox);
        lblUsersOnline = new Label();
        lblActualChatroom = new Label();
        lblNewMsg = new Label();
        textArea = new TextArea();
        textArea.setMinSize(80, 80);
        
        scroll.setMinWidth(220);
    
    	
    	
        middleBox.getChildren().addAll(scroll, textArea);
        chatRoomBox.getChildren().addAll(lblInfo );
        infoBox.getChildren().addAll(lblActualChatroom, lblUsersOnline);
        
        root.add(infoBox, 1, 2,3,1);
        root.add(chatRoomBox, 0, 3);
        root.add(middleBox, 2, 3);
        
        
        
        //Bottom Pane
       
        txtChatMessage = new TextField();
        HBox.setHgrow(txtChatMessage, Priority.ALWAYS);	
        
       
        sendbutton = new Button();
        sendbutton.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
        HBox bottom = new HBox(txtChatMessage, sendbutton);
        bottom.getStyleClass().add("hbox");
        root.add(bottom, 0, 4,3,1);
        
        
        
        
        updateTexts();
		//@TODO make GUI Design 
        Scene scene = new Scene(root,700,800);
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
           password.setText(t.getString("program.menu.password"));
           btnMulti.setText(t.getString("button.changepw"));
           delete.setText(t.getString("program.delete"));
           chatroom.setText(t.getString("program.chatroom"));
           createChatroom.setText(t.getString("program.createchatroom"));
           addUser.setText(t.getString("program.adduser"));
           
           // Top Controls
           lblMulti.setText(t.getString("label.lblname"));
           leavechatroom.setText(t.getString("program.leavechatroom"));
           lblInfo.setText(t.getString("label.lblInfo"));
           lblUserInfo.setText(t.getString("label.lblUserInfo"));
	        
	        // Bottom Controls
           sendbutton.setText(t.getString("button.sendbutton"));
           stage.setTitle(t.getString("program.name"));
	    }

	

	public void addChatbox(String name, Chatraum c) {
		HBox ChatBox = new HBox();
		
		
		Button btn = new Button(name);
		btnArray.add(btn);
		
		//Soll neue Nachricht anzeigen, rechts neben Button
		Label lblNewMessage = new Label();
		lblNewMessage.textProperty().bind(c.newmsgProperty().asString());
		
		
		btn.setPrefWidth(180);
		
		ChatBox.getChildren().addAll(btn,lblNewMessage);
		chatRoomBox.getChildren().add(ChatBox);
		
		
	}

	public ArrayList<Button> getBtnArray() {
		return btnArray;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}