package Poker;

public class PokerCard {
	

	private CardRank cardRank;
	private CardSuit cardSuit;
	

	
	public PokerCard(CardRank cardRank, CardSuit cardSuit) {
		
		this.cardRank = cardRank;
		this.cardSuit = cardSuit;
		
		
		
	}

	public enum CardSuit {
	
	HEARTS, 
	SPADES, 
	CLUBS, 
	DIAMONDS;

	}
	

	
}
	


