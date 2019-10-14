package poker.version_graphics;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import poker.version_graphics.controller.PokerGameController;
import poker.version_graphics.model.PokerGameModel;
import poker.version_graphics.view.EnterMenu;
import poker.version_graphics.view.PokerGameView;

public class PokerGame extends Application {
	//two players are given, we can change here the number of players
	
	public static int NUM_PLAYERS = 1;
	EnterMenu menu;
	PokerGameModel model;
	PokerGameView view;
	PokerGameController controller;

	
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	// Create and initialize the MVC components

    		
    		startEnterMenu();
    		startGUI(primaryStage);
    				
    		
    	}

	private void startEnterMenu() {
		menu = new EnterMenu();
		
		
		
	}

	private void startGUI(Stage primaryStage) throws Exception{
		
			model = new PokerGameModel();
	       	view = new PokerGameView(primaryStage, model);
	       	controller = new PokerGameController(model, view);
	       	
		
		
	}
    	
    	
    	 
    	
    	
    	
    
}
