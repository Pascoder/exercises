package poker.version_graphics;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import poker.version_graphics.controller.PokerGameController;
import poker.version_graphics.model.PokerGameModel;
import poker.version_graphics.view.EnterMenu;
import poker.version_graphics.view.PokerGameView;

public class PokerGame extends Application {
	//two players are given, we can change here the number of players
	public static int NUM_PLAYERS;
	EnterMenu menu;
	PokerGameModel model;
	PokerGameView view;
	PokerGameController controller;
	Stage primaryStage;

	
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	// Create and initialize EnterMenu components
    	this.primaryStage = primaryStage;
    		
    		startEnterMenu();
    		menu.enter.pressedProperty().addListener((something,old,neu)->continueProcess(neu));	
    		
    	}

	private Object continueProcess(Boolean neu)  {
		// Create and initialize the MVC components
		PokerGame.NUM_PLAYERS = menu.getCounter();
		if(menu.getCounter()>2) {
			menu.setProperty();
		}
		model = new PokerGameModel();
       	view = new PokerGameView(primaryStage, model);
       	controller = new PokerGameController(model, view);
		return true;
	}

	private void startEnterMenu() {
		menu = new EnterMenu();		
		
	}	
    
}
