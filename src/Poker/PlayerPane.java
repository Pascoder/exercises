package Poker;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PlayerPane extends VBox{
	private Label labelName = new Label();
	private HBox hboxCards = new HBox();
	private Label labelEvaluation = new Label();
	
	private PokerPlayer player;
	
	public PlayerPane() {
		super();
		this.getStyleClass().add("player");
		
		this.getChildren().addAll(labelName,hboxCards, labelEvaluation );
		
		for (int i = 0; i < 5; i++) {
            Label lblCard = new CardLabel();
            hboxCards.getChildren().add(lblCard);
        }

	}
	

}
