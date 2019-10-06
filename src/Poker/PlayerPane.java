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
	
	public void setPlayer(PokerPlayer player) {
    	this.player = player;
    	updatePlayerDisplay(); // Immediately display the player information
    }
    
    public void updatePlayerDisplay() {
    	labelName.setText(player.getName());
    	for (int i = 0; i < PokerPlayer.HANDSIZE; i++) {
    		PokerCard card = null;
    		if (player.getCards().size() > i) card = player.getCards().get(i);
    		CardLabel cl = (CardLabel) hboxCards.getChildren().get(i);
    		cl.setCard(card);
    		HandType evaluation = player.evaluateHand();
    		if (evaluation != null)
    			lblEvaluation.setText(evaluation.toString());
    		else
    			lblEvaluation.setText("--");
    	}

	

}
