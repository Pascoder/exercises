package Poker;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardLabel extends Label{
	public CardLabel() {
		super();
		this.getStyleClass().add("card");
	}

	public void setCard(PokerCard card) {
		if (card != null) {
			String fileName = cardToFileName(card);
			Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("Poker/images/" + fileName));
			ImageView imv = new ImageView(image);
			imv.fitWidthProperty().bind(this.widthProperty());
			imv.fitHeightProperty().bind(this.heightProperty());
			imv.setPreserveRatio(true);
			this.setGraphic(imv);
		} else {
			this.setGraphic(null);
		}
	}
		
		private String cardToFileName(PokerCard card) {
			String rank = card.getCardRank().toString();
			String suit = card.getCardSuit().toString();
			return rank + "_of_" + suit + ".png";
		}

		
	}

