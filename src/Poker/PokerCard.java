package Poker;

public class PokerCard {
	final CardRank cardRank;
	final CardSuit cardSuit;
	

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
	
}
	


