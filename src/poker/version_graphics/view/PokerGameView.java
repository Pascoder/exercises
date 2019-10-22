package poker.version_graphics.view;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import poker.version_graphics.PokerGame;
import poker.version_graphics.model.PokerGameModel;

public class PokerGameView {
	private TilePane players;
	private ControlArea controls;
	private PokerGameModel model;
	
	
	
	public PokerGameView(Stage stage, PokerGameModel model) {
		
		
		
		this.model = model;
		// Create all of the player panes we need, and put them into an HBox
		players = new TilePane();
		
		for (int i = 0; i < PokerGame.NUM_PLAYERS; i++) {
			PlayerPane pp = new PlayerPane();
			pp.setPlayer(model.getPlayer(i)); // link to player object in the logic
			players.getChildren().add(pp);
			
		}
		
		// Create the control area
		controls = new ControlArea();
		controls.linkDeck(model.getDeck()); // link DeckLabel to DeckOfCards in the logic
		
		// Put players and controls into a BorderPane
		BorderPane root = new BorderPane();
		root.setCenter(players);
		root.setBottom(controls);
		
		// Disallow resizing - which is difficult to get right with images
		stage.setResizable(false);
		
		
		
		//Tailpane verwenden damit grösse automatisch anpasst

        // Create the scene using our layout; then display it MAX 1140
		
		int height = 0;
		if((PokerGame.NUM_PLAYERS)<3) {
			height = 271;
		}else {
		if((PokerGame.NUM_PLAYERS)>=3&& PokerGame.NUM_PLAYERS<=4) {
			height =  501;
		}else {
		if(PokerGame.NUM_PLAYERS >4) {
			height = 731;
		}
		}
		
		}
        
        Scene scene = new Scene(root,1138,height);
        scene.getStylesheets().add(
                getClass().getResource("poker.css").toExternalForm());
        stage.setTitle("Poker Miniproject");
        stage.setScene(scene);
        
        stage.show();	
      
  		
		 
		 
	}
	
	
	public PlayerPane getPlayerPane(int i) {
		return (PlayerPane) players.getChildren().get(i);
	}
	
	public Button getShuffleButton() {
		return controls.btnShuffle;
	}
	
	public Button getDealButton() {
		return controls.btnDeal;
	}

	
	
	
	
}
