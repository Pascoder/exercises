package Poker;

import javafx.scene.image.Image;

public class PokerCard {
	final CardRank cardRank;
	final CardSuit cardSuit;
	public Image images; //Möglichkeit bieten Pokerkarten ein Bild zu hinterlegen
	

	public PokerCard(CardRank cardRank, CardSuit cardSuit) {
		
		this.cardRank = cardRank;
		this.cardSuit = cardSuit;
	}
	
	public enum CardSuit {
		HEARTS, 
		SPADES, 
		CLUBS, 
		DIAMONDS
	}
	
	
	public enum CardRank {
		TWO, THREE, FOUR, FIVE, SIX,
		SEVEN, EIGHT, NINE, TEN,
		JACK, QUEEN, KING, ACE
	}
	
	public String toString(){
   		return cardSuit.name()+"-"+cardRank.name();
	}
	public  void addImage(Image img) {
		this.images = img;
	}
	
	
}
	


